package ManyToMany;
import Hotel.Hotel;
import Persoana.Persoana;

import java.time.LocalDate;

public class Angajare extends ManyToMany{
    private LocalDate DataAngajare;

    public String toString() {
        return "Angajare{" +
                "DataAngajare=" + DataAngajare +
                ", Persoana=" + Persoana +
                ", Hotel=" + Hotel +
                '}';
    }

    public Angajare(Persoana persoana, Hotel hotel, LocalDate dataAngajare) {
        super(persoana, hotel);
        DataAngajare = dataAngajare;
    }
}
