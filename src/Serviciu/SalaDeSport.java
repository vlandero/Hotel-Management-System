package Serviciu;

import java.util.List;

public class SalaDeSport extends Serviciu {
    private List<String> Echipamente;

    public String toString() {
        return "SalaDeSport: " + Nume + " " + Descriere + " " + Pret + " " + Durata + " " + Echipamente;
    }
    public SalaDeSport(String Nume, String Descriere, int Pret, String Durata, List<String> Echipamente) {
        super(Nume, Descriere, Pret, Durata);
        this.Echipamente = Echipamente;
    }
}
