/*
 * Name: Puneetpal Powar
 * Project: Pascals Triangle
 * Date: Apr 18th, 2020
 * Version: 1
 * Description: This project can give you the first 10 rows of pascals triangle and any number at a row and column with those 10 rows
 */

import java.util.Scanner;

/**
 *
 * @author Puneetpal Powar
 */
public class PascalsTriangle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Variables needed for user input
        int displayRow;
        int row;
        int col;
        double choice;

        // Ask the user to choice between 1 and 2 to choose the option they want to do
        do {
            System.out.println(
                    "Press 1 to find a certain term within the first 10 lines of Pascal's Triangle or 2 to display the first 10 lines of Pascal's Triangle:\n");
            // Declare the scanner for the input
            Scanner input = new Scanner(System.in);
            // Set choice to the next input
            choice = Double.parseDouble(input.nextLine());
            // While the choice is not 1 or 2 keep asking the user
        } while ((choice < 1 && choice > 2) || (choice > 1 && choice < 2));

        // If the choice is 1 execute the code below
        if (choice == 1) {

            // Ask the user for the row that the number they are looking for is in
            do {
                System.out.println("Please enter a row between 1 and 10:\n");
                Scanner input = new Scanner(System.in);
                row = Integer.parseInt(input.nextLine());
                row = Math.round(row);
                // If the row is not between 1 and 10 inclusive then keep asking
            } while (row < 1 && row > 10);

            // Ask the user to input the column the number is in
            do {
                System.out.println("Please enter a column between 1 and the row number:\n");
                Scanner input = new Scanner(System.in);
                col = Integer.parseInt(input.nextLine());
                col = Math.round(col);
                // If the input is less than 1 or greater than the row number(becuase that is
                // the maximum amount of numbers in the row) then ask the user to input another
                // number with in the constraints
            } while (col < 1 || col > row);

            // Send the row and column to the term method and print out the answer
            System.out.println("The value at this specific row and column is " + term(row, col));
        }

        // If the choice is 2, execute the code below
        if (choice == 2) {
            do {
                // Ask the user how many lines of pascals triangle they want to see
                System.out.println("Which row do you want the triangle to stop at(Pick a number between 1 and 10):\n");
                Scanner input = new Scanner(System.in);
                displayRow = Integer.parseInt(input.nextLine());
                displayRow = Math.round(displayRow);
                System.out.println("---------------------------------------------------------------------\n");
                // Keep asking the user the user for a correct input between 1 and 10
            } while (displayRow < 1 || displayRow > 10);

            // Send a starting row of 0 and the ending row chosen by the user to the row
            // method
            row(0, displayRow);
        }

    }

    // Declare a method term with 2 conditions
    public static int term(int row, int col) {

        // If the column number is 0 or the row and col are equal then return the number
        // 1
        if (col == 0 || row == col) {
            return 1;
        } else {
            // If not keep doing recursion until the value is found and send back to the
            // caller
            return term(row - 1, col) + term(row - 1, col - 1);
        }
    }

    // Declare and method row with 2 conditions
    public static void row(int row, int displayRow) {

        // If the starting row number is less than the ending row specified by the user
        // then start recursion(base for the factorial)
        if (row < displayRow) {

            // Make a for loop which calculates the number of spaces needed at each row, but
            // subtracting the ending by the current row
            for (int space = 0; space < (displayRow - row); space++) {
                System.out.print(" ");
            }

            // Make another for loop that goes through each row printing out each value at
            // the variable i, by calling on the term method
            for (int i = 0; i <= row; i++) {
                // Print out the values at that row with a space between them
                System.out.format(term(row, i) + " ");
            }
            // Print new line so the next row will be below the previous row
            System.out.println("");
            // Call on itself again only adding 1 to row eachtime until it reaches the
            // ending row value
            row(row + 1, displayRow);
        }

    }
}