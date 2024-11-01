package az.classes.module01.games;

import java.util.Scanner;

public class WeekPlannerGame {

    private static final int DAYS_IN_WEEK = 7;
    private static final String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] weekTasks = new String[DAYS_IN_WEEK];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            System.out.println("Enter your task for " + daysOfWeek[i] + ": ");
            weekTasks[i] = scan.nextLine();
        }

        System.out.println("\nYour Week Planner:");
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            System.out.println(daysOfWeek[i] + ": " + weekTasks[i]);
        }

        while (true) {
            System.out.println("\nEnter the day to view your task (or type 'exit' to quit): ");
            String input = scan.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Exiting the week planner.");
                break;
            }

            boolean dayFound = false;
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                if (daysOfWeek[i].toLowerCase().equals(input)) {
                    System.out.println(daysOfWeek[i] + ": " + weekTasks[i]);
                    dayFound = true;
                    break;
                }
            }

            if (!dayFound) {
                System.out.println("Invalid day. Please enter a valid day of the week.");
            }
        }
        scan.close();
    }

}
