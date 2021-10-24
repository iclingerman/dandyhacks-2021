import java.net.*;
import java.io.*;

public class Server extends Player{
    private InetAddress ip; 
    private ServerSocket server; 

    public Server(String name) {
        super(name);
        try {
            this.ip = InetAddress.getLocalHost();
            this.server = new ServerSocket(0, 1, ip);
            System.out.printf("You have created a server at %s on port %d\n", ip.getHostAddress(), server.getLocalPort());
            System.out.println("Waiting for connection...");
        
            this.socket = server.accept();
            updatePlayerDataStreams(); //this must be called here so that in and out are correctly initialized
            String oppName = in.readUTF();
            System.out.println(oppName + " connected from " + this.socket.getInetAddress() + ":" + this.socket.getPort());
            out.writeUTF(name);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        for (int i = 0; i < 5; i++) {
            receiveMove();
            sendMove();
            // String move = "";
            // try {
            //     // System.out.print("Please enter your move: ");
            //     move = getMove();
            //     out.writeUTF(move);
            //     String response = in.readUTF();
            //     System.out.println("Your move was a " + response);
            //     // System.out.println();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }

        // System.out.println("Waiting for opponents move...");
        // String message = "";
        // try {
        //     message = in.readUTF();
        //     System.out.println("Opponent's move: " + message);
        //     String response = processMove(message);
        //     out.writeUTF(response);
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // String move = "";
        // try {
        //     // System.out.print("Please enter your move: ");
        //     move = getMove();
        //     out.writeUTF(move);
        //     String response = in.readUTF();
        //     System.out.println("Your move was a " + response);
        //     // System.out.println();
        // } catch (IOException e1) {
        //     e1.printStackTrace();
        // }

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeConnection();
        
    }


    public static void main(String[] args) {
        Server s = new Server("Ian");
    }
}
