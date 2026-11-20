package activite42.clientPackage;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();

        DatagramSocket socket = new DatagramSocket();
        InetAddress adresse = InetAddress.getByName("localhost");

        Thread reception = new Thread(() -> {
            try {
                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(paquet);
                    System.out.println(new String(paquet.getData(), 0, paquet.getLength()));
                }
            } catch (Exception e) {
                System.out.println("Réception terminée");
            }
        });
        reception.start();

        String bienvenue = "[" + nom + "] a rejoint le chat";
        byte[] d = bienvenue.getBytes();
        socket.send(new DatagramPacket(d, d.length, adresse, 1234));

        while (true) {
            String texte = sc.nextLine();
            if (texte.equals("quit")) {
                break;
            }
            String message = "[" + nom + "] : " + texte;
            byte[] donnees = message.getBytes();
            socket.send(new DatagramPacket(donnees, donnees.length, adresse, 1234));
        }
        socket.close();
        System.exit(0);
    }
}