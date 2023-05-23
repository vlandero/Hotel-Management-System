import Camera.Camera;
import Hotel.Hotel;
import ManyToMany.Angajare;
import ManyToMany.Rezervare;
import Persoana.Persoana;
import Serviciu.Serviciu;
import Persoana.Client;
import Persoana.Angajat;
import Serviciu.Masaj;

import javax.xml.crypto.Data;
import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DatabaseQueries db = DatabaseQueries.getInstance();
        ArrayList<Angajat> angajati = db.getAllAngajati();
        ArrayList<Client> clienti = db.getAllClienti();
        ArrayList<Hotel> hoteluri = db.getAllHotels();
        ArrayList<Rezervare> rezervari = db.getAllRezervari(clienti, hoteluri);
        ArrayList<Angajare> angajari = db.getAllAngajari(angajati, hoteluri);

        ServiceMain serviceMain = new ServiceMain(hoteluri, clienti, angajati, rezervari, angajari, db);

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
        System.out.println("21. Adauga camera la hotel");
        System.out.println("22. Modificare data check-in");
        System.out.println("23. Modificare data check-out");

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
                case 21 -> serviceMain.addCamera();
                case 22 -> serviceMain.modificareCheckInDate();
                case 23 -> serviceMain.modificareCheckOutDate();
                default -> ok = false;
            }
        }
    }
}