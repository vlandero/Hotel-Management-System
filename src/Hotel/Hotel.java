package Hotel;

import Camera.Camera;
import Serviciu.Serviciu;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String Nume;
    private String Adresa;
    private ArrayList<Camera> Camere;
    private ArrayList<Serviciu> Servicii;

    public ArrayList<Serviciu> getServicii() {
        return Servicii;
    }

    public String getNume() {
        return Nume;
    }

    public void camereLibere(String d1, String d2) {
        for (Camera camera : Camere) {
            if (camera.eDisponibil(d1, d2)) {
                System.out.println(camera);
            }
        }
    }

    public void addCamera(Camera camera) {
        Camere.add(camera);
    }

    public void addServiciu(Serviciu serviciu) {
        Servicii.add(serviciu);
    }

    public void prezentareServicii() {
        for (Serviciu serviciu : Servicii) {
            System.out.println(serviciu);
        }
    }


    public Hotel(String Nume, String Adresa, ArrayList<Camera> Camere, ArrayList<Serviciu> Servicii) {
        this.Nume = Nume;
        this.Adresa = Adresa;
        this.Camere = Camere;
        this.Servicii = Servicii;
    }
}
