import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelDriver {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        do {
            printMenu();
            choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        Reservation reservation = promptBookReservation();
                        hotel.addReservation(reservation);
                        break;
                    case 2:
                        if (hotel.removeReservation(promptName())) {
                            System.out.println("Your reservation has been canceled.");
                        } else {
                            System.out.println("Sorry we could not cancel your reservation.");
                        }
                        break;
                    default:
                        System.err.println("An error has occurred!");
                }
            } catch (InputMismatchException inputMismatchException) {
                scanner.nextLine();    // Clear the invalid input
                System.out.println("Invalid input - " + inputMismatchException.getMessage());
            }

            System.out.println();
        } while(choice != 0);
    }

    public static void printMenu() {
        System.out.println("Welcome to the hotel! What would you like to do?");
        System.out.println("0. Exit");
        System.out.println("1. Book a room");
        System.out.println("2. Cancel an existing reservation");
        System.out.println();
    }

    public static String promptName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the guest?");
        return scanner.nextLine();
    }

    public static Reservation promptBookReservation() {
        // Get the name of the guest
        Scanner scanner = new Scanner(System.in);
        String name = promptName();

        // Get the checkin date of the guest
        System.out.println("What date would " + name + " like to check in?");
        System.out.println("Please put the date in the format: MM/dd/yyyy hh:mm; e.g. 09/13/2022 11:30");
        String checkin = scanner.nextLine();

        // Get the number of nights the guest would like to stay
        System.out.println("How many nights would you like to stay?");
        int numberOfNights = scanner.nextInt();

        return new Reservation(name, checkin, numberOfNights);
    }
}
