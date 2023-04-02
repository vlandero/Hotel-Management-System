package ManyToMany;

import Hotel.Hotel;
import Persoana.Persoana;

public abstract class ManyToMany {
    protected Persoana Persoana;
    protected Hotel Hotel;

    public ManyToMany(Persoana persoana, Hotel hotel) {
        this.Persoana = persoana;
        this.Hotel = hotel;
    }
}
