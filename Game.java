import java.util.Scanner;
import java.util.*;
public class Game {

    public Game(){

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
