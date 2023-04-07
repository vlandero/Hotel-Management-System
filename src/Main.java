import Camera.Camera;
import Hotel.Hotel;
import Persoana.Persoana;
import Serviciu.Serviciu;
import Persoana.Client;
import Persoana.Angajat;
import Serviciu.Masaj;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Angajat> angajati = new ArrayList<Angajat>();
        ArrayList<Client> clienti = new ArrayList<Client>();
        ArrayList<Hotel> hoteluri = new ArrayList<Hotel>();

        clienti.add(new Client("Ionescu", "Maria", "1234567890123", "0722222222", "mariaionescu"));
        clienti.add (new Client("Popescu", "Ion", "2345678901234", "0733333333", "ionpopescu"));
        clienti.add (new Client("Georgescu", "Andrei", "3456789012345", "0744444444", "andrei_georgescu"));
        clienti.add (new Client("Dumitru", "Ana", "4567890123456", "0755555555", "anadumitru"));
        clienti.add (new Client("Popa", "George", "5678901234567", "0766666666", "george_popa"));
        clienti.add (new Client("Andrei", "Alexandra", "6789012345678", "0777777777", "alexandraandrei"));
        clienti.add (new Client("Radu", "Mihai", "7890123456789", "0788888888", "mihairadu"));
        clienti.add (new Client("Stefan", "Andreea", "8901234567890", "0799999999", "andreeastefan"));
        clienti.add (new Client("Gheorghe", "Cristian", "9012345678901", "0800000000", "cristiangheorghe"));
        clienti.add (new Client("Munteanu", "Maria", "0123456789012", "0811111111", "mariamunteanu"));
        clienti.add (new Client("Alexandrescu", "Dragos", "1234567890123", "0822222222", "dragos_alexandrescu"));
        clienti.add (new Client("Constantinescu", "Andrei", "2345678901234", "0833333333", "andrei_constantinescu"));
        clienti.add (new Client("Petrescu", "Ioana", "3456789012345", "0844444444", "ioanapetrescu"));
        Camera camera1 = new Camera("1", 1, 1, "", 100);
        Camera camera2 = new Camera("2", 1, 1, "", 100);
        Camera camera3 = new Camera("3", 1, 1, "", 100);
        Camera camera4 = new Camera("4", 2, 1, "", 150);
        Camera camera5 = new Camera("5", 2, 1, "", 150);
        Camera camera6 = new Camera("6", 2, 1, "", 150);
        Camera camera7 = new Camera("7", 2, 2, "", 200);
        Camera camera8 = new Camera("8", 2, 2, "", 200);
        Camera camera9 = new Camera("9", 2, 2, "", 200);
        Camera camera10 = new Camera("10", 3, 2, "", 250);
        Camera camera11 = new Camera("11", 3, 2, "", 250);
        Camera camera12 = new Camera("12", 3, 2, "", 250);
        Camera camera13 = new Camera("13", 3, 3, "", 300);
        Camera camera14 = new Camera("14", 3, 3, "", 300);
        Camera camera15 = new Camera("15", 3, 3, "", 300);
        Camera camera16 = new Camera("16", 4, 3, "", 350);
        Camera camera17 = new Camera("17", 4, 3, "", 350);
        Camera camera18 = new Camera("18", 4, 3, "", 350);
        Camera camera19 = new Camera("19", 4, 4, "", 400);
        Camera camera20 = new Camera("20", 4, 4, "", 400);
        Camera camera21 = new Camera("21", 4, 4, "", 400);
        Camera camera22 = new Camera("22", 5, 4, "", 450);
        Camera camera23 = new Camera("23", 5, 4, "", 450);
        Camera camera24 = new Camera("24", 5, 4, "", 450);
        Camera camera25 = new Camera("25", 5, 5, "", 500);
        ArrayList<Camera> camere1 = new ArrayList<Camera>();

        Serviciu serviciu1 = new Masaj("Masaj Thailandez", "Masaj Thailandez misto", 100, "O ora", "Relaxare");
        Hotel hotel1 = new Hotel("Royals", "Bucuresti",new ArrayList<Camera>(), new ArrayList<Serviciu>());
        hotel1.addCamera(camera1);
        hotel1.addCamera(camera2);
        hotel1.addCamera(camera3);
        hotel1.addCamera(camera4);
        hotel1.addCamera(camera5);
        hotel1.addCamera(camera23);
        hoteluri.add(hotel1);
        hotel1.addServiciu(serviciu1);

        ServiceMain serviceMain = new ServiceMain(hoteluri, clienti, angajati);

        System.out.println("Selectati numarul din lista urmatoare pentru a realiza interogarea dorita:");
        System.out.println("1. Afisare hoteluri");
        System.out.println("2. Afisare clienti");
        System.out.println("3. Afisare angajati");
        System.out.println("4. Afisare rezervari client");
        System.out.println("5. Inregistreaza client");
        System.out.println("6. Inregistreaza angajat");
        System.out.println("7. Rezerva camera");
        System.out.println("8. Inregistrare angajat la hotel");
        System.out.println("9. Demisioneaza angajat de la hotel");
        System.out.println("10. Inregistreaza hotel");
        System.out.println("11. Afisare persoana dupa username");
        System.out.println("12. Afisare servicii hotel");
        System.out.println("13. Afisare hotel dupa nume");
        System.out.println("14. Sterge angajat");
        System.out.println("15. Sterge client");
        System.out.println("16. Sterge hotel");
        System.out.println("17. Anuleaza rezervare");
        System.out.println("18. Check-in");
        System.out.println("19. Check-out");
        System.out.println("20. Afisare camere libere");

        Scanner scanner = new Scanner(System.in);
        boolean ok = true;
        while(ok){
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1 -> serviceMain.getInfoHotels();
                case 2 -> serviceMain.getInfoClienti();
                case 3 -> serviceMain.getInfoAngajati();
                case 4 -> serviceMain.getRezervariClient();
                case 5 -> serviceMain.addClient();
                case 6 -> serviceMain.addAngajat();
                case 7 -> serviceMain.rezervaCamera();
                case 8 -> serviceMain.angajeazaLaHotel();
                case 9 -> serviceMain.demisioneazaDeLaHotel();
                case 10 -> serviceMain.addHotel();
                case 11 -> serviceMain.getInfoPersoana();
                case 12 -> serviceMain.getServiciiHotel();
                case 13 -> serviceMain.getInfoHotel();
                case 14 -> serviceMain.deleteAngajat();
                case 15 -> serviceMain.deleteClient();
                case 16 -> serviceMain.deleteHotel();
                case 17 -> serviceMain.anuleazaRezervare();
                case 18 -> serviceMain.checkIn();
                case 19 -> serviceMain.checkOut();
                case 20 -> serviceMain.afisareCamereLibereHotel();
                default -> ok = false;
            }
        }

    }
}