import java.net.*;
import java.io.*;

public class Server extends Player{
    private InetAddress ip; 
    private ServerSocket server; 

    public Server(String name) {
        super(name, false);
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
            e.printStackTrace();
        }

        
        while (!isOver) {
            if(myTurn) {
                sendMove();
                game.board.printBothBoards(game.oppBoard);
            }else {
                receiveMove();
                game.board.printBothBoards(game.oppBoard);
            }
        }


        if (this.winner) {
            System.out.println("Congrats! You Win!");
        } else {
            System.out.println(this.oppName + " Wins. Better luck next time");
        }


        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeConnection();
        
    }
}
