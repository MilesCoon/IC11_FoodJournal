import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        int calories, type, cookingTemp, carbs, organicChoice, option;
        boolean organic = true;
        String name;
        PaleoFood[] journal = new PaleoFood[100];
        File BINARY_FILE = new File("FoodJournal.data");
        Scanner keyboard = new Scanner(System.in);
        if (BINARY_FILE.exists() && BINARY_FILE.length() > 0) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
                journal = (PaleoFood[]) fileReader.readObject();
                for (PaleoFood pf : journal) {
                    if (pf != null) {
                        count++;
                        System.out.println(pf);
                    } else {
                        break;
                    }
                }
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        // If array is empty print no food eaten
        if (count == 0) {
            System.out.println("[No food eaten. You must be hungry.]");
        }

        do {
            System.out.println("\n******* Options Menu *******");
            System.out.println("Enter (1) to record a meat");
            System.out.println("Enter (2) to record a produce");
            System.out.println("Enter (3) to quit");
            option = keyboard.nextInt();
            keyboard.nextLine();
            switch (option) {
                case 1: // Record a meat
                    try {
                        System.out.print("What is the name of the meat eaten? ");
                        name = keyboard.nextLine();
                        System.out.print("How many calories was it? ");
                        calories = keyboard.nextInt();
                        System.out.print("Enter (1) for meat and (2) for seafood: ");
                        type = keyboard.nextInt();
                        if (type < 1 || type > 2) {
                            throw new MysteryMeatException();
                        }
                        System.out.print("Enter the cooking temperature: ");
                        cookingTemp = keyboard.nextInt();
                        // new meat object, and add it to journal
                        journal[count++] = new Meat(name, calories, type, cookingTemp);
                    } catch (MysteryMeatException e) {
                        System.err.println(e.getMessage());
                        Thread.sleep(500);
                    }
                    break;
                case 2: // Record a produce
                    System.out.print("What is the name of the produce eaten? ");
                    name = keyboard.nextLine();
                    System.out.print("How many calories was it? ");
                    calories = keyboard.nextInt();
                    System.out.print("How many carbohydrates? ");
                    carbs = keyboard.nextInt();
                    System.out.print("Enter (1) for organic and (2) for non-organic: ");
                    organicChoice = keyboard.nextInt();
                    if (organicChoice != 1) {organic = false;}
                    // new meat object, and add it to journal
                    journal[count++] = new Produce(name, calories, carbs, organic);
                    break;
                case 3: // Quit
                    System.out.println("~~~Food Recorded in Journal~~~");
                    for (int i = 0; i < count; i++) {
                        System.out.println(journal[i]);
                    }
                    int totalCals = totalCalories(journal, count);
                    System.out.println();
                    System.out.println("Total calories consumed = " + totalCals);
                    System.out.println("Average calories consumed = " + 1.0 * totalCals/count);
                    System.out.println("Food with most calories = " + foodWithMostCalories(journal, count));
                    System.out.println("Eat healthy and enjoy your weekend!");
                    break;
            }
        } while (option != 3);

        // Write the data to BINARY_FILE
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
            fileWriter.writeObject(journal);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int totalCalories(PaleoFood[] journal, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += journal[i].getCalories();
        }
        return total;
    }

    public static PaleoFood foodWithMostCalories (PaleoFood[] journal, int count) {
        int max = Integer.MIN_VALUE;
        PaleoFood maxFood = null;
        for (int i = 0; i < count; i++) {
            if (journal[i].getCalories() > max) {
                max = journal[i].getCalories();
                maxFood = journal[i];
            }
        }
        return maxFood;
    }
}