
//Imports needed for the code
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author YOU
 */
public class MovieReview {

    // CLASS CONSTANTS
    private static final String FS = File.separator;
    private static final int MENU_WORD_SCORE = 1;
    private static final int MENU_SENTENCE_SCORE = 2;
    private static final int MENU_WORD_MAX_MIN = 3;
    private static final int MENU_WORD_SORT = 4;
    private static final int MENU_EXIT = 5;

    // CLASS VARIABLES
    private static File reviewFile = new File("." + FS + "data" + FS + "movie.review" + FS + "MovieReviews.txt");
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Call on the menu method
        menu();
    }

    // Create a method for part 1 called WordReview
    public static void WordReview() throws FileNotFoundException, IOException {
        // Declare a variable word as a string and wordScore as a double
        String word;
        double wordScore;

        // Ask user to input a word and set the variable word to that input
        System.out.println("Enter word:\n");
        word = input.nextLine();

        // set word to lower case
        word = word.toLowerCase();

        // create an array results and set it to whatever the wordScore method returns
        double[] results = wordScore(word);

        // Calculate the word score by diving the first column of the array by the
        // second
        wordScore = results[0] / results[1];

        // print out how many times the words appears and the wordscore for that word
        System.out.println("The word " + word + " appears " + results[1] + " times");
        System.out.println(word + " has an average word score of " + wordScore);

    }

    // Create a method to calculate the wordScore
    public static double[] wordScore(String word) throws FileNotFoundException {
        // Declare empty doubles counter and rating
        double counter = 0;
        double rating = 0;

        // Make the code read from the MovieReview.txt file
        Scanner fileInput = new Scanner(reviewFile);

        // Go through the text document while it still has lines
        while (fileInput.hasNextLine()) {

            // Declare a varible data that is equal to the inputs from the text file and
            // make everything lowercase
            String data = fileInput.nextLine().toLowerCase();

            // If the word asked is in the text file
            if (data.contains(" " + word + " ")) {

                // Make counter go up as many times as the word shows up in the document
                counter = counter + 1;

                // The first charcter is the rating, convert it to ASCII and subtract the Ascii
                // equivilent for 0 to get your actual rating number
                rating = (int) (data.charAt(0) - '0') + rating;

            }

            // Make data empty before going back up to the top
            data = "";
        }

        // Make an array results and set the total rating and counter to certain points
        // in that array
        double[] results = new double[2];
        results[0] = rating;
        results[1] = counter;

        // return the array with the total rating and counter
        return results;

    }

    // For part 2 make a method called SentenceReview
    public static void SentenceReview() throws FileNotFoundException, IOException {

        // Declare the same variables as in part 1
        String file;
        double counter = 0;
        double rating = 0;
        double wordScore;
        double[] result;

        // Ask user to input a file name
        System.out.println("Enter file name:\n");
        // The variable file is equal to the input
        file = input.nextLine();
        // If make the varaible sentence file equal to file
        File sentenceFile = new File("." + FS + "data" + FS + "movie.review" + FS + file);

        // read from sentenceFile
        Scanner fileInput = new Scanner(sentenceFile);

        // while you have more lines in your text file
        while (fileInput.hasNextLine()) {

            // set the inputs to lowercase
            String word = fileInput.nextLine().toLowerCase();

            // Get the wordScore from the wordScore method and make it equal to the array
            // results
            result = wordScore(word);
            // Set the counter to the second column of the array
            counter = counter + result[1];
            // Set the the ratings the the first column of the array
            rating = rating + result[0];

        }

        // The word score is equal to the rating divided by the counter
        wordScore = rating / counter;

        // If the word score is greater than or equal to 2.2 the sentiment is positive
        if (wordScore >= 2.2) {
            System.out.println("The average score in " + file + " is positive");
            System.out.println("The average score is " + wordScore);
        }
        // If the word score is less than or equal to 1.8 the sentiment is negative
        if (wordScore <= 1.8) {
            System.out.println("The average score in " + file + " is negative");
            System.out.println("The average score is " + wordScore);
        }
        // If the word score is between 1.8 and 2.2 then the sentiment is neutral
        if (wordScore > 1.8 && wordScore < 2.2) {
            System.out.println("The average score in " + file + " is neutral");
            System.out.println("The average score is " + wordScore);
        }
    }

    // For part 2 make a method called MultipleWordScore
    public static void MultipleWordScore() throws FileNotFoundException, IOException {

        // Declare the same variables as the last methods
        String file;
        double wordScore;
        double[] result;
        // Make 2 new arrays called posWords and negWords with a capacity of one thing
        // and 2 more arrays called posScores and negScores with the same capacity
        String[] posWords = new String[1];
        double[] posScores = new double[1];
        String[] negWords = new String[1];
        double[] negScores = new double[1];

        // Ask the user to enter the file name they want you to sort and make the
        // variable file equal that input
        System.out.println("Enter file name:\n");
        file = input.nextLine();
        File sentenceFile = new File("." + FS + "data" + FS + "movie.review" + FS + file);

        // Read the input from the file entered by the user
        Scanner fileInput = new Scanner(sentenceFile);

        // Set the positive score to very negative and the negative scores to very
        // positive
        posScores[0] = -9999;
        negScores[0] = 9999;

        // While the file has more lines
        while (fileInput.hasNextLine()) {

            // Set each line to the variable word and set it to lower case
            String word = fileInput.nextLine().toLowerCase();

            // Get the results from the wordScore method
            result = wordScore(word);

            // The wordScore is equal to the first column divided by the second
            wordScore = result[0] / result[1];

            // If the word score is greater than the previous one, set the new word score as
            // the highest and record what word it is for the word score
            if (wordScore > posScores[0]) {
                posScores[0] = wordScore;
                posWords[0] = word;
            }
            // If the word score is less than the previous word score, then set the new word
            // score as the lowest and record the word for that word score
            if (wordScore < negScores[0]) {
                negScores[0] = wordScore;
                negWords[0] = word;
            }
        }

        // Print ou the most positive word and its score and the most negative word and
        // its score
        System.out
                .println("The most positive word in this file is " + posWords[0] + " with a score of " + posScores[0]);
        System.out
                .println("The most negative word in this file is " + negWords[0] + " with a score of " + negScores[0]);

    }

    // For part 4 make a method called MultipleWordScoreSort
    public static void MultipleWordScoreSort() throws FileNotFoundException, IOException {

        // Same variables as the previous method
        String file;
        double wordScore;
        double[] result;

        // Ask the user to enter a file name and set that input to the variable file
        System.out.println("Enter file name:\n");
        file = input.nextLine();
        File sentenceFile = new File("." + FS + "data" + FS + "movie.review" + FS + file);

        // Read the file inputed as an input
        Scanner fileInput = new Scanner(sentenceFile);

        // Make two new files called positive.txt and negative.txt
        File positiveFile = new File("." + FS + "data" + FS + "movie.review" + FS + "positive.txt");
        File negativeFile = new File("." + FS + "data" + FS + "movie.review" + FS + "negative.txt");

        // Make two printWriters, one writing to the positive.txt and another writing to
        // negative.txt
        PrintWriter pos = new PrintWriter(positiveFile);
        PrintWriter neg = new PrintWriter(negativeFile);

        // While the inputed file has more lines
        while (fileInput.hasNextLine()) {

            // Set the next line to the variable string and set everything to lowercase
            String word = fileInput.nextLine().toLowerCase();

            // Get the results from the method wordScore
            result = wordScore(word);

            // Get the word score by dividing the first column of the array by the second
            // column of the array
            wordScore = result[0] / result[1];

            // If the wordScore is greater than or equal 2.2 then write to the word and its
            // score in the positive.txt
            if (wordScore >= 2.2) {
                pos.println(word + ", " + wordScore);
            }

            // If the wordScore is less than of equal to 1.8 then write the word and its
            // score to negative.txt
            if (wordScore <= 1.8) {
                neg.println(word + ", " + wordScore);
            }
        }

        // When done tell the user to check files and see if the files are there
        System.out.println("Check Files");
        System.out.println("----------------------------------------");

        // Close the positive.txt writer and negative.txt writer
        pos.close();
        neg.close();

    }

    // For part 5 make a method called menu
    public static void menu() throws FileNotFoundException, IOException {

        // Make a variable choice so the user can input its optionby number
        int choice;

        // Print out the program name
        System.out.println("MOVIE REVIEW");
        System.out.println("----------------------------------------------");

        // While the user does not pick a number between 1 and 5, keep asking for an
        // option
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Get the word score?");
            System.out.println("2. Get the average score of words in a file?");
            System.out.println("3. Find the highest/lowest scoring words in a file?");
            System.out.println("4. Sort words into files positive.txt and negative.txt?");
            System.out.println("5. EXIT");
            System.out.println("------------------------------------------------");
            choice = Integer.parseInt(input.nextLine());
        } while (choice < 1 || choice > 5);

        // If the choice equals to the first class constant then call on the WordReview
        // function
        if (choice == MENU_WORD_SCORE) {
            WordReview();
        }
        // If the choice equals to the second class constant then call on the
        // SentenceReview function
        if (choice == MENU_SENTENCE_SCORE) {
            SentenceReview();
        }
        // If the choice equals to the third class constant then call on the
        // MultipleWordScore function
        if (choice == MENU_WORD_MAX_MIN) {
            MultipleWordScore();
        }
        // If the choice equals to the fourth class constant then call on the
        // MultipleWordScoreSort function
        if (choice == MENU_WORD_SORT) {
            MultipleWordScoreSort();
        }
        // If the choice equals to the fifth class constant then say thankyou and end
        // program
        if (choice == MENU_EXIT) {
            System.out.println("Thank You");
            System.out.println("-------------------------------------------");
        }
        // If nothing is chosen keep calling the menu method after each task
        else {
            menu();
        }
    }
}