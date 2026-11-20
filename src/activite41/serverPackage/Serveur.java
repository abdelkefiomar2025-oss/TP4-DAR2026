package activite41.serverPackage;

import java.net.*;

public class Serveur {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(1234));
        System.out.println("Le serveur attend des messages...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquet);
            String message = new String(paquet.getData(), 0, paquet.getLength());
            System.out.println(paquet.getAddress().getHostAddress() + ":" + paquet.getPort() + " -> " + message);
        }
    }
}