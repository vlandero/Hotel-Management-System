import Hotel.Hotel;
import ManyToMany.Angajare;
import ManyToMany.Rezervare;
import Persoana.Angajat;
import Persoana.Client;
import Persoana.Persoana;

import java.util.ArrayList;

public class ServiceMain {

    private ArrayList<Hotel> Hotels;
    private ArrayList<Client> Clienti;
    private ArrayList<Angajat> Angajati;
    private ArrayList<Rezervare> Rezervari;
    private ArrayList<Angajare> Angajari;

    public void addHotel(Hotel hotel){
        Hotels.add(hotel);
    }

    public void addClient(Client client){
        Clienti.add(client);
    }

    public void addAngajat(Angajat angajat){
        Angajati.add(angajat);
    }

    public ArrayList<Hotel> getHotels() {
        return Hotels;
    }

    public ArrayList<Client> getClienti() {
        return Clienti;
    }

    public ArrayList<Angajat> getAngajati() {
        return Angajati;
    }

    public Hotel getHotel(String nume){ // cauta hotel dupa nume
        for (Hotel hotel : Hotels) {
            if(hotel.getNume().equals(nume)){
                return hotel;
            }
        }
        return null;
    }

    public Persoana getPersoana(String username){ // cauta persoana dupa usernane
        for (Client client : Clienti) {
            if(client.getUsername().equals(username)){
                return client;
            }
        }
        for (Angajat angajat : Angajati) {
            if(angajat.getUsername().equals(username)){
                return angajat;
            }
        }
        return null;
    }

    public ServiceMain(ArrayList<Hotel> hotels, ArrayList<Client> clienti, ArrayList<Angajat> angajati) {
        Hotels = hotels;
        Clienti = clienti;
        Angajati = angajati;
    }

    public void run(){

    }
}
