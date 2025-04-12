import java.util.InputMismatchException;    //import InputMismatchException class
import java.util.Scanner;                   //import Scanner class

public class w2051811_PlaneManagement {


    public static void main(String[] arg){


        System.out.println("Welcome to the Plane Management application");

        final int allRows = 4;          //declaring variables, assigning values as how many rows and how many seats per a row as 12 and 14 at seat arrangement
        final int seatsInRow12 = 12;
        final int seatsInRow14 = 14;

        int[][] seats = new int[allRows][]; // declare a 2D array using above variables

        seats[0] = new int[seatsInRow14];
        seats[1] = new int[seatsInRow12];
        seats[2] = new int[seatsInRow12];
        seats[3] = new int[seatsInRow14];

        for (int i = 0; i < seats.length; i++){             // assigning 0 to all element in the array as the default value
            for (int j = 0; j < seats[i].length; j++){
                seats[i][j] = 0;
            }
        }

        Ticket[] tickets = new Ticket[52];              // create an array that prints the information of tickets

        Person person = new Person();
        Ticket ticket = new Ticket(person);

        for (int i = 0; i < tickets.length; i++){       // assigning default values to ticket array elements
            tickets[i] = ticket;
        }

        Scanner scanner = new Scanner(System.in);  // creating Scanner object
        int optionNum;


        while(true){
            try{
                System.out.println("*********************************************");
                System.out.println("*\t\t\t\t MENU OPTIONS \t\t\t\t*");
                System.out.println("*********************************************");
                System.out.println("1) Buy a seat");
                System.out.println("2) Cancel a seat");
                System.out.println("3) Find first available seat");
                System.out.println("4) Show seating plans");
                System.out.println("5) Print ticket information and total sales");
                System.out.println("6) Search ticket");
                System.out.println("0) Quit");
                System.out.println("*********************************************");

                System.out.println("Please select an option: ");
                optionNum = scanner.nextInt();                          // to get user input for option

                if (optionNum == 0) {                                   // what tasks to do, when an option selected
                    break;

                } else if (optionNum == 1) {
                    buy_seat(seats, tickets);

                } else if (optionNum == 2) {
                    cancel_seat(seats, tickets);

                } else if (optionNum == 3) {
                    find_first_available(seats);

                } else if (optionNum == 4) {
                    show_seating_plan(seats);

                } else if (optionNum == 5) {
                    print_tickets_info(tickets);

                } else if (optionNum == 6) {
                    search_ticket(tickets);

                } else {
                    System.out.println("Invalid option.try again please.");
                }

            }catch(InputMismatchException e){
                System.out.println("Enter correct number at option.");
                scanner.next();
            }

        }

        scanner.close();
    }



    public static void buy_seat(int[][] seats, Ticket[] tickets) {

        System.out.println("----PURCHASE SEAT----");

        Scanner scanner2 = new Scanner(System.in);
        char rowSeat;
        int seatNum;
        int seatIndex;
        int rowIndex;
        double seatPrice;

        System.out.println("Enter NAME: ");                         //to get user inputs as name, surname, email
        String nameInput = scanner2.nextLine().toUpperCase();
        System.out.println("Enter SURNAME: ");
        String surnameInput = scanner2.nextLine().toUpperCase();
        System.out.println("Enter EMAIL: ");
        String emailInput = scanner2.nextLine().toLowerCase();


        do {
            System.out.println("Enter seat row (A/B/C/D): ");           //to input row from  user
            rowSeat = scanner2.next().toUpperCase().charAt(0);
        } while (!(rowSeat == 'A' | rowSeat == 'B' | rowSeat == 'C' | rowSeat == 'D'));

        if (rowSeat == 'A' | rowSeat == 'D') {
            do {
                System.out.println("Enter seat number (1 to 14): ");     // to input seat number from user who selected A or D
                seatNum = scanner2.nextInt();
            } while (seatNum > 14 | seatNum < 1);
        } else {
            do {
                System.out.println("Enter seat number (1 to 12): ");     // to input seat number from user who selected B or C
                seatNum = scanner2.nextInt();
            } while (seatNum > 12 | seatNum < 1);
        }

        rowIndex = rowSeat - 'A';           // using row and seat number, find index numbers of 2D array
        seatIndex = seatNum - 1;

        if (seats[rowIndex][seatIndex] == 0){       // assign 1 to element of 2D array
            seats[rowIndex][seatIndex] = 1;

            if (seatNum <6){            // put a price for seat following seat number
                seatPrice = 200;
            } else if (seatNum < 10) {
                seatPrice = 150;
            } else {
                seatPrice = 180;
            }

            Person person = new Person(nameInput, surnameInput, emailInput);        //pass the values to constructor Person

            Ticket ticket = new Ticket(rowSeat, seatNum, seatPrice, person);        // pass the values to constructor Ticket
            ticket.printInformation();

            if (rowSeat == 'A'){                    // add seat information to array of tickets
                tickets[seatNum - 1] = ticket;
            } else if (rowSeat == 'B') {
                tickets[14 + seatNum - 1] = ticket;
            } else if (rowSeat == 'C') {
                tickets[26 + seatNum - 1] = ticket;
            } else {
                tickets[38 + seatNum - 1] = ticket;
            }

            System.out.println("In the ROW " + rowSeat + ", SEAT NUMBER " + seatNum + " has been SOLD successfully.");

            ticket.save();

        } else {
            System.out.println("Sorry, In the ROW " + rowSeat + " SEAT NUMBER " + seatNum + " is already taken.");
        }

    }



