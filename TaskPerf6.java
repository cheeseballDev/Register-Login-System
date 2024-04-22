package javaActivities;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskPerf6 {

    private static String filePath = "C:\\Users\\Matthew\\OneDrive - STI College Ortigas-Cainta\\Documents\\VSCode\\java\\TextFiles\\records.txt";
    private static Scanner userInput = new Scanner(System.in);
    private static String alphanumeric = "^[a-zA-Z0-9]*$";
    
    public static void main(String[] args) throws IOException {
        clear();
        selectionArea();
    }

    private static void selectionArea() throws IOException {
        boolean flag = true;
        while(flag) {
            System.out.println("YOUR DECISIONS:");
            System.out.println("TYPE 1 TO REGISTER");
            System.out.println("TYPE 2 TO LOGIN");
            System.out.print("YOUR MOVE: ");
            int userSelection = userInput.nextInt();
            switch(userSelection) {
                case 1: 
                    registerAccount();
                    break;
                case 2:
                    loginAccount();
                    break;
                default:
                    clear();
                    System.out.println("Invalid input! Please try again!");
                    continue;
            }
        }
    }

    private static void registerAccount() {
        userInput.nextLine();
        clear();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String username = checkIfUsernameIsValid();
            String password = checkIfPasswordIsValid();

            writer.write(username + " " + password + "\n");
            writer.close();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("An error has occured while registering your account!");
            e.printStackTrace();
        }
    }

    private static void loginAccount() {
        clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while(true) {
                System.out.print("Enter your username: ");
                String username = userInput.next();
                System.out.print("Enter your password: ");
                String password = userInput.next();
                if(checkIfCredentialsAreCorrect(username, password)) {
                    System.out.println("Successfully logged in!");
                    System.exit(0);
                }
                clear();
                System.out.println("Incorrect username or password! Please try again!");
                continue;
            }
        } catch (IOException e) {
            System.out.println("An error has occured while logging in your account!");
            e.printStackTrace();
        }
    }

    private static String checkIfUsernameIsValid() throws IOException {
        while(true) {
            System.out.print("Enter username: ");
            String line = userInput.next();
            if (!line.matches(alphanumeric)) {
                clear();
                System.out.println("Username does not contain alphanumeric letters! Please try again!");
                continue;
            }
            if (checkIfUsernameExists(line)){
                clear();
                System.out.println("Username already exists! Please try again!");
                continue;
            }
            return line;
        }
    }

    private static String checkIfPasswordIsValid() {
        while(true) {
            System.out.print("Enter password: ");
            String line = userInput.next();
            if (!line.matches(alphanumeric)) {
                clear();
                System.out.println("Password does not contain alphanumeric letters! Please try again!");
                continue;
            }
            return line;
        }
    }

    private static boolean checkIfUsernameExists(String line) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String oneLineOfText;
        while((oneLineOfText = reader.readLine()) != null) {
            String listOfAllData[] = oneLineOfText.split(" ");
            if (listOfAllData[0].equals(line)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfCredentialsAreCorrect(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String oneLineOfText;
        while((oneLineOfText = reader.readLine()) != null) {
            String listOfAllData[] = oneLineOfText.split(" ");
            if (listOfAllData[0].equals(username) && listOfAllData[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
