import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Ticket {

    private char row;
    private int seatNumber;
    private double price;
    private Person person;


    public Ticket(Person person) {

        this.person = person;
    }

    public Ticket(char row, int seatNumber, Person person){
        this.row = row;
        this.seatNumber = seatNumber;
        this.person = person;

    }

    public Ticket(char row, int seatNumber, double price, Person person) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seatNumber;
    }

    public void setSeat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void printInformation(){

        person.printInformation();
        System.out.println("Row: " + row);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Price: GBP " + price);

    }

    public void save(){

        String rowName = Character.toString(row);               // convert to strings
        String seatNumName = Integer.toString(seatNumber);
        String priceToString = Double.toString(price);
        String nameFile = rowName + seatNumName + ".txt";       // create text filename using row and seat number

        try{
            FileWriter writeData = new FileWriter(nameFile);
            writeData.write("Name: " + getPerson().getName() + "\n");
            writeData.write("Surname: " + getPerson().getSurname()+ "\n");
            writeData.write("Email: " + getPerson().getEmail()+ "\n");
            writeData.write("Row: " + rowName+ "\n");
            writeData.write("Seat number: " + seatNumName+ "\n");
            writeData.write("Price of ticket: GBP " + priceToString+ "\n");
            writeData.close();
            System.out.println("Information is saved in a text file.");

        }catch (IOException e){
            System.out.println("An error");
            e.printStackTrace();

        }

    }

    public void deleteFile(){           // when canceled seat, to delete file previously created while buying a seat

        String rowName = Character.toString(row);               // convert to strings
        String seatNumName = Integer.toString(seatNumber);
        String nameFile = rowName + seatNumName + ".txt";       // create text file name using row and seat number

        File deleteFile = new File(nameFile);
        if (deleteFile.delete()) {
            System.out.println("Deleted the information of file because the seat was canceled.");
        }

    }
}
