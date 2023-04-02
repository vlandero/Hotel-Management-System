package Serviciu;

public class Masaj extends Serviciu {
    private String Tip;

    public String getTip() {
        return Tip;
    }

    public String toString() {
        return "Masaj: " + Nume + " " + Descriere + " " + Pret + " " + Durata + " " + Tip;
    }

    public Masaj(String Nume, String Descriere, int Pret, String Durata, String Tip) {

        super(Nume, Descriere, Pret, Durata);
        this.Tip = Tip;
    }
}
