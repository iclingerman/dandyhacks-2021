import java.net.*;
import java.io.*; 

public class Client extends Player{
    private String ip; 
    private int port;  

    public Client(String ip, int port, String name) {
        super(name, true);
        this.ip = ip; 
        this.port = port;
        try {
            this.socket = new Socket(this.ip, this.port);
            updatePlayerDataStreams();
            out.writeUTF(name); //sending name to server
            this.oppName = in.readUTF(); //get name from server
            System.out.println("You have connect to challenge " + oppName);
        }  catch (IOException e) {
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

        closeConnection();

    }
}
