import java.util.Scanner;
public class Game {
    public BattleBoard board;
    public BattleBoard oppBoard;

    public Game(){
        board = new BattleBoard();
        oppBoard = new BattleBoard();
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to randomize your board? (y/n)");
        String answer = scan.nextLine();
        if (answer.equals("n")){
            board.shipAdd();
        }else{
            board.shipRandom();
        }
        System.out.println("Your Board: ");
        board.printBoard();
        scan.close();
    }

    public boolean gameOver(){
        if (board.remainingShips == 0){
            return true;
        }
        return false;
    }
}
