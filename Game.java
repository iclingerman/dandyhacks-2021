import java.util.Scanner;
import java.util.*;
public class Game {
    public BattleBoard board;
    public BattleBoard oppBoard;

    public Game(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to randomize your board? (y/n)");
        String answer = scan.nextLine();
        if (answer.equals("y")){
            board.shipAdd();
        }else{
            board.shipRandom();
        }
    }

    public void shipSetUp(){
        Scanner scan = new Scanner(System.in);
        BattleBoard board = new BattleBoard();
        BattleBoard board2 = new BattleBoard();
        // board.shipAdd();
        board.shipRandom();
        board2.shipRandom();
        board.printBoard();
        System.out.println();
        board.printBothBoards(board2);
    }


    public static void main(String args[]){
        // BattleBoard test = new BattleBoard();
        // BattleBoard opp = new BattleBoard();
        // test.printBothBoards(opp);
        Game test = new Game();

        test.shipSetUp();
    }
}
