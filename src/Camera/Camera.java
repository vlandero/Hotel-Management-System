package Camera;

import java.time.LocalDate;
import java.util.*;

public class Camera implements Comparable<Camera> {
    private String Numar;
    private int Etaj;
    private int PretNoapte;
    private int NumarLocuri;
    private String DescriereExtra;
    Map<String, Boolean> disponibilitate; // stringurile sunt de tipul "yyyy-mm-dd"
    public boolean eDisponibil(String i1, String i2) {
        LocalDate d1 = LocalDate.parse(i1);
        LocalDate d2 = LocalDate.parse(i2);
        for (LocalDate date = d1; date.isBefore(d2); date = date.plusDays(1)) {
            if (!disponibilitate.get(date.toString())) {
                return false;
            }
        }
        return true;
    }

    public void setDisponibilitateFalse(String i1, String i2) {
        LocalDate d1 = LocalDate.parse(i1);
        LocalDate d2 = LocalDate.parse(i2);
        for (LocalDate date = d1; date.isBefore(d2); date = date.plusDays(1)) {
            disponibilitate.put(date.toString(), false);
        }
    }

    public void setDisponibilitateTrue(String i1, String i2) {
        LocalDate d1 = LocalDate.parse(i1);
        LocalDate d2 = LocalDate.parse(i2);
        for (LocalDate date = d1; date.isBefore(d2); date = date.plusDays(1)) {
            disponibilitate.put(date.toString(), true);
        }
    }

    public void getDisponibilitate(){
        for (Map.Entry<String, Boolean> entry : disponibilitate.entrySet()) {
            if(!entry.getValue())
                System.out.println(entry.getKey());
        }
    }

    public String getNumar() {
        return Numar;
    }

    public int getEtaj() {
        return Etaj;
    }

    public int getPretNoapte() {
        return PretNoapte;
    }

    public int getNumarLocuri() {
        return NumarLocuri;
    }

    public void setDescriereExtra(String descriereExtra) {
        DescriereExtra = descriereExtra;
    }

    public String toString() {
        return "Camera {" +
                "Numar=" + Numar +
                ", Etaj=" + Etaj +
                ", PretNoapte=" + PretNoapte +
                ", disponibilitate=" + disponibilitate +
                ", DescriereExtra='" + DescriereExtra + '\'' +
                '}';
    }

    public int compareTo(Camera o) {
        return this.Numar.compareTo(o.Numar);
    }

    public Camera(String numar, int etaj, int numarLocuri, String descriereExtra, int pretNoapte) {
        this.Numar = numar;
        this.Etaj = etaj;
        this.NumarLocuri = numarLocuri;
        this.disponibilitate = Collections.emptyMap();
        this.PretNoapte = pretNoapte;
        this.DescriereExtra = descriereExtra;
    }
}
