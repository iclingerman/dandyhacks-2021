import java.net.*;
import java.util.Scanner;
import java.io.*; 

public class Client {
    private String ip; 
    private int port; 
    private Socket socket; 
    private Scanner scan; 
    private DataOutputStream out; 
    private DataInputStream in; 

    public Client(String ip, int port) {
        this.ip = ip; 
        this.port = port;
        try {
            this.socket = new Socket(this.ip, this.port);
            this.scan = new Scanner(System.in);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        
        System.out.println("Waiting for opponents move...");
        String message = "";
        try {
            message = in.readUTF();
            System.out.println("Opponent's move: " + message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        //game loop
        // String message = "";
        // while(!message.equals("quit")) {
        //     try {
        //         message = scan.nextLine();
        //         System.out.println("message: " + message);
        //         out.writeUTF(message);
        //     } catch (IOException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }

        try {
            scan.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public static void main(String[] args) {
        Client c = new Client("172.28.176.1", 5050);
    }
}
