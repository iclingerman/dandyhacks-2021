import java.util.Scanner;
import java.util.*;
public class Game {
    public BattleBoard board;
    public BattleBoard oppBoard;

    public Game(){
        // board = new BattleBoard();
        // oppBoard = new BattleBoard();
        // Scanner scan = new Scanner(System.in);
        // System.out.println("Would you like to randomize your board? (y/n)");
        // String answer = scan.nextLine();
        // if (answer.equals("n")){
        //     board.shipAdd();
        // }else{
        //     board.shipRandom();
        // }
        // System.out.println("Your Board: ");
        // board.printBoard();

    }

    public void shipSetUp(){
        Scanner scan = new Scanner(System.in);
        BattleBoard board = new BattleBoard();
        BattleBoard board2 = new BattleBoard();
        // board.shipAdd();
        board.shipRandom();
        // board2.shipRandom();
        board.printBoard();
        System.out.println();
        // board.printBothBoards(board2);
        int[] a1 = new int[]{0,0};
        board.updateBoard(a1);
        int[] a2 = new int[]{1,0};
        board.updateBoard(a2);
        int[] a3 = new int[]{2,0};
        board.updateBoard(a3);
        System.out.println("miss " + board.miss);
        System.out.println("hits " + board.hits);
        board.printBoard();
    }


    public static void main(String args[]){
        // BattleBoard test = new BattleBoard();
        // BattleBoard opp = new BattleBoard();
        // test.printBothBoards(opp);
        Game test = new Game();

        test.shipSetUp();
    }
}
