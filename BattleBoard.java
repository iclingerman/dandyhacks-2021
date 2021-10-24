import java.util.*;
// water = 0
// ship = 1
// hit = 2
// miss = 3
public class BattleBoard {
    private int[][] board;
    public ArrayList<ArrayList<Integer>> ships;
    public String hits;
    public String miss;
    public int remainingShips;

    public BattleBoard(){
        this.board = new int[8][8];
        this.ships = new ArrayList<ArrayList<Integer>>();
        this.hits = "";
        this.miss = "";
        this.remainingShips = 5;
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
                        System.out.print("x ");
                        break;
                    case 3:
                        System.out.print("o ");
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
                        System.out.print("x ");
                        break;
                    case 3:
                        System.out.print("o ");
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
                        System.out.print("x ");
                        break;
                    case 3:
                        System.out.print("o ");
                        break;
                }
            }
            System.out.println();
        }
    }

      public void shipAdd(){
        Scanner scan = new Scanner(System.in);
        int[] shipLengths = new int[]{5,4,3,3,2};
        for (int i=0; i <shipLengths.length; i++){
            System.out.println("This is your board:");
            printBoard();
            System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
            String input = scan.nextLine();
            String[] check = input.split(" ");
            int[] coords = stringToCoord(check[0]);
            boolean valid = checkValid(check[1], shipLengths[i], coords);
            while(!valid){
                System.out.println("Invalid Ship Placement");
                System.out.println("Please enter where you want the head of the ship (length " + shipLengths[i] + ")");
                input = scan.nextLine();
                check = input.split(" ");
                coords = stringToCoord(check[0]);
                valid = checkValid(check[1], shipLengths[i], coords);
            }
            shipPlace(check[1], shipLengths[i], coords);
        }
        // scan.close();
    }

    public boolean checkValid(String orientation, int length, int[] coords){
        if (coords[0] == -1 || coords[1] == -1){
            return false;
        }
        if (orientation.contains("v")){
            if (coords[1]+length>8 ){
                return false;
            }
            for (int i = coords[1]; i<(length + coords[1]); i++){
                if (getValue(i,coords[0]) == 1){
                    return false;
                }else{
                    if (!checkAround(i, coords[0], 1)){
                        return false;
                    }
                }
            }
        }else{
            if (coords[0]+length>8){
                return false;
            }
            for (int i = coords[0]; i<(length + coords[0]); i++){
                if (getValue(coords[1],i) == 1){
                    return false;
                }else{
                    if (!checkAround(coords[1], i, 1)){
                        return false;
                    }
                 }
            }
        }
        return true;
    }


    public void shipRandom(){
        int[] shipLengths = new int[]{5,4,3,3,2};
        Random rand = new Random();
        for (int i =0; i<shipLengths.length; i++){
            int row = rand.nextInt((7 - 0) + 1) + 0;
            int column = rand.nextInt((7 - 0) + 1) + 0;
            int ori = rand.nextInt((1 - 0) + 1) + 0;
            int[] coords = new int[]{row, column};
            String orientation = "";
            if (ori==1){
                orientation = "v";
            }else{
                orientation = "h";
            }
            boolean valid = checkValid(orientation, shipLengths[i], coords);
            while(!valid){;
                row = rand.nextInt((7 - 0) + 1) + 0;
                column = rand.nextInt((7 - 0) + 1) + 0;
                ori = rand.nextInt((1 - 0) + 1) + 0;
                coords = new int[]{row, column};
                if (ori==1){
                    orientation = "v";
                }else{
                    orientation = "h";
                }
                valid = checkValid(orientation, shipLengths[i], coords);
            }
            shipPlace(orientation, shipLengths[i], coords);
        }
    }

    public boolean checkAround(int r, int c, int val){
        if (r==7 || r==0 || c==7 || c==0){
            if (r==7){
                if (c==7){
                    if (getValue(r-1,c-1) == val || getValue(r,c-1) == val || getValue(r-1,c) == val){
                        return false;
                    }
                }else if (c==0){
                    if (getValue(r-1,c) == val || getValue(r-1,c+1) == val || getValue(r,c+1) == val){
                        return false;
                    }
                }else{
                    if (getValue(r-1,c-1) == val || getValue(r,c-1) == val || getValue(r-1,c) == val  || getValue(r-1,c+1) == val || getValue(r,c+1) == val){
                        return false;
                    }
                }
            }else if(r==0){
                if (c==7){
                    if (getValue(r,c-1) == val || getValue(r+1,c-1) == val || getValue(r+1,c) == val){
                        return false;
                    }
                }else if (c==0){
                    if (getValue(r,c+1) == val || getValue(r+1,c+1) == val || getValue(r+1,c) == val){
                        return false;
                    }
                }else{
                    if (getValue(r,c-1) == val || getValue(r+1,c-1) == val || getValue(r+1,c) == val  || getValue(r+1,c+1) == val || getValue(r,c+1) == val){
                        return false;
                    }
                }
            }else if (c==7){
                if (getValue(r-1,c-1) == val|| getValue(r-1,c) == val|| getValue(r,c-1) == val|| getValue(r+1,c-1) == val|| getValue(r+1,c) == val){
                    return false;
                }
            }else{
                if (getValue(r-1,c) == val|| getValue(r-1,c+1) == val|| getValue(r,c+1) == val|| getValue(r+1,c+1) == val|| getValue(r+1,c) == val){
                    return false;
                }
            }
        }else{
            if (getValue(r-1,c-1) == val || getValue(r-1,c) == val || getValue(r-1,c+1) == val|| getValue(r,c-1) == val|| getValue(r,c+1) == val|| getValue(r+1,c-1) == val|| getValue(r+1,c) == val|| getValue(r+1,c+1) == val){
                return false;
            }
        }
        return true;
    }

    public void setAround(int r, int c, int val){
        if (r==7 || r==0 || c==7 || c==0){
            if (r==7){
                if (c==7){
                    setValue(r-1,c-1, val);
                    setValue(r,c-1,val);
                    setValue(r-1,c,val);
                }else if (c==0){
                    setValue(r-1,c, val);
                    setValue(r-1,c+1, val);
                    setValue(r,c+1,val);
                }else{
                    setValue(r-1,c-1,val);
                    setValue(r,c-1,val);
                    setValue(r-1,c,val); 
                    setValue(r-1,c+1,val);
                    setValue(r,c+1,val);
                }
            }else if(r==0){
                if (c==7){
                    setValue(r,c-1,val);
                    setValue(r+1,c-1,val);
                    setValue(r+1,c,val);
                }else if (c==0){
                    setValue(r,c+1,val);
                    setValue(r+1,c+1,val);
                    setValue(r+1,c,val);
                    
                }else{
                    setValue(r,c-1, val);
                    setValue(r+1,c-1, val);
                    setValue(r+1,c,val);
                    setValue(r+1,c+1, val);
                    setValue(r,c+1, val);
                }
            }else if (c==7){
                setValue(r-1,c-1,val);
                setValue(r-1,c,val);
                setValue(r,c-1,val);
                setValue(r+1,c-1,val);
                setValue(r+1,c,val);
            }else{
                setValue(r-1,c,val);
                setValue(r-1,c+1,val);
                setValue(r,c+1,val);
                setValue(r+1,c+1,val);
                setValue(r+1,c,val);
            }
        }else{
            setValue(r-1,c-1,val);
            setValue(r-1,c,val);
            setValue(r-1,c+1,val);
            setValue(r,c-1,val);
            setValue(r,c+1,val);
            setValue(r+1,c-1,val);
            setValue(r+1,c,val);
            setValue(r+1,c+1,val);
        }
    }

    public void shipPlace(String info, int length, int[] coords){
        ArrayList<Integer> current = new ArrayList<Integer>();
        if (info.contains("v")){
            for (int i = coords[1]; i<(length + coords[1]); i++){
                setValue(i, coords[0], 1);
                current.add(i);
                current.add(coords[0]);
            }
        }else{
            for (int i = coords[0]; i<(length + coords[0]); i++){
                setValue(coords[1],i, 1);
                current.add(coords[1]);
                current.add(i);
            }
        }
        ships.add(current);
    }

    public int updateBoard(int[] coords){
        int spot = getValue(coords[0], coords[1]);
        String move = Integer.toString(coords[0]) + Integer.toString(coords[1]);
        if (spot == 1){
            setValue(coords[0], coords[1], 2);
            hits = hits + move + " ";
        }else{
            setValue(coords[0], coords[1], 3);
            if (miss.contains(move)){

            }else{
                miss = miss + move + " ";
            }
            return 0;
        }

        if (checkAround(coords[0], coords[1], 1)){
            ArrayList<Integer> shipGone = new ArrayList<Integer>();
            for(ArrayList<Integer> curr : ships){
                for (int i = 0; i < curr.size(); i=i+2){
                    if (curr.get(i)==coords[0] && curr.get(i+1)==coords[1]){
                        shipGone = curr;
                        break;
                    }
                }
                if (!shipGone.isEmpty()){
                    break;
                }
            }
            boolean allGone = true;
            for (int i=0; i<shipGone.size(); i=i+2){
                System.out.println(i);
                System.out.println(i+1);
                if (getValue(shipGone.get(i),shipGone.get(i+1))!=2){
                    allGone=false;
                    break;
                }
            }
            if (allGone){
                remainingShips--;
                for (int i=0; i<shipGone.size(); i=i+2){
                    setAround(shipGone.get(i), shipGone.get(i+1), 3);
                }
                for (int i=0; i<shipGone.size(); i=i+2){
                    setValue(shipGone.get(i), shipGone.get(i+1), 2);
                }
                for (int i=0; i<8; i++){
                    for (int j=0; j<8; j++){
                        move = Integer.toString(i) + Integer.toString(j);
                        if (getValue(i, j)==3){
                            if (miss.contains(move)){

                            }else{
                                miss = miss + move + " ";
                            }
                        }
                    }
                }

                return 2;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }
}