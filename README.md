# Date Lab

## Learning Goals

- Use the classes from the `java.time` package.
- Make use of formatting dates using the `DateTimeFormatter` class.

## Instructions

Create a simple reservation system for a hotel where guests can create or
cancel their reservation.

Follow the given instructions and tips:

- Create a `Reservation` class.
  - The `Reservation` class will need the following properties:
    - Name of the guest.
    - Check in date and time using the format "MM/dd/uuuu HH:mm".
      - Example: "09/13/2022 11:30".
    - Number of nights.
  - A constructor that takes in the above properties.
  - Accessor and mutator methods for  accessing the instance variables.
- Create a `Hotel` class.
  - The `Hotel` class will only have a list of reservations for simplicity.
  - Should have the following methods:
    - `addReservation()`
    - `removeReservation()`
  - When a guest creates a reservation, let the user know the date and time
    they need to check out of their room.
- Use the `HotelDriver` class to run your code and use as a reference to how
  your code will be tested.

## Starter Code

Consider the driver class when writing the code for the `Hotel` and
`Reservation` classes:

```java
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
```

## Example Output

Here is an example run of the code for your reference. Make sure your output
looks the same when given these values:

```plaintext
Welcome to the hotel! What would you like to do?
0. Exit
1. Book a room
2. Cancel an existing reservation

1
What is the name of the guest?
Leslie Knope
What date would Leslie Knope like to check in?
Please put the date in the format: MM/dd/yyyy hh:mm; e.g. 09/13/2022 11:30
09/30/2022 12:15
How many nights would you like to stay?
5
You're reservation is set! Check out is at 10/05/2022 12:15

Welcome to the hotel! What would you like to do?
0. Exit
1. Book a room
2. Cancel an existing reservation

1
What is the name of the guest?
April Ludgate
What date would April Ludgate like to check in?
Please put the date in the format: MM/dd/yyyy hh:mm; e.g. 09/13/2022 11:30
10/01/2022 10:00
How many nights would you like to stay?
1
You're reservation is set! Check out is at 10/02/2022 10:00

Welcome to the hotel! What would you like to do?
0. Exit
1. Book a room
2. Cancel an existing reservation

2
What is the name of the guest?
April Ludgate
Your reservation has been canceled.

Welcome to the hotel! What would you like to do?
0. Exit
1. Book a room
2. Cancel an existing reservation

0
```

## Extension

Want more? Modify the driver class to validate the date-time that the user
enters. Make sure the date is a valid date, and it fits the format specified.
Then try validating the number of nights - ensuring the user only enters a
positive integer (cannot stay a negative number of nights or 0 nights in a
hotel).

