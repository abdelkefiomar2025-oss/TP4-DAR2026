package activite42.serverPackage;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        System.out.println("Le serveur de chat attend des clients...");

        List<SocketAddress> clients = new ArrayList<>();

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquet);

            SocketAddress expediteur = paquet.getSocketAddress();
            String message = new String(paquet.getData(), 0, paquet.getLength());
            System.out.println(expediteur + " -> " + message);

            if (!clients.contains(expediteur)) {
                clients.add(expediteur);
                System.out.println("Nouveau client : " + expediteur);
            }

            byte[] donnees = message.getBytes();
            for (SocketAddress client : clients) {
                if (!client.equals(expediteur)) {
                    DatagramPacket copie = new DatagramPacket(donnees, donnees.length, client);
                    socket.send(copie);
                }
            }
        }
    }
}