package Hotel;

import Camera.Camera;
import Serviciu.Serviciu;

import java.util.*;

public class Hotel {
    private String Nume;
    private String Adresa;
    private Set<Camera> Camere;
    private ArrayList<Serviciu> Servicii;

    public ArrayList<Serviciu> getServicii() {
        return Servicii;
    }

    public String getNume() {
        return Nume;
    }
    public String getAdresa() {
        return Adresa;
    }

    public void camereLibere(String d1, String d2) {
        for (Camera camera : Camere) {
            if (camera.eDisponibil(d1, d2)) {
                System.out.println(camera);
            }
        }
    }

    public Set<Camera> getCamere() {
        return Camere;
    }

    public void addCamera(Camera camera) {
        Camere.add(camera);
    }

    public void removeCamera(Camera camera) {
        Camere.remove(camera);
    }

    public Camera cautaCamera(String numar) {
        for (Camera camera : Camere) {
            if (Objects.equals(camera.getNumar(), numar)) {
                return camera;
            }
        }
        return null;
    }

    public void addServiciu(Serviciu serviciu) {
        Servicii.add(serviciu);
    }

    public void prezentareServicii() {
        for (Serviciu serviciu : Servicii) {
            System.out.println(serviciu);
        }
    }

    public String toString() {
        return "Hotel{" +
                "Nume='" + Nume + '\'' +
                ", Adresa='" + Adresa + '\'' +
                ", Camere=" + Camere +
                ", Servicii=" + Servicii +
                '}';
    }


    public Hotel(String Nume, String Adresa, Set<Camera> Camere, ArrayList<Serviciu> Servicii) {
        this.Nume = Nume;
        this.Adresa = Adresa;
        this.Camere = Camere;
        this.Servicii = Servicii;
    }
}
