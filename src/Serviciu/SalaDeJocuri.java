package Serviciu;

import java.util.List;

public class SalaDeJocuri extends Serviciu{
    private List<String> Jocuri;

    public String toString() {
        return "SalaDeJocuri: " + Nume + " " + Descriere + " " + Pret + " " + Durata + " " + Jocuri;
    }
    public SalaDeJocuri(String Nume, String Descriere, int Pret, String Durata, List<String> Jocuri) {
        super(Nume, Descriere, Pret, Durata);
        this.Jocuri = Jocuri;
    }
}
