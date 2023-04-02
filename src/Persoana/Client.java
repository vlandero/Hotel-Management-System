package Persoana;

public class Client extends Persoana{
    private String Telefon;

    public String getTelefon() {
        return Telefon;
    }

    @Override
    public String toString() {
        return "Client {" +
                ", Telefon='" + Telefon + '\'' +
                ", Nume='" + Nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
    }

    public Client(String nume, String prenume, String cnp, String telefon, String username) {
        super(nume, prenume, cnp, username);
        Telefon = telefon;
    }
}
