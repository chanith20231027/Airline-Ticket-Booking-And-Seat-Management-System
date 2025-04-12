public class Person {
     private String name;
     private String surname;
     private String email;

     public Person() {
          this.name = "No name because It's a free seat";
          this.surname = "No surname because It's a free seat";
          this.email = "No email because It's a free seat";
     }
     public Person(String name, String surname, String email) {
          this.name = name;
          this.surname = surname;
          this.email = email;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getSurname() {
          return surname;
     }

     public void setSurname(String surname) {
          this.surname = surname;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public void printInformation() {
          System.out.println("Name: " + name);
          System.out.println("Surname: " + surname);
          System.out.println("Full Name: " + name + " " + surname);
          System.out.println("Email: " + email);
     }
}

