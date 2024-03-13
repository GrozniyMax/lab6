package Managers;

import CommonClasses.Commands.CommandDescription;
import CommonClasses.Interaction.ServerResponse;
import CommonClasses.Interaction.UserCommandRequest;
import CommonClasses.Interaction.UserRequest;
import CommonClasses.Utils.IOUtils;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Set;

public class UDPManager {
    //TODO add chunks separation and collection
    //TODO TEST
    static final String HOSTNAME = "localhost";
    static final int SERVER_PORT=8080;
    private InetSocketAddress serverAddress;
    private int port=8081;
    private DatagramSocket clientSocket;

    public UDPManager() {
        try {
            this.serverAddress = new InetSocketAddress(InetAddress.getLocalHost(),8080);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        boolean clientSocketSet = false;
        while (!clientSocketSet){
            try {
                this.clientSocket = new DatagramSocket(null);
                clientSocket.connect(serverAddress);
                clientSocketSet = true;
            } catch (SocketException e) {
                System.err.println(e.getMessage());
                port++;
            }
        }
    }

    private void sayHelloToServer(){

        byte[] request= IOUtils.toByteArray(new UserCommandRequest(this.clientSocket.getInetAddress()));
        DatagramPacket helloPackage = new DatagramPacket(request, request.length, this.serverAddress);
        try {
            this.clientSocket.send(helloPackage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Приветствие отправлено");
    }

    private LinkedList<CommandDescription> readCommands(){

        byte[] buffer = new byte[16384];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.serverAddress);
        try {
            //TODO add timeout execution
            boolean recieved = false;
            LinkedList<CommandDescription> result = new LinkedList<>();
            System.out.println("Начал ждать команды");
            while (!recieved){
                this.clientSocket.receive(packet);
                result = IOUtils.<LinkedList<CommandDescription>>fromByteArray(packet.getData());
                if (result.size()>0) {
                    recieved = true;
                }
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<CommandDescription> getCommands(){
        sayHelloToServer();
        //wait maybe
        return readCommands();
    }

    public void sendUserRequest(UserRequest request){
        byte[] buffer = IOUtils.toByteArray(request);
        try {
            this.clientSocket.send(new DatagramPacket(buffer,buffer.length));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerResponse getServerResponce(){
        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        //TODO add timeout check
        try {
            ServerResponse response = null;
            while (true){
                this.clientSocket.receive(packet);
                response = IOUtils.<ServerResponse>fromByteArray(packet.getData());
                if (response!=null) {
                    return response;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
