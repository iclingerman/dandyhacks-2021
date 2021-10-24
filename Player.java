import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Player {
    // private BattleBoard board;
    // private BattleBoard oppBoard;
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
        // this.board = new BattleBoard();
        //TODO add code to set up board
        // this.oppBoard = new BattleBoard();
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
            // System.out.print("Please enter your move: ");
            move = getMove();
            processPlayerMove(move);
            out.writeUTF(move);
            String response = in.readUTF();
            System.out.println("Your move was a " + response);
            // System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getMove() {
        String move = "";
        // Scanner scan = new Scanner(System.in);
        // scan.nextLine();
        do {
            System.out.println();
            if(!move.equals("")) {
                System.out.println("Invalid Move");
            }
            System.out.print("Please enter your move: ");
            move = scan.nextLine();
            // if (scan.hasNext()) {
            //     move = scan.next();
            // }
            System.out.println();  
            // scan.nextLine();
        } while (!validMove(move));
        // scan.close();
        
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
        //TODO add hit or miss function in battle board class and updates the two boards
    }

    public void processPlayerMove(String move) {
        int[] coords = game.oppBoard.stringToCoord(move);
        game.oppBoard.updateBoard(coords);
        //Don't think we need to return anything here
    }

}
