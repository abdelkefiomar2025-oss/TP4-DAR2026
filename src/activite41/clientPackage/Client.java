package activite41.clientPackage;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();

        DatagramSocket socket = new DatagramSocket();
        InetAddress adresse = InetAddress.getByName("localhost");

        while (true) {
            String texte = sc.nextLine();
            if (texte.equals("quit")) {
                break;
            }
            String message = "[" + nom + "] : " + texte;
            byte[] donnees = message.getBytes();
            DatagramPacket paquet = new DatagramPacket(donnees, donnees.length, adresse, 1234);
            socket.send(paquet);
        }
        socket.close();
    }
}