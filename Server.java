import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server {
    private InetAddress ip; 
    private int port; 
    private ServerSocket server; 
    private Socket socket; 
    private DataInputStream in; 
    private DataOutputStream out;
    private Scanner scan;

    public Server(int port) {
        try {
            this.port = port;
            this.ip = InetAddress.getLocalHost();
            System.out.println(ip);
            this.server = new ServerSocket(port, 1, ip);
            this.scan = new Scanner(System.in);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("waiting for connection...");
        try {
            this.socket = server.accept();
            System.out.println("Client accepted from " + socket.getInetAddress() + ":" + socket.getPort());
            in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Waiting for opponents move...");
        String message = "";
        try {
            message = in.readUTF();
            System.out.println("Opponent's move: " + message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String move = "";
        try {
            System.out.print("Please enter your move: ");
            move = scan.nextLine();
            out.writeUTF(move);
            System.out.println();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }




        
        // while(!message.equals("quit")) {
        //     try {
        //         message = in.readUTF();
        //         System.out.println(message);
        //     } catch (IOException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
            

        // }

        try {
            socket.close();
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


    public static void main(String[] args) {
        Server s = new Server(5050);
    }
}
