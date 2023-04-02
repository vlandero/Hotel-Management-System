package Serviciu;

public abstract class Serviciu {
    protected String Nume;
    protected String Descriere;
    protected int Pret;
    protected String Durata;

    public String getNume() {
        return Nume;
    }

    public String getDescriere() {
        return Descriere;
    }

    public int getPret() {
        return Pret;
    }

    public String getDurata() {
        return Durata;
    }

    public abstract String toString();

    public Serviciu(String Nume, String Descriere, int Pret, String Durata) {
        this.Nume = Nume;
        this.Descriere = Descriere;
        this.Pret = Pret;
        this.Durata = Durata;
    }
}
