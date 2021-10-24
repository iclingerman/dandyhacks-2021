import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Player {
    private BattleBoard board;
    private BattleBoard oppBoard;
    protected String name;
    protected String oppName;
    protected Socket socket; 
    protected DataInputStream in; 
    protected DataOutputStream out;

    public Player(String name) {
        this.name = name;
        this.oppName = "";
        this.board = new BattleBoard();
        //TODO add code to set up board
        this.oppBoard = new BattleBoard();
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
            String response = processMove(message);
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
        Scanner scan = new Scanner(System.in);
        // scan.nextLine();
        // do {
        //     System.out.println();
        //     if(!move.equals("")) {
        //         System.out.println("Invalid Move");
        //     }
        //     System.out.print("Please enter your move: ");
        //     // move = scan.nextLine();
            if (scan.hasNext()) {
                move = scan.next();
            }
        //     System.out.println();  
        //     // scan.nextLine();
        // } while (!validMove(move));
        scan.close();
        
        return move;
    }

    public boolean validMove(String move) {
        if(move.equals("")) {
            return false;
        }
        int[] check = oppBoard.stringToCoord(move);
        if (check[0] == -1) {
            return false;
        }
        return true;
    }

    //return 'hit' or 'miss'
    public String processMove(String move) {
        return "hit";
        //TODO add hit or miss function in battle board class and updates the two boards
    }

}
