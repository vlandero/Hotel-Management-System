package Persoana;

public abstract class Persoana {
    protected String Username;
    protected String Nume;
    protected String Prenume;
    protected String CNP;

    public String getCNP() {
        return CNP;
    }

    public String getNume() {
        return Nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public String getUsername() {
        return Username;
    }


    public abstract String toString();
    Persoana(String nume, String prenume, String cnp, String username){
        Nume = nume;
        Prenume = prenume;
        CNP = cnp;
        Username = username;
    }
}
