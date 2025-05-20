package LibraryManagementPackage;
import java.util.Date;

public class ReserveBook {
    private Book book;
    private Patron patron;
    private Date reservationDate;
    private ReservationStatus status;
    public enum ReservationStatus {
        PENDING, SUCCESS, CANCELLED
    }


    public ReserveBook(Book book, Patron patrons) {
        this.book = book;
        this.patron = patrons;
        this.reservationDate = new Date();
        this.status = ReservationStatus.PENDING;
        book.setStatus(Book.BookStatus.RESERVED);
    }

    public void cancelReservation() {
        this.status = ReservationStatus.CANCELLED;
        book.setStatus(Book.BookStatus.AVAILABLE);
    }
    public void confirmReservation() {
        this.status = ReservationStatus.SUCCESS;
        book.setStatus(Book.BookStatus.BORROWED);
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
