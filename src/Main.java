import Camera.Camera;
import Hotel.Hotel;
import Persoana.Persoana;
import Serviciu.Serviciu;
import Persoana.Client;
import Serviciu.Masaj;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Persoana client1 = new Client("Ionescu", "Maria", "1234567890123", "0722222222", "mariaionescu");
        Persoana client2 = new Client("Popescu", "Ion", "2345678901234", "0733333333", "ionpopescu");
        Persoana client3 = new Client("Georgescu", "Andrei", "3456789012345", "0744444444", "andrei_georgescu");
        Persoana client4 = new Client("Dumitru", "Ana", "4567890123456", "0755555555", "anadumitru");
        Persoana client5 = new Client("Popa", "George", "5678901234567", "0766666666", "george_popa");
        Persoana client6 = new Client("Andrei", "Alexandra", "6789012345678", "0777777777", "alexandraandrei");
        Persoana client7 = new Client("Radu", "Mihai", "7890123456789", "0788888888", "mihairadu");
        Persoana client8 = new Client("Stefan", "Andreea", "8901234567890", "0799999999", "andreeastefan");
        Persoana client9 = new Client("Gheorghe", "Cristian", "9012345678901", "0800000000", "cristiangheorghe");
        Persoana client10 = new Client("Munteanu", "Maria", "0123456789012", "0811111111", "mariamunteanu");
        Persoana client11 = new Client("Alexandrescu", "Dragos", "1234567890123", "0822222222", "dragos_alexandrescu");
        Persoana client12 = new Client("Constantinescu", "Andrei", "2345678901234", "0833333333", "andrei_constantinescu");
        Persoana client13 = new Client("Petrescu", "Ioana", "3456789012345", "0844444444", "ioanapetrescu");
        Camera camera1 = new Camera("1", 1, 1, "", 100);
        Camera camera2 = new Camera("2", 1, 1, "", 100);
        Camera camera3 = new Camera("3", 1, 1, "", 100);
        Camera camera4 = new Camera("4", 2, 1, "", 150);
        Camera camera5 = new Camera("5", 2, 1, "", 150);
        Camera camera6 = new Camera("6", 2, 1, "", 150);
        Camera camera7 = new Camera("7", 2, 2, "", 200);
        Camera camera8 = new Camera("8", 2, 2, "", 200);
        Camera camera9 = new Camera("9", 2, 2, "", 200);
        Camera camera10 = new Camera("10", 3, 2, "", 250);
        Camera camera11 = new Camera("11", 3, 2, "", 250);
        Camera camera12 = new Camera("12", 3, 2, "", 250);
        Camera camera13 = new Camera("13", 3, 3, "", 300);
        Camera camera14 = new Camera("14", 3, 3, "", 300);
        Camera camera15 = new Camera("15", 3, 3, "", 300);
        Camera camera16 = new Camera("16", 4, 3, "", 350);
        Camera camera17 = new Camera("17", 4, 3, "", 350);
        Camera camera18 = new Camera("18", 4, 3, "", 350);
        Camera camera19 = new Camera("19", 4, 4, "", 400);
        Camera camera20 = new Camera("20", 4, 4, "", 400);
        Camera camera21 = new Camera("21", 4, 4, "", 400);
        Camera camera22 = new Camera("22", 5, 4, "", 450);
        Camera camera23 = new Camera("23", 5, 4, "", 450);
        Camera camera24 = new Camera("24", 5, 4, "", 450);
        Camera camera25 = new Camera("25", 5, 5, "", 500);
        ArrayList<Camera> camere1 = new ArrayList<Camera>();

        Serviciu serviciu1 = new Masaj("Masaj Thailandez", "Masaj Thailandez misto", 100, "O ora", "Relaxare");
        Hotel hotel1 = new Hotel("Royals", "Bucuresti",new ArrayList<Camera>(), new ArrayList<Serviciu>());
        hotel1.addCamera(camera1);
        hotel1.addCamera(camera2);
        hotel1.addCamera(camera3);
        hotel1.addCamera(camera4);
        hotel1.addCamera(camera5);
    }
}