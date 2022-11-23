package cinema;

import java.util.Scanner;

public class Cinema {

    static int rows;
    static int seats;
    static String[][]matrix = {};
    static Scanner scanner = new Scanner(System.in);
    static int purchasedTickets = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {

        // Get number of rows from input
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        // Get number of seats per rows from input
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        // Creat a matrix of rows and columns (Seating plan)
        matrix = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                matrix[i][j] = " S";
            }
        }

        while (true) {
            // Print the menu of available action
            printMenu();

            // choose action and perform based on action chosen
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    showSeats(matrix);
                    break;

                case 2:
                    // Buy a ticket
                    buyTicket();
                    break;

                case 3:
                    showStats();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void updateMatrix(int row, int seatNo) {
        matrix[row-1][seatNo-1] = " B";
    }


    public static int getPrice(int row) {
        int ticketPrice;
        // Calculate total number of seats
        int totalSeats = rows * seats;

        if (totalSeats <= 60) {
            ticketPrice = 10;

        } else if (row <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    public static void showSeats(String[][] matrix) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static void buyTicket() {
        // Get row and seat number
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        if (row > rows || row < 1) {
            System.out.println("Wrong input!");
            buyTicket();
        }
        System.out.println("Enter a seat number in that row:");
        int seatNo = scanner.nextInt();
        if (seatNo > seats || seatNo < 1) {
            System.out.println("Wrong input!");
            buyTicket();
        }

        // Check if ticket is already purchased
        if (matrix[row-1][seatNo-1].equals(" B")) {
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        }

        // Print out ticket price
        System.out.println("Ticket price: $" + getPrice(row));
        updateMatrix(row, seatNo);
        purchasedTickets++;
        currentIncome += getPrice(row);
    }

    private static void showStats() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        float percentSold = 100 * (float) purchasedTickets/((float) seats * rows);
        System.out.printf("Percentage: %.2f%%\n", percentSold);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income : $" + getTotalIncome());
    }

    private static int getTotalIncome() {
        int totalSeats = rows * seats;
        int totalIncome = 0;
        if (totalSeats <= 60) {
            totalIncome = 10 * totalSeats;
        } else {
            int frontSeats = (rows/2) * seats;
            int backSeats = totalSeats - frontSeats;
            totalIncome = frontSeats * 10 + backSeats * 8;
        }
        return totalIncome;
    }
}