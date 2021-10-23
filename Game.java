import java.util.Scanner;

public class Game {

    public Game(){

    }

    public void shipSetUp(){
        Scanner scan = new Scanner(System.in);
        BattleBoard test = new BattleBoard();
        System.out.println("This is your board:");
        test.printBoard();
        System.out.println("Please enter where you want the head of the Carrier ship (length 5)");
        String carrierInput = scan.nextLine();
        String[] carrierCheck = carrierInput.split(" ");
        boolean carrier = checkValid(carrierCheck, 5, test);
        while(!carrier){
            System.out.println("Invalid Ship Placement");
            System.out.println("Please enter where you want the head of the Carrier ship (length 5)");
            carrierInput = scan.nextLine();
            carrierCheck = carrierInput.split(" ");
            carrier = checkValid(carrierCheck, 5, test);
        }
    }

    public void shipAdd(BattleBoard board){
        Scanner scan = new Scanner(System.in);
        int[] shipLengths = new int[]{5,4,3,3,2};
        for (int i=0; i <shipLengths.length; i++){
            System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
            String input = scan.nextLine();
            String[] check = input.split(" ");
            boolean carrier = checkValid(check, shipLengths[i], board);
            while(!carrier){
                System.out.println("Invalid Ship Placement");
                System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
                input = scan.nextLine();
                check = input.split(" ");
                carrier = checkValid(check, shipLengths[i], board);
            }
        }
    }

    public boolean checkValid(String[] input, int length, BattleBoard board){
        String orientation = input[1];
        int[] coords = board.stringToCoord(input[0]);
        System.out.println("row = " + coords[0]);
        System.out.println("column = " + coords[1]);
        System.out.println("ord " + orientation);
        if (orientation.contains("v")){
            if (coords[0]+length>8 ){
                return false;
            }
            for (int i = coords[0]; i<(length + coords[0]); i++){
                if (board.getValue(i,coords[1]) == 1){
                    return false;
                }
            }
        }else{
            if (coords[1]+length>8 ){
                return false;
            }
            for (int i = coords[1]; i<(length + coords[1]); i++){
                if (board.getValue(coords[0],i) == 1){
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
