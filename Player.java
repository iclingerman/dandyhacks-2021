import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Player {
    protected Game game;
    protected String name;
    protected String oppName;
    protected Socket socket; 
    protected DataInputStream in; 
    protected DataOutputStream out;

    private Scanner scan;

    public Player(String name) {
        this.name = name;
        this.oppName = "";
        this.scan = new Scanner(System.in);
        this.game = new Game();
        this.socket = null;
        this.in = null;
        this.out = null;
        
    }

    public void updatePlayerDataStreams() {
        try {
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {    
        try {
            this.socket.close();
            this.in.close();
            this.out.close();
            this.scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }


    public void receiveMove() {
        System.out.println("Waiting for opponents move...");
        String message = "";
        try {
            message = in.readUTF();
            System.out.println("Opponent's move: " + message);
            String response = processOpponentMove(message);
            out.writeUTF(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMove() {
        String move = "";
        try {
            move = getMove();
            out.writeUTF(move);
            String response = in.readUTF();
            System.out.println("Your move was a " + response);
            processPlayerMove(move, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getMove() {
        String move = "";
        do {
            System.out.println();
            if(!move.equals("")) {
                System.out.println("Invalid Move");
            }
            System.out.print("Please enter your move: ");
            move = scan.nextLine();
            System.out.println();  
        } while (!validMove(move));       
        return move;
    }

    public boolean validMove(String move) {
        if(move.equals("")) {
            return false;
        }
        int[] check = game.oppBoard.stringToCoord(move);
        if (check[0] == -1) {
            return false;
        }
        return true;
    }

    //return 'hit' or 'miss'
    public String processOpponentMove(String move) {
        int[] coords = game.board.stringToCoord(move);
        int option = game.board.updateBoard(coords);
        switch(option) {
            case 0:
                return "Miss";
            case 1:
                return "Hit";
            case 2:
                return "Hit:Sink";
        }

        return "Miss";
    }

    public void processPlayerMove(String move, String response) {
        int[] coords = game.oppBoard.stringToCoord(move);
        // game.oppBoard.updateBoard(coords);
        if (response.equals("Miss")) {
            game.oppBoard.setValue(coords[0], coords[1], 3);
        } else if (response.equals("Hit")) {
            game.oppBoard.setValue(coords[0], coords[1], 2);
        } else if (response.equals("Hit:Sink")) {

        }

        //Don't think we need to return anything here
    }

}
