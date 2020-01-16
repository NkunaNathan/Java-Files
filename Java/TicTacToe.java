import java.util.InputMismatchException; // Import to catch error in input
import java.util.Scanner; // Scanner import to take input
import static java.lang.Math.abs; // used absolute values to do some mathematical oparations
//  Tic-tac-toe or noughts and crosses is a game
//  where two players take turns marking an empty
//  cell in a 3 x 3 grid with their respective tokens.
//  Each player has either an X token or an O token. A player
//  wins whenever they place three of their tokens in a horizontal,
//  vertical, or diagonal row on the grid. A draw occurs when all
//  the cells on the grid have been filled with tokens and neither player has won.

public class TicTacToe{
    public static void main(String[]args){
        TicTacToe game = new TicTacToe();
        Scanner sc = new Scanner(System.in);
        char[][] grid = game.initializeGrid();
        int row;
        int col;
        char gameState = 'c';
        boolean playerToggle = true; //variable to switch between players

//        System.out.println("Grid length: "+ grid.length);
//        System.out.println("Grid length: "+ grid[1].length);
        while (true){
            game.printGrid(grid);
            gameState = game.checkGameState(grid);
            if(gameState == 'w'){
                if(playerToggle){
                    System.out.println("Player O has won!");
                }else{
                    System.out.println("Player X has won!");
                }
                break;
            }else if(gameState == 'd'){
                System.out.println("Game over! It's a draw!");
                break;
            }

            if (playerToggle) {
                System.out.println();
                System.out.print("Player X, please enter a row (0, 1 or 2): ");
                row = game.getRow(sc);
                System.out.print("Player X, please enter a column (0, 1 or 2): ");
                col = game.getCol(sc);
                System.out.println();
            }else{
                System.out.println();
                System.out.print("Player O, please enter a row (0, 1 or 2): ");
                row = game.getRow(sc);
                System.out.print("Player O, please enter a column (0, 1 or 2): ");
                col = game.getCol(sc);
                System.out.println();
            }


            playerToggle = !playerToggle;

            char[][] newGrid;

            if(playerToggle){
                newGrid = game.placeToken(grid, 'O', row, col);
            }else{
                newGrid = game.placeToken(grid, 'X', row, col);
            }
            if(game.sameValues(grid, newGrid)){
                System.out.println("Illegal move! Try again.");
                playerToggle = !playerToggle;
            }else{
                for(int irow = 0 ; irow < grid.length ; irow++){
                    for(int jcol = 0 ; jcol  < grid[1].length ; jcol++){
                        grid[irow][jcol] = newGrid[irow][jcol];
                    }
                }
            }
        }


    }
// Method to return rows
    public int getRow(Scanner sc){
        int row = 999;
        while(row == 999){
            try{
                row = sc.nextInt();
                switch(row){
                    case 0: row =1;
                        break;
                    case 1: row = 3;
                        break;
                    case 2: row = 5;
                        break;
                    default:
                        break;
                }
            }catch(InputMismatchException exception){
                System.out.println(exception);
                sc.nextLine();
            }
        }
        return row;
    }
// method to return columns
    public int getCol(Scanner sc){
        int col = 999;
        while(col == 999){
            try{
                col = sc.nextInt();
                switch (col){
                    case 0: col = 2;
                        break;
                    case 1: col = 4;
                        break;
                    case 2: col = 6;
                        break;
                    default:
                        break;
                }
            }catch(InputMismatchException exception){
                System.out.println(exception);
                sc.nextLine();
            }
        }
        return col;
    }
// Check if there is a win, lose or draw in order to terminate the game
    public char checkGameState(char[][] grid){
        boolean victoryDetected = false;
        boolean noMovesLeft = true;
        for(int i=1 ; i< grid.length ; i+=2){
            if(i== 1 && grid[i][2] != ' ' && (grid[i][2] == grid[3][6]) && (grid[i][2] == grid[5][10])){
//                System.out.println("I should be one but it's is " + i);
                victoryDetected = true;
                break;
            }
            if(i==5 && grid[i][2] != ' ' && (grid[i][2] == grid[3][6]) && (grid[i][2] == grid[1][10])){
//                System.out.println("I should be five but it's is " + i);
                victoryDetected = true;
                break;
            }
            if(grid[i][2] != ' ' && (grid[i][2] == grid[i][6]) && (grid[i][2] == grid[i][10])){
                victoryDetected = true;
                break;
            }
            for(int j=2 ; j < grid[1].length ; j+=4){
                if(grid[i][j] != ' '){
                    if((grid[1][j] == grid[3][j]) && (grid[3][j] == grid[5][j])){
                        victoryDetected = true;
                        break;
                    }
                }else
                {
                    noMovesLeft = false;
                }
            }
        }
        if(victoryDetected){
            return 'w';
        }else if(noMovesLeft){
            return 'd';
        }else{
            return 'c';
        }
    }

//   Method to initialize game grid that will look like a 3 by 3 matrix
//    //        7 rows, 13 columns
    public char[][] initializeGrid(){
        char [][] grid = new char[][]{
                {'-','-','-','-','-','-','-','-','-','-','-','-','-'}, //0
//                0   1   2   3   4   5   6   7   8   9   10  11  12    // put x or O at 2, 6 and 10 in the columns
                {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'}, //1 empty
                {'-','-','-','-','-','-','-','-','-','-','-','-','-'}, //2
                {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'}, //3 empty
                {'-','-','-','-','-','-','-','-','-','-','-','-','-'}, //4
                {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'}, //5 empty
                {'-','-','-','-','-','-','-','-','-','-','-','-','-'}}; //6
//        7 rows, 13 columns
        return grid;
    }
// This method check if the use has place a value where one already exist
    public boolean sameValues(char[][] gridA, char[][] gridB){
        boolean same = true;
        for(int row= 0 ; row < gridA.length ; row++){
            for(int col= 0 ; col < gridA[1].length ; col++){
                if(gridA[row][col] != gridB[row][col]){
                    same = false;
                }
            }
        }
        return same;
    }
//  method to place tokens X & O
    public char[][] placeToken(char[][] grid, char token, int row, int col){
        char[][] newGrid = new char[7][13];
        for(int rowi=0 ; rowi < grid.length ; rowi++){
            for(int colj= 0 ; colj < grid[1].length ; colj++){
                newGrid[rowi][colj] = grid[rowi][colj];
            }
        }
        try{
            if(grid[row][abs(2*(col)-2)] == ' '){
                newGrid[row][abs(2*(col)-2)] = token;
            }
        }catch(ArrayIndexOutOfBoundsException exception){
            System.out.println(exception);
        }
        return newGrid;
    }
//  Method to print the grid
    public void printGrid(char[][] grid){
        for(int row=0 ; row < grid.length ; row++){
            for(int col=0 ; col<grid[0].length ; col++){
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

}
