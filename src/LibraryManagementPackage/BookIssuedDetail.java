package LibraryManagementPackage;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookIssuedDetail {
    private Book book;
    private Patron patron;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BookIssuedDetail(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusWeeks(2);
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }

    public double calculateFine() {
        if (returnDate != null) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * 10;
        }
        return 0;
    }
}