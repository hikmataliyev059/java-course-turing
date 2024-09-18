package az.classes.module01.games;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    public static void main(String[] args) {
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Guess a number from 1 to 100!");

        while (guess != targetNumber) {
            System.out.print("Enter number: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < targetNumber) {
                System.out.println("It's too small! Please try again.");
            } else if (guess > targetNumber) {
                System.out.println("It's huge! Please try again.");
            } else {
                System.out.println("Congratulations! You have found the correct number after " + attempts + " trying.");
            }
        }
        scanner.close();
    }

}
