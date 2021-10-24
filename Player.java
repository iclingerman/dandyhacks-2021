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

    protected boolean myTurn;

    private Scanner scan;

    public Player(String name, boolean myTurn) {
        this.name = name;
        this.oppName = "";
        this.myTurn = myTurn;
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
            message = in.readUTF(); //receive move from other player
            System.out.println("Opponent's move: " + message);
            String[] response = processOpponentMove(message);
            if (response[0].contains("Miss")) {
                myTurn = true;
            }
            out.writeUTF(response[0]);
            out.writeUTF(response[1]);
            out.writeUTF(response[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMove() {
        String move = "";
        try {
            move = getMove();
            out.writeUTF(move); //send move to other player
            String response = in.readUTF();
            String hits = in.readUTF();
            String miss = in.readUTF();
            System.out.println("Your move was a " + response);
            if(!response.contains("Hit")) {
                myTurn = false;
            }
            processPlayerMove(move, response, hits, miss);
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
    public String[] processOpponentMove(String move) {
        String[] rval = new String[3];
        int[] coords = game.board.stringToCoord(move);
        int[] temp = new int[2];
        temp[0] = coords[1];
        temp[1] = coords[0];
        int option = game.board.updateBoard(temp);
        switch(option) {
            case 0:
                rval[0] = "Miss";
                break;
            case 1:
                rval[0] = "Hit";
                break;
            case 2:
                rval[0] = "Hit:Sink";
                break;
            default:
                rval[0] = "Miss";
        }
        rval[1] = game.board.hits;
        rval[2] = game.board.miss;
        return rval;
    }

    public void processPlayerMove(String move, String response, String hits, String miss) {
        int[] coords = game.oppBoard.stringToCoord(move);
        // game.oppBoard.updateBoard(coords);
        if (response.equals("Miss")) {
            game.oppBoard.setValue(coords[1], coords[0], 3);
        } else if (response.equals("Hit")) {
            game.oppBoard.setValue(coords[1], coords[0], 2);
        } else if (response.equals("Hit:Sink")) {
            String[] hitArray = hits.split(" ");
            String[] missArray = miss.split(" ");
            for (int i = 0; i < hitArray.length; i++) {
                int a = hitArray[i].charAt(0) - '0';
                int b = hitArray[i].charAt(1) - '0';
                game.oppBoard.setValue(a, b, 2);
            }
            for (int i = 0; i < missArray.length; i++) {
                int a = missArray[i].charAt(0) - '0';
                int b = missArray[i].charAt(1) - '0';
                game.oppBoard.setValue(a, b, 3);
            }
            
        }

        //Don't think we need to return anything here
    }

}