    public static void cancel_seat(int[][] seats, Ticket[] tickets){

        System.out.println("----SEAT CANCELING----");

        Scanner scanner3 = new Scanner(System.in);
        char rowSeat;
        int seatNum;
        int seatIndex;
        int rowIndex;


        do {
            System.out.println("Enter seat row (A/B/C/D): ");                                 //to input row from  user
            rowSeat = scanner3.next().toUpperCase().charAt(0);
        } while (!(rowSeat == 'A' | rowSeat == 'B' | rowSeat == 'C' | rowSeat == 'D'));

        if (rowSeat == 'A' | rowSeat == 'D') {
            do {
                System.out.println("Enter seat number (1 to 14): ");                        // to input seat number from user who selected A or D
                seatNum = scanner3.nextInt();
            } while (seatNum > 14 | seatNum < 1);
        } else {
            do {
                System.out.println("Enter seat number (1 to 12): ");                        // to input seat number from user who selected B or C
                seatNum = scanner3.nextInt();
            } while (seatNum > 12 | seatNum < 1);
        }

        rowIndex = rowSeat - 'A';                   // using row and seat number, find index numbers of 2D array
        seatIndex = seatNum - 1;

        if (seats[rowIndex][seatIndex] == 1){           // assign 0 to element of 2D array
            seats[rowIndex][seatIndex] = 0;

            Person person = new Person();               // assigning default values again to relevant element while canceling seat
            Ticket ticket = new Ticket(person);

            if (rowSeat == 'A'){
                tickets[seatNum - 1] = ticket;
            } else if (rowSeat == 'B') {
                tickets[14 + seatNum - 1] = ticket;
            } else if (rowSeat == 'C') {
                tickets[26 + seatNum - 1] = ticket;
            } else {
                tickets[38 + seatNum - 1] = ticket;
            }

            System.out.println("In the ROW " + rowSeat + ", SEAT NUMBER " + seatNum + " has been CANCELED successfully.");

            Ticket ticketToDeleteFile = new Ticket(rowSeat, seatNum, person);
            ticketToDeleteFile.deleteFile();

        } else {
            System.out.println("The ROW " + rowSeat + " SEAT NUMBER " + seatNum + " is already free.");
        }

    }



    public static void find_first_available(int[][] seats){

        System.out.println("----THE FIRST SEAT AVAILABLE----");

        char[] rowLineLetters = {'A', 'B', 'C', 'D'};

        for (int n = 0; n < seats.length; n++) {
            for (int m = 0; m < seats[n].length; m++){
                if (seats[n][m] == 0){
                    System.out.println("You can find the first available seat in >>> ROW " + rowLineLetters[n] + " SEAT NUMBER " + (m + 1) + " <<<" );
                    break;
                }
            }
            break;
        }

    }



    public static void show_seating_plan(int[][] seats){

        System.out.println("----THE SEATING PLAN----");

        for (int n = 0; n < seats.length; n++) {

            if (n == 2) {
                System.out.println("");
            }

            for (int m = 0; m < seats[n].length; m++){

                if (seats[n][m] == 0){
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }

            System.out.println("");
        }

    }



    public static void print_tickets_info(Ticket[] tickets){

        double amount = 0;

        System.out.println("----All TICKETS THAT HAVE BEEN SOLD DURING THE SECTION----\n");

        for(Ticket t : tickets){
            if (t.getPrice() != 0){                                                 // depending on seat price, print all sold tickets
                System.out.println("Name: " + t.getPerson().getName());
                System.out.println("Surname: " + t.getPerson().getSurname());
                System.out.println("Email: " + t.getPerson().getEmail());
                System.out.println("Row: " + t.getRow());
                System.out.println("Seat number: " + t.getSeat());
                System.out.println("Price of ticket: " + t.getPrice());
                System.out.println("");

                amount += t.getPrice();

            }

        }

        System.out.println("Total amount: GBP " + amount);

    }

    public static void search_ticket(Ticket[] tickets){

        System.out.println("----SEARCH TICKET----");

        char rowSeat;
        int seatNum;
        int ticketArrayIndex;


        Scanner scanner4 = new Scanner(System.in);

        do {
            System.out.println("Enter seat row (A/B/C/D): ");           //to input row from  user
            rowSeat = scanner4.next().toUpperCase().charAt(0);
        } while (!(rowSeat == 'A' | rowSeat == 'B' | rowSeat == 'C' | rowSeat == 'D'));

        if (rowSeat == 'A' | rowSeat == 'D') {
            do {
                System.out.println("Enter seat number (1 to 14): ");        // to input seat number from user who selected A or D
                seatNum = scanner4.nextInt();
            } while (seatNum > 14 | seatNum < 1);
        } else {
            do {
                System.out.println("Enter seat number (1 to 12): ");        // to input seat number from user who selected B or C
                seatNum = scanner4.nextInt();
            } while (seatNum > 12 | seatNum < 1);
        }

        if (rowSeat == 'A'){
            ticketArrayIndex = seatNum - 1;
        } else if (rowSeat == 'B') {
            ticketArrayIndex = 14 + seatNum - 1;
        } else if (rowSeat == 'C') {
            ticketArrayIndex = 26 + seatNum - 1;
        } else {
            ticketArrayIndex = 38 + seatNum - 1;
        }

       if(tickets[ticketArrayIndex].getPrice() != 0) {
           System.out.println("Name: " + tickets[ticketArrayIndex].getPerson().getName());
           System.out.println("Surname: " + tickets[ticketArrayIndex].getPerson().getSurname());
           System.out.println("Email: " + tickets[ticketArrayIndex].getPerson().getEmail());
           System.out.println("Row: " + tickets[ticketArrayIndex].getRow());
           System.out.println("Seat number: " + tickets[ticketArrayIndex].getSeat());
           System.out.println("Price of ticket: GBP " + tickets[ticketArrayIndex].getPrice());
       }else{
           System.out.println("This seat is available.");
       }

    }

}
