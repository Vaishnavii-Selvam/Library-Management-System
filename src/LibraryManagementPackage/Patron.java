package LibraryManagementPackage;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String id;
    private List<String> borrowedBooks;
    private String contactInfo;

    public Patron(String name, String id, String contactInfo) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void updateContactInfo(String newContactInfo) {
        this.contactInfo = newContactInfo;
    }

}
