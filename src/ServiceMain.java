import Camera.Camera;
import Hotel.Hotel;
import ManyToMany.Angajare;
import ManyToMany.Rezervare;
import Persoana.Angajat;
import Persoana.Client;
import Serviciu.Serviciu;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class ServiceMain {

    private ArrayList<Hotel> Hotels;
    private ArrayList<Client> Clienti;
    private ArrayList<Angajat> Angajati;
    private ArrayList<Rezervare> Rezervari;
    private ArrayList<Angajare> Angajari;
    private final DatabaseQueries db;
    private final AuditService auditService = new AuditService();

    private String getString(String text){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti " + text + ": ");
        return scanner.nextLine();
    }

    private int getInt(String text){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti " + text + ": ");
        return scanner.nextInt();
    }

    private Hotel getHotelByName(String nume){
        for (Hotel hotel : Hotels) {
            if (hotel.getNume().equals(nume)) {
                return hotel;
            }
        }
        return null;
    }

    private Client getClientByUsername(String username){
        for (Client client : Clienti) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    private Angajat getAngajatByUsername(String nume){
        for (Angajat angajat : Angajati) {
            if (angajat.getUsername().equals(nume)) {
                return angajat;
            }
        }
        return null;
    }

    private Angajare getAngajare(String numeAngajat, String numeHotel){
        for (Angajare angajare : Angajari) {
            if (angajare.getHotel().getNume().equals(numeHotel) && angajare.getPersoana().getNume().equals(numeAngajat)) {
                return angajare;
            }
        }
        return null;
    }

    private Rezervare getRezervare(String username, String numeHotel, String numarCamera){
        for (Rezervare rezervare : Rezervari) {
            if(rezervare.getPersoana().getUsername().equals(username) && rezervare.getHotel().getNume().equals(numeHotel) && rezervare.getCamera().getNumar().equals(numarCamera)){
                return rezervare;
            }
        }
        System.out.println("Nu exista rezervare pentru acest client!");
        return null;
    }

    public void getRezervariClient(){
        String username = getString("username-ul clientului");
        Client client = getClientByUsername(username);
        if(client != null){
            for (Rezervare rezervare : Rezervari) {
                if(rezervare.getPersoana().getUsername().equals(username)){
                    System.out.println(rezervare);
                }
            }
        }
    }

    public Rezervare getRezervareInput(){
        String nume = getString("numele clientului");
        Client client = getClientByUsername(nume);
        String numeHotel = getString("numele hotelului");
        Hotel hotel = getHotelByName(numeHotel);
        if(client != null && hotel != null){
            String numeCamera = getString("numarul camerei camerei");
            Camera camera = hotel.cautaCamera(numeCamera);
            if (camera != null) {
                for(Rezervare rezervare : Rezervari){
                    if(rezervare.getCamera().equals(camera) && rezervare.getPersoana().equals(client) && rezervare.getHotel().equals(hotel)){
                        return rezervare;
                    }
                }
            }
        }
        return null;
    }

    public void addHotel(){
        String nume = getString("numele hotelului");
        String adresa = getString("adresa hotelului");
        Hotel existent = getHotelByName(nume);
        if(existent != null){
            System.out.println("Hotelul exista deja!");
            return;
        }
        Hotel hotel = new Hotel(nume, adresa, new HashSet<Camera>(), new ArrayList<Serviciu>());
        Hotels.add(hotel);
        db.addHotel(hotel);
    }

    public void addClient(){
        String nume = getString("numele clientului");
        String prenume = getString("prenumele clientului");
        String cnp = getString("CNP-ul clientului");
        String telefon = getString("numarul de telefon al clientului");
        String username = getString("username-ul clientului");
        Client existent = getClientByUsername(username);
        if(existent != null){
            System.out.println("Usernameul exista deja!");
            return;
        }
        Client client = new Client(nume, prenume, cnp, telefon, username);
        Clienti.add(client);
        db.addClient(client);
    }

    public void addAngajat(){
        String nume = getString("numele angajatului");
        String prenume = getString("prenumele angajatului");
        String cnp = getString("CNP-ul angajatului");
        String username = getString("username-ul angajatului");
        Integer salary = getInt("salariul angajatului");
        String salaryUnit = getString("unitatea salariului");
        Angajat existent = getAngajatByUsername(username);
        if(existent != null){
            System.out.println("Usernameul exista deja!");
            return;
        }
        Angajat ang = new Angajat(nume, prenume, cnp, username, salary, salaryUnit, null, null, null);
        int st, dr, mij;
        st = 0;
        dr = Angajati.size() - 1;
        while (st <= dr) {
            mij = (st + dr) / 2;
            if (Angajati.get(mij).compareTo(ang) < 0) {
                st = mij + 1;
            } else {
                dr = mij - 1;
            }
        }
        Angajati.add(st, ang);
        db.addAngajat(ang);

    }

    public void angajeazaLaHotel(){
        String username = getString("username-ul angajatului");
        Angajat angajat = getAngajatByUsername(username);
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(angajat != null && hotel != null){
            int salariu = getInt("salariul angajatului");
            angajat.setSalariu(salariu);
            String unitate = getString("unitatea salariului");
            angajat.setUnitateSalariu(unitate);
            LocalDate dataAngajare = LocalDate.now();
            Angajari.add(new Angajare(angajat, hotel, dataAngajare));
            db.angajeazaLaHotel(angajat, hotel, dataAngajare);
            System.out.println("Angajatul a fost angajat cu succes!");
            return;
        }
        System.out.println("Nu s-a putut angaja angajatul!");
    }

    public void demisioneazaDeLaHotel(){
        String username = getString("username-ul angajatului");
        Angajat angajat = getAngajatByUsername(username);
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(angajat != null && hotel != null){
            Angajare angajare = getAngajare(username, nume);
            if(angajare != null){
                Angajari.remove(angajare);
                db.demisioneazaDeLaHotel(angajat, hotel);
                System.out.println("Angajatul a fost demis cu succes!");
                return;
            }
        }
        System.out.println("Nu s-a putut demite angajatul!");
    }

    public void getInfoHotels() {
        auditService.logAction("getInfoHotels");
        for(Hotel hotel : Hotels){
            System.out.println(hotel);
        }
    }

    public void getInfoClienti() {
        auditService.logAction("getInfoClienti");
        for(Client client : Clienti){
            System.out.println(client);
        }
    }

    public void getInfoAngajati() {
        auditService.logAction("getInfoAngajati");
        for(Angajat angajat : Angajati){
            System.out.println(angajat);
        }
    }

    public void getInfoHotel(){
        auditService.logAction("getInfoHotel");
        boolean ok = false;
        String nume = getString("numele hotelului");
        for (Hotel hotel : Hotels) {
            if(hotel.getNume().equals(nume)){
                System.out.println(hotel);
                ok = true;
            }
        }
        if(!ok)
            System.out.println("Nu exista hotel cu numele " + nume);
    }

    public void getInfoPersoana(){
        auditService.logAction("getInfoPersoana");
        boolean ok = false;
        String username = getString("username-ul persoanei");
        for (Client client : Clienti) {
            if(client.getUsername().equals(username)){
                ok = true;
                System.out.println(client);
            }
        }
        for (Angajat angajat : Angajati) {
            if(angajat.getUsername().equals(username)){
                ok = true;
                System.out.println(angajat);
            }
        }
        if(!ok)
            System.out.println("Nu exista persoana cu username-ul " + username);
    }

    public void deleteAngajat(){
        auditService.logAction("deleteAngajat");
        String username = getString("username-ul angajatului");
        for (Angajat angajat : Angajati) {
            if(angajat.getUsername().equals(username)){
                Angajati.remove(angajat);
                db.deleteAngajat(angajat);
                return;
            }
        }
        System.out.println("Nu exista persoana cu username-ul " + username);
    }

    public void deleteHotel(){
        auditService.logAction("deleteHotel");
        String nume = getString("numele hotelului");
        for (Hotel hotel : Hotels) {
            if(hotel.getNume().equals(nume)){
                Hotels.remove(hotel);
                db.deleteHotel(hotel);
                return;
            }
        }
        System.out.println("Nu exista hotel cu numele " + nume);
    }

    public void deleteClient(){
        auditService.logAction("deleteClient");
        String username = getString("username-ul clientului");
        for (Client client : Clienti) {
            if(client.getUsername().equals(username)){
                Clienti.remove(client);
                db.deleteClient(client);
                return;
            }
        }
        System.out.println("Nu exista persoana cu username-ul " + username);
    }

    public void addCamera(){
        auditService.logAction("addCamera");
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(hotel != null){
            String numar = getString("numarul camerei");
            if(hotel.cautaCamera(numar) != null){
                System.out.println("Camera cu numarul " + numar + " exista deja!");
                return;
            }
            int etaj = getInt("etajul camerei");
            int nrLocuri = getInt("numarul de locuri din camera");
            int pret = getInt("pretul camerei");
            String descriere = getString("descrierea camerei");
            Camera camera = new Camera(numar, etaj, nrLocuri, descriere, pret, null);
            hotel.addCamera(camera);
            db.addCamera(camera, hotel);
            System.out.println("Camera a fost adaugata cu succes!");
            return;
        }
        System.out.println("Nu s-a putut adauga camera!");
    }

    public void rezervaCamera(){
        auditService.logAction("rezervaCamera");
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(hotel != null){
            String numar = getString("numarul camerei");
            Camera camera = hotel.cautaCamera(numar);
            if(camera != null){
                System.out.println("Camera nu este disponibila in urmatoarele zile:");
                camera.getDisponibilitate();
                System.out.println("Introduceti datele pentru rezervare:");
                String dataInceput = getString("data de inceput a rezervarii");
                String dataSfarsit = getString("data de sfarsit a rezervarii");
                if(!camera.eDisponibil(dataInceput, dataSfarsit)){
                    System.out.println("Camera nu este disponibila in aceste zile!");
                    return;
                }
                String username = getString("username-ul clientului");
                Client client = getClientByUsername(username);
                if(client != null){
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                    Rezervare rezervare = new Rezervare(client, camera, hotel, LocalDate.parse(dataInceput, formatter), LocalDate.parse(dataSfarsit, formatter), false);
                    camera.setDisponibilitateFalse(dataInceput, dataSfarsit);
                    db.setDisponibilitate(camera.getNumar(), hotel.getNume(), dataInceput, dataSfarsit, false);
                    Rezervari.add(rezervare);
                    db.addRezervare(rezervare);
                    System.out.println("Camera a fost rezervata cu succes!");
                    return;
                }
            }
        }
        System.out.println("Nu s-a putut rezerva camera!");
    }

    public void anuleazaRezervare(){
        auditService.logAction("anuleazaRezervare");
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(hotel != null){
            String numar = getString("numarul camerei");
            Camera camera = hotel.cautaCamera(numar);
            if(camera != null){
                String username = getString("username-ul clientului");
                Client client = getClientByUsername(username);
                if(client != null){
                    Rezervare rezervare = getRezervare(username, nume, numar);
                    if(rezervare != null){
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                        camera.setDisponibilitateTrue(rezervare.getCheckInDate().format(formatter), rezervare.getCheckOutDate().format(formatter));
                        db.setDisponibilitate(camera.getNumar(), hotel.getNume(), rezervare.getCheckInDate().format(formatter), rezervare.getCheckOutDate().format(formatter), true);
                        Rezervari.remove(rezervare);
                        db.removeRezervare(rezervare);
                        System.out.println("Rezervarea a fost anulata cu succes!");
                        return;
                    }
                }
            }
        }
        System.out.println("Nu s-a putut anula rezervarea!");
    }

    public void checkIn(){
        auditService.logAction("checkIn");
        Rezervare rezervare = getRezervareInput();
        if(rezervare != null){
            rezervare.checkIn();
            db.checkIn(rezervare);
        }
    }

    public void checkOut(){
        auditService.logAction("checkOut");
        Rezervare rezervare = getRezervareInput();
        if(rezervare != null){
            rezervare.checkOut();
            db.checkOut(rezervare);
        }
    }

    public void modificareCheckInDate(){
        auditService.logAction("modificareCheckInDate");
        Rezervare rezervare = getRezervareInput();
        if(rezervare != null){
            String data = getString("data noua de check-in");
            rezervare.modificareCheckInDate(LocalDate.parse(data));
            db.changeCheckInDate(rezervare, LocalDate.parse(data));
        }
    }

    public void modificareCheckOutDate(){
        auditService.logAction("modificareCheckOutDate");
        Rezervare rezervare = getRezervareInput();
        if(rezervare != null){
            String data = getString("data noua de check-out");
            rezervare.modificareCheckOutDate(LocalDate.parse(data));
            db.changeCheckOutDate(rezervare, LocalDate.parse(data));
        }
    }

    public void getServiciiHotel(){
        auditService.logAction("getServiciiHotel");
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(hotel != null){
            var servicii = hotel.getServicii();
            for(Serviciu serviciu : servicii){
                System.out.println(serviciu);
            }
        }
    }

    public void afisareCamereLibereHotel(){
        auditService.logAction("afisareCamereLibereHotel");
        String nume = getString("numele hotelului");
        Hotel hotel = getHotelByName(nume);
        if(hotel != null){
            String dataInceput = getString("data de inceput a rezervarii");
            String dataSfarsit = getString("data de sfarsit a rezervarii");
            System.out.println("Camerele libere sunt:");
            hotel.camereLibere(dataInceput, dataSfarsit);
        }
        else {
            System.out.println("Nu exista hotel cu numele " + nume);
        }
    }

    public ServiceMain(ArrayList<Hotel> hotels, ArrayList<Client> clienti, ArrayList<Angajat> angajati, ArrayList<Rezervare> rezervari, ArrayList<Angajare> angajari, DatabaseQueries DB) {
        Hotels = hotels;
        Clienti = clienti;
        Angajati = angajati;
        Collections.sort(Angajati);
        Rezervari = rezervari;
        Angajari = angajari;
        db = DB;
    }
}
