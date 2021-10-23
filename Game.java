import java.util.Scanner;

public class Game {

    public Game(){

    }

    public void shipSetUp(){
        Scanner scan = new Scanner(System.in);
        BattleBoard board = new BattleBoard();
        shipAdd(board);
        board.printBoard();
    }

    public void shipAdd(BattleBoard board){
        Scanner scan = new Scanner(System.in);
        int[] shipLengths = new int[]{5,4,3,3,2};
        for (int i=0; i <shipLengths.length; i++){
            System.out.println("This is your board:");
            board.printBoard();
            System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
            String input = scan.nextLine();
            String[] check = input.split(" ");
            boolean valid = checkValid(check, shipLengths[i], board);
            while(!valid){
                System.out.println("Invalid Ship Placement");
                System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
                input = scan.nextLine();
                check = input.split(" ");
                valid = checkValid(check, shipLengths[i], board);
            }
            board = shipPlace(board, check, shipLengths[i]);
        }
    }

    public BattleBoard shipPlace(BattleBoard board, String[] info, int length){
        int[] coords = board.stringToCoord(info[0]);
        if (info[1].contains("v")){
            for (int i = coords[1]; i<(length + coords[1]); i++){
                board.setValue(i, coords[0], 1);
            }
        }else{
            for (int i = coords[0]; i<(length + coords[0]); i++){
                board.setValue(coords[1],i, 1);
            }
        }
        return board;
    }

    public boolean checkValid(String[] input, int length, BattleBoard board){
        String orientation = input[1];
        int[] coords = board.stringToCoord(input[0]);
        System.out.println("row = " + coords[1]);
        System.out.println("column = " + coords[0]);
        System.out.println("ord " + orientation);
        if (orientation.contains("v")){
            if (coords[1]+length>8 ){
                return false;
            }
            for (int i = coords[1]; i<(length + coords[1]); i++){
                if (board.getValue(i,coords[0]) == 1){
                    return false;
                }
            }
        }else{
            if (coords[0]+length>8){
                return false;
            }
            for (int i = coords[0]; i<(length + coords[0]); i++){
                if (board.getValue(coords[1],i) == 1){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String args[]){
        // BattleBoard test = new BattleBoard();
        // BattleBoard opp = new BattleBoard();
        // test.printBothBoards(opp);
        Game test = new Game();
        test.shipSetUp();
    }
}
