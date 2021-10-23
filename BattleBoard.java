import java.util.*;
// water = 0
// ship = 1
// hit = 2
// miss = 3
public class BattleBoard {
    private int[][] board;

    public BattleBoard(){
        this.board = new int[8][8];
        fillBoard();
    }

    public void fillBoard(){
        for (int row = 0; row<8; row++){
            for (int column = 0; column<8; column++){
                board[row][column] = 0;
            }
        }
    }

    public void printBoard(){
        System.out.print("  ");
        for (char i = 'a'; i < 'a' + 8; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row<8; row++){
            System.out.print((row+1) + " ");
            for (int column = 0; column<8; column++){
                switch(board[row][column]){
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                        System.out.print("s ");
                        break;
                    case 2:
                        System.out.print("o ");
                        break;
                    case 3:
                        System.out.print("x ");
                        break;
                }
            }
            System.out.println();
        }
    }

    public int getValue(int row, int column){
        return board[row][column];
    }

    public void setValue(int row, int column, int value){
        board[row][column] = value;
    }

    public int[] stringToCoord(String coord){
        int[] returnCoord = new int[]{-1, -1};
        returnCoord[1] = Integer.parseInt(coord.substring(1)) - 1;
        switch (coord.charAt(0)) {
            case 'a':
                returnCoord[0] = 0;
                break;
            case 'b':
                returnCoord[0] = 1;
                break;
            case 'c':
                returnCoord[0] = 2;
                break;
            case 'd':
                returnCoord[0] = 3;
                break;
            case 'e':
                returnCoord[0] = 4;
                break;
            case 'f':
                returnCoord[0] = 5;
                break;
            case 'g':
                returnCoord[0] = 6;
                break;
            case 'h':
                returnCoord[0] = 7;
                break;
            default:
                System.out.println("Error");
                break;

        }
        return returnCoord;
    }

    public void printBothBoards(BattleBoard opponent){
        System.out.println("  Your Board               Opponent's board\n");
        System.out.print("  ");
        for (char i = 'a'; i < 'a' + 8; i++) {
            System.out.print(i + " ");
        }
        System.out.print("          ");
        for (char i = 'a'; i < 'a' + 8; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row<8; row++){
            System.out.print((row+1) + " ");
            for (int column = 0; column<8; column++){
                switch(board[row][column]){
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                        System.out.print("s ");
                        break;
                    case 2:
                        System.out.print("o ");
                        break;
                    case 3:
                        System.out.print("x ");
                        break;
                }
            }

            System.out.print("        " + (row+1) + " ");
            for (int column = 0; column<8; column++){
                switch(opponent.getValue(row, column)){
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                        System.out.print("s ");
                        break;
                    case 2:
                        System.out.print("o ");
                        break;
                    case 3:
                        System.out.print("x ");
                        break;
                }
            }
            System.out.println();
        }
    }
    // public static void main(String args[]){
    //     BattleBoard test = new BattleBoard();
    //     BattleBoard opp = new BattleBoard();
    //     test.printBothBoards(opp);
    // }
}