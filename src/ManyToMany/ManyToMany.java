package ManyToMany;

import Hotel.Hotel;
import Persoana.Persoana;

public abstract class ManyToMany {
    protected Persoana Persoana;
    protected Hotel Hotel;

    public Persoana getPersoana() {
        return Persoana;
    }

    public Hotel getHotel() {
        return Hotel;
    }

    public abstract String toString();

    public ManyToMany(Persoana persoana, Hotel hotel) {
        this.Persoana = persoana;
        this.Hotel = hotel;
    }
}
