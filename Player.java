import java.util.Scanner;

public class Player {
    private BattleBoard board;
    private BattleBoard oppBoard;
    protected String name;

    public Player(String name) {
        this.name = name;
        this.board = new BattleBoard();
        //TODO add code to set up board
        this.oppBoard = new BattleBoard();
    }


    public String getMove() {
        String move = "";
        Scanner scan = new Scanner(System.in);
        do {
            if(!move.equals("")) {
                System.out.println("Invalid Move");
            }
            System.out.print("Please enter your move: ");
            move = scan.nextLine();
            // System.out.println();
        } while (!validMove(move));
        scan.close();
        
        return move;
    }

    public boolean validMove(String move) {
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
