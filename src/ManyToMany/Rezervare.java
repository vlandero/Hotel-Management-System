package ManyToMany;
import Hotel.Hotel;
import Persoana.Persoana;
import Serviciu.Serviciu;
import Camera.Camera;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rezervare extends ManyToMany{
    private LocalDate CheckInDate;
    private LocalDate CheckOutDate;
    private boolean CheckedIn;
    private ArrayList<Serviciu> ServiciiCumparate;
    private Camera Camera;

    void achizitioneazaServiciu(Serviciu s){
        if(Hotel.getServicii().contains(s)){
            ServiciiCumparate.add(s);
            System.out.println("Serviciul a fost achizitionat");
            return;
        }
        System.out.println("Hotelul nu ofera acest serviciu");
    }

    void checkIn(){
        if(!CheckedIn){
            CheckedIn = true;
            System.out.println("CheckIn efectuat");
            return;
        }
        System.out.println("CheckIn deja efectuat");
    }

    void checkOut(){
        if(CheckedIn){
            CheckedIn = false;
            System.out.println("CheckOut efectuat");
            return;
        }
        System.out.println("CheckOut deja efectuat");
    }

    public void modificareCheckInDate(LocalDate checkInDate) {
        if(CheckedIn){
            System.out.println("CheckIn deja efectuat");
            return;
        }
        if(checkInDate.isAfter(CheckOutDate)){
            System.out.println("Data de checkIn nu poate fi dupa data de checkOut");
            return;
        }
        if(Camera.eDisponibil(checkInDate.toString(), CheckOutDate.toString())){
            CheckInDate = checkInDate;
            System.out.println("Data de checkIn a fost modificata");
            return;
        }
    }

    public void modificareCheckOutDate(LocalDate checkOutDate) {
        //verifica daca data de check out e inainte de check in
        if(CheckedIn){
            System.out.println("CheckIn deja efectuat");
            return;
        }
        if(checkOutDate.isBefore(CheckInDate)){
            System.out.println("Data de checkOut nu poate fi inainte de data de checkIn");
            return;
        }
        if(Camera.eDisponibil(CheckInDate.toString(), checkOutDate.toString())){
            CheckOutDate = checkOutDate;
            System.out.println("Data de checkOut a fost modificata");
            return;
        }
        System.out.println("Camera nu este disponibila in aceasta perioada");
    }
    public Rezervare(Persoana persoana, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate) {
        super(persoana, hotel);
        ServiciiCumparate = new ArrayList<Serviciu>();
        CheckedIn = false;
        CheckInDate = checkInDate;
        CheckOutDate = checkOutDate;
        modificareCheckInDate(checkInDate);
    }
}
