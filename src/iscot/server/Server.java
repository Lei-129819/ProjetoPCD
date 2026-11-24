package iscot.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){

        int port=8080;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Servidor ISCOT iniciado na porta" + serverSocket + "a espera de ligações");

            DeviceIdGenerator idGenerator= new DeviceIdGenerator();

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Servidor] Novo dispositivo ligado a partir de: " + clientSocket.getRemoteSocketAddress());

                ClientHandle clientHandle = new ClientHandle(clientSocket, idGenerator);
                new Thread(clientHandle).start();
            }
        } catch (IOException e){
            System.err.println("Servidor Erro critico no server Socket" + e.getMessage());
        }
    }
}
