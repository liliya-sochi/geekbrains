import java.util.ArrayList;

public class PhoneBook {
    private String name;
    private String number;
    private static ArrayList<PhoneBook> phoneBooks = new ArrayList<>();

    private PhoneBook(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public static void add(String name, String number) {
        phoneBooks.add(new PhoneBook(name, number));
    }

    public static void get(String name) {
        for(PhoneBook phoneBook : phoneBooks) {
            if(name.equals(phoneBook.name)) System.out.println(phoneBook.name + ": " + phoneBook.number);
        }
    }
}
