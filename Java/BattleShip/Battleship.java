/* This class must instantiate a Grid object and use its methods to let the player pick a game grid,
update the game grid according to player input, print a feedback grid for the player, and check if
the player has sunk all the ships. See the Sample I/O in the assignment brief for details on what you
you should print to the console */
import java.util.Scanner;

/* This class must instantiate a Grid object and use its methods to let the player pick a game grid,
update the game grid according to player input, print a feedback grid for the player, and check if
the player has sunk all the ships. See the Sample I/O in the assignment brief for details on what you
you should print to the console */


public class Battleship{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int shots =  12;
        System.out.println("Pick your grid (1-4):");
        int seed = sc.nextInt();
        Grid grid = new Grid(seed);
        System.out.println("The battle begins!");
        System.out.println("You have " + shots + " shots remaining.");
        do {
            System.out.println("choose your square:");
            String var = sc.next();
            char[] input = var.toCharArray();
            int X = Integer.parseInt(String.valueOf(input[0]));
            int Y = Integer.parseInt(String.valueOf(input[1]));
            String gotthem = grid.fire(X,Y);
            System.out.println(gotthem);
            grid.printGrid();

            if (grid.checkGrid()){
                shots--;
                System.out.println("You have " + shots +" shots remaining.");
            }
            else{
                System.out.println("You won! and in only " + shots +" shots!");
			break;
            }
//
            if (shots == 0)
                System.out.println("You’re out of ammunition! Game Over");
        }while(shots != 0);

    }
}
