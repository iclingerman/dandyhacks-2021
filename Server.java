import java.net.*;
import java.io.*;

public class Server extends Player{
    private InetAddress ip; 
    private ServerSocket server; 

    public Server(String name) {
        super(name);
        try {
            this.ip = InetAddress.getLocalHost();
            this.server = new ServerSocket(5050, 1, ip); //TODO change back to random port
            System.out.printf("You have created a server at %s on port %d\n", ip.getHostAddress(), server.getLocalPort());
            System.out.println("Waiting for connection...");
        
            this.socket = server.accept();
            updatePlayerDataStreams(); //this must be called here so that in and out are correctly initialized
            String oppName = in.readUTF();
            System.out.println(oppName + " connected from " + this.socket.getInetAddress() + ":" + this.socket.getPort());
            out.writeUTF(name);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        for (int i = 0; i < 5; i++) {
            receiveMove();
            game.board.printBothBoards(game.oppBoard);
            sendMove();
            game.board.printBothBoards(game.oppBoard);
        }

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeConnection();
        
    }
}
