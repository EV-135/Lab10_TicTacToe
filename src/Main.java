import java.util.Scanner;
public class Main {
    private static int ROWS=3;
    private static int COLS=3;
    private static String[][] board = new String[ROWS][COLS];
    private static int moveCount=0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row,col=-1;
        boolean done=false;
        boolean win=false;
        boolean tie=false;

        while(!done){
            String player = "X";
            clearBoard();
            win = false;
            tie = false;
            while(!win && !tie){
                display();

                System.out.println("Enter Move for " + player);
                row = SafeInput.getRangedInt(in, "Enter Row", 1, 3);
                col = SafeInput.getRangedInt(in, "Enter Column", 1, 3);
                row--;
                col--;
                if (isValidMove(row, col)) {
                    board[row][col] = player;
                    moveCount++;
                    if (isWin(player)) {
                        win = true;
                        display();
                        System.out.println("Player " + player + " wins!");
                    } else if (isTie()) {
                        display();
                        tie = true;
                        System.out.println("It's a tie!");
                    }

                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }




                } else {
                    System.out.println("Invalid move, try again.");
                }




            }
            done = SafeInput.getYNConfirm(in, "Play again?");
        }
    }


    private static void clearBoard(){
        for(int row=0;row<ROWS;row++){
            for(int col=0;col<COLS;col++){
                board[row][col]=" ";
            }
        }
        moveCount=0;
    }
    private static void display(){
        for(int row=0;row<ROWS;row++){
            for(int col=0;col<COLS;col++){
                System.out.print(board[row][col]);
                if(col<2){
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }
    private static boolean isValidMove(int row, int col){
        return board[row][col].equals(" ");
    }
    private static boolean isWin(String player){
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        }
        return false;
    }
    private static boolean isRowWin(String player){
        for (int row=0;row<ROWS;row++){
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player){
        for (int col=0;col<COLS;col++){
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player){
        for(int row=0;row<ROWS;row++){
            for(int col=0;col<COLS;col++){
                if((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isTie(){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }


}