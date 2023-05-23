import ManyToMany.Angajare;
import ManyToMany.Rezervare;
import Persoana.Angajat;
import Hotel.Hotel;
import Camera.Camera;
import Persoana.Client;
import Serviciu.Serviciu;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class DatabaseQueries {
    private static DatabaseQueries instance;
    private String url;
    private String username;
    private String password;
    private DatabaseQueries() {
        url = Secrets.url;
        username = Secrets.username;
        password = Secrets.password;
    }
    public static synchronized DatabaseQueries getInstance() {
        if (instance == null) {
            instance = new DatabaseQueries();
        }
        return instance;
    }
    private Connection createConnection(){
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL database!");
            return con;
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return null;
    }

    public Set<Camera> getAllCamereForHotel(String nume){
        Set<Camera> camere = new HashSet<Camera>();
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM camera WHERE HotelNume = ?");
            st.setString(1, nume);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String numar = rs.getString("Numar");
                int etaj = rs.getInt("Etaj");
                int pretNoapte = rs.getInt("PretNoapte");
                int numarLocuri = rs.getInt("NumarLocuri");
                String descriereExtra = rs.getString("DescriereExtra");
                Map<String, Boolean> disp = new HashMap<>();
                // get disponibilitate from database
                PreparedStatement st2 = con.prepareStatement("SELECT * FROM disponibilitate WHERE CameraNumar = ? AND HotelNume = ?");
                st2.setString(1, numar);
                st2.setString(2, nume);
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next()) {
                    String data = rs2.getString("Date");
                    Boolean dispBool = rs2.getBoolean("Disponibil");
                    disp.put(data, dispBool);
                }
                rs2.close();
                st2.close();
                camere.add(new Camera(numar, etaj, pretNoapte, descriereExtra, numarLocuri, disp));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return camere;
    }

    public void setDisponibilitate(String numar, String hotel, String dataInceput, String dataSfarsit, Boolean disponibil) {
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement(
                    "INSERT INTO disponibilitate (CameraNumar, HotelNume, Date, Disponibil) " +
                            "VALUES (?, ?, ?, ?) " +
                            "ON CONFLICT (CameraNumar, HotelNume, Date) DO UPDATE SET Disponibil = excluded.Disponibil"
            );

            LocalDate startDate = LocalDate.parse(dataInceput);
            LocalDate endDate = LocalDate.parse(dataSfarsit);

            // Loop through the dates and execute the query for each date
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                st.setString(1, numar);
                st.setString(2, hotel);
                st.setString(3, currentDate.toString());
                st.setBoolean(4, disponibil);
                st.executeUpdate();
                currentDate = currentDate.plusDays(1);
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }


    public ArrayList<Rezervare> getAllRezervari(ArrayList<Client> clienti, ArrayList<Hotel> hoteluri){
        ArrayList<Rezervare> rezervari = new ArrayList<>();
        try {
            Connection con = createConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM rezervare");
            while (rs.next()) {
                String numeClient = rs.getString("PersoanaUsername");
                String numarCamera = rs.getString("CameraNumar");
                String numeHotel = rs.getString("HotelNume");
                String dataInceput = rs.getString("CheckInDate");
                String dataSfarsit = rs.getString("CheckOutDate");
                Boolean checkedIn = rs.getBoolean("CheckedIn");
                Client client = null;
                Hotel hotel = null;
                for(Client c : clienti){
                    if(c.getUsername().equals(numeClient)){
                        client = c;
                        break;
                    }
                }
                for(Hotel h : hoteluri){
                    if(h.getNume().equals(numeHotel)){
                        hotel = h;
                        break;
                    }
                }
                if(hotel != null)
                    for(Camera c : hotel.getCamere()){
                        if(c.getNumar().equals(numarCamera)){
                            Rezervare rezervare = new Rezervare(client, c, hotel, LocalDate.parse(dataInceput), LocalDate.parse(dataSfarsit), checkedIn);
                            rezervari.add(rezervare);
                            break;
                        }
                    }
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return rezervari;

    }

    public ArrayList<Angajare> getAllAngajari(ArrayList<Angajat> angajati, ArrayList<Hotel> hoteluri){
        ArrayList<Angajare> angajari = new ArrayList<>();
        try {
            Connection con = createConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM angajare");
            while (rs.next()) {
                String numeAngajat = rs.getString("PersoanaUsername");
                String numeHotel = rs.getString("HotelNume");
                String dataInceput = rs.getString("DataAngajare");
                Angajat angajat = null;
                Hotel hotel = null;
                for(Angajat a : angajati){
                    if(a.getUsername().equals(numeAngajat)){
                        angajat = a;
                        break;
                    }
                }
                for(Hotel h : hoteluri){
                    if(h.getNume().equals(numeHotel)){
                        hotel = h;
                        break;
                    }
                }
                assert hotel != null;
                Angajare angajare = new Angajare(angajat, hotel, LocalDate.parse(dataInceput));
                angajari.add(angajare);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return angajari;
    }

    public ArrayList<Hotel> getAllHotels(){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            Connection con = createConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hotel");
            while (rs.next()) {
                String nume = rs.getString("Nume");
                String adresa = rs.getString("Adresa");
                Set<Camera> camere = getAllCamereForHotel(nume);
                hotels.add(new Hotel(nume, adresa, camere, new ArrayList<Serviciu>()));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return hotels;
    }

    public ArrayList<Client> getAllClienti(){
        ArrayList<Client> clienti = new ArrayList<>();
        try {
            Connection con = createConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client");
            while (rs.next()) {
                String username = rs.getString("Username");
                String nume = rs.getString("Nume");
                String prenume = rs.getString("Prenume");
                String CNP = rs.getString("CNP");
                String telefon = rs.getString("Telefon");
                clienti.add(new Client(nume, prenume, CNP, telefon, username));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        return clienti;
    }

    public ArrayList<Angajat> getAllAngajati(){
        ArrayList<Angajat> angajati = new ArrayList<>();
        try {
            Connection con = createConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM angajat");
            while (rs.next()) {
                String username = rs.getString("Username");
                String nume = rs.getString("Nume");
                String prenume = rs.getString("Prenume");
                String CNP = rs.getString("CNP");
                int salariu = rs.getInt("Salariu");
                String unitateSalariu = rs.getString("UnitateSalariu");
                String job = rs.getString("Job");
                angajati.add(new Angajat(nume, prenume, CNP,username, salariu, unitateSalariu, job, null, null));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
        //sort angajati
        Collections.sort(angajati);
        return angajati;
    }

    public void addAngajat(Angajat angajat){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO angajat VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, angajat.getUsername());
            st.setString(2, angajat.getNume());
            st.setString(3, angajat.getPrenume());
            st.setString(4, angajat.getCNP());
            st.setInt(5, angajat.getSalariu());
            st.setString(6, angajat.getUnitateSalariu());
            st.setString(7, angajat.getJob());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void addClient(Client client){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO client VALUES (?, ?, ?, ?, ?)");
            st.setString(1, client.getUsername());
            st.setString(2, client.getNume());
            st.setString(3, client.getPrenume());
            st.setString(4, client.getCNP());
            st.setString(5, client.getTelefon());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void addHotel(Hotel hotel){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO hotel VALUES (?, ?)");
            st.setString(1, hotel.getNume());
            st.setString(2, hotel.getAdresa());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void addCamera(Camera camera, Hotel hotel){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO camera VALUES (?, ?, ?, ?, ?,?)");
            st.setString(1, camera.getNumar());
            st.setInt(2, camera.getEtaj());
            st.setInt(3, camera.getPretNoapte());
            st.setInt(4, camera.getNumarLocuri());
            st.setString(5, camera.getDescriereExtra());
            st.setString(6, hotel.getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void angajeazaLaHotel(Angajat angajat, Hotel hotel, LocalDate dataAngajarii){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO angajare VALUES (?, ?, ?)");
            st.setString(1, angajat.getUsername());
            st.setString(2, hotel.getNume());
            st.setDate(3, Date.valueOf(dataAngajarii));
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void demisioneazaDeLaHotel(Angajat angajat, Hotel hotel){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM angajare WHERE PersoanaUsername = ? AND HotelNume = ?");
            st.setString(1, angajat.getUsername());
            st.setString(2, hotel.getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void deleteAngajat(Angajat angajat){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM angajat WHERE Username = ?");
            st.setString(1, angajat.getUsername());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void deleteHotel(Hotel hotel){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM hotel WHERE Nume = ?");
            st.setString(1, hotel.getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void deleteClient(Client client){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM client WHERE Username = ?");
            st.setString(1, client.getUsername());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void addRezervare(Rezervare rezervare){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO rezervare VALUES (?, ?, ?, ?, ?, ?)");
            st.setString(1, rezervare.getPersoana().getUsername());
            st.setString(2, rezervare.getCamera().getNumar());
            st.setString(3, rezervare.getHotel().getNume());
            st.setDate(4, Date.valueOf(rezervare.getCheckInDate()));
            st.setDate(5, Date.valueOf(rezervare.getCheckOutDate()));
            st.setBoolean(6, rezervare.isCheckedIn());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void removeRezervare(Rezervare rezervare){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM rezervare WHERE PersoanaUsername = ? AND CameraNumar = ? AND HotelNume = ?");
            st.setString(1, rezervare.getPersoana().getUsername());
            st.setString(2, rezervare.getCamera().getNumar());
            st.setString(3, rezervare.getHotel().getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void checkIn(Rezervare rezervare){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("UPDATE rezervare SET CheckedIn = ? WHERE PersoanaUsername = ? AND CameraNumar = ? AND HotelNume = ?");
            st.setBoolean(1, true);
            st.setString(2, rezervare.getPersoana().getUsername());
            st.setString(3, rezervare.getCamera().getNumar());
            st.setString(4, rezervare.getHotel().getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void checkOut(Rezervare rezervare){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("UPDATE rezervare SET CheckedIn = ? WHERE PersoanaUsername = ? AND CameraNumar = ? AND HotelNume = ?");
            st.setBoolean(1, false);
            st.setString(2, rezervare.getPersoana().getUsername());
            st.setString(3, rezervare.getCamera().getNumar());
            st.setString(4, rezervare.getHotel().getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void changeCheckOutDate(Rezervare rezervare, LocalDate newCheckOutDate){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("UPDATE rezervare SET CheckOutDate = ? WHERE PersoanaUsername = ? AND CameraNumar = ? AND HotelNume = ?");
            st.setDate(1, Date.valueOf(newCheckOutDate));
            st.setString(2, rezervare.getPersoana().getUsername());
            st.setString(3, rezervare.getCamera().getNumar());
            st.setString(4, rezervare.getHotel().getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

    public void changeCheckInDate(Rezervare rezervare, LocalDate newCheckInDate){
        try {
            Connection con = createConnection();
            PreparedStatement st = con.prepareStatement("UPDATE rezervare SET CheckInDate = ? WHERE PersoanaUsername = ? AND CameraNumar = ? AND HotelNume = ?");
            st.setDate(1, Date.valueOf(newCheckInDate));
            st.setString(2, rezervare.getPersoana().getUsername());
            st.setString(3, rezervare.getCamera().getNumar());
            st.setString(4, rezervare.getHotel().getNume());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection failure or query execution error: " + e.getMessage());
        }
    }

}
