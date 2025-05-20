package LibraryManagementPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Library {
    private String name;
    private String location;
    private List<Book> books;
    private List<Patron> patrons;
    private List<BookIssuedDetail> borrowedHistory = new ArrayList<>();
    private List<ReserveBook> reservations = new ArrayList<>();

    public Library(String name, String location) {
        this.name = name;
        this.location = location;
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String ISBN) {
        return books.removeIf(book -> book.getISBN().equals(ISBN));
    }

    public boolean updateBook(String title, String author, String ISBN, String genre, String publicationYear) {
        for(Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setPublicationYear(publicationYear);
                return true;
            }
        }
        return false;
    }

    public List<Book> searchBook(String title, String author, String genre) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if ((title == null || book.getTitle().equalsIgnoreCase(title)) &&
                (author == null || book.getAuthor().equalsIgnoreCase(author)) &&
                (genre == null || book.getGenre().equalsIgnoreCase(genre))) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void removePatron(Patron patron) {
        patrons.remove(patron);
    }

    public void searchPatron(String name, String id) {
        for (Patron patron : patrons) {
            if ((name == null || patron.getName().equalsIgnoreCase(name)) &&
                (id == null || patron.getId().equalsIgnoreCase(id))) {
                System.out.println("Patron found: " + patron.getName());
            }
        }
    }

    public void updatePatron(String id, String newContactInfo) {
        for (Patron patron : patrons) {
            if (patron.getId().equals(id)) {
                patron.updateContactInfo(newContactInfo);
                break;
            }
        }
    }

    public boolean borrowBook(Book book, Patron patron) {
        if (book.getStatus() == Book.BookStatus.AVAILABLE) {
            BookIssuedDetail detail = new BookIssuedDetail(book, patron);
            addIssuedDetail(detail);
            book.setStatus(Book.BookStatus.BORROWED);
            return true;
        }
        return false;
    }

    public void addIssuedDetail(BookIssuedDetail detail) {
        borrowedHistory.add(detail);
    }

    public List<BookIssuedDetail> getBorrowedHistoryForPatron(Patron patron) {
        List<BookIssuedDetail> history = new ArrayList<>();
        for (BookIssuedDetail detail : borrowedHistory) {
            if (detail.getPatron().equals(patron)) {
                history.add(detail);
            }
        }
        return history;
    }

    public boolean reserveBook(Book book, Patron patron) {
        if (book.getStatus() == Book.BookStatus.AVAILABLE) {
            ReserveBook reservation = new ReserveBook(book, patron);
            addReservation(reservation);
            book.setStatus(Book.BookStatus.RESERVED);
            return true;
        }
        return false;
    }

    public void addReservation(ReserveBook reservation) {
        reservations.add(reservation);
    }

    public void notifyPatronsForAvailableBook(Book book) {
        for (ReserveBook reservation : reservations) {
            if (reservation.getBook().equals(book) && reservation.getStatus() == ReserveBook.ReservationStatus.PENDING) {
                System.out.println("Notification: Dear " + reservation.getPatron().getName() +
                        ", the book '" + book.getTitle() + "' is now available.");
                reservation.confirmReservation();
            }
        }
    }

    public boolean cancelReservation(ReserveBook reservation) {
        if (reservations.contains(reservation)) {
            reservation.cancelReservation();
            reservations.remove(reservation);
            return true;
        }
        return false;
    }

    public Book recommendBookForPatron(Patron patron) {
        List<BookIssuedDetail> history = getBorrowedHistoryForPatron(patron);
        Map<String, Integer> genreCount = new HashMap<>();
        for (BookIssuedDetail detail : history) {
            String genre = detail.getBook().getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }
        String favGenre = genreCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        for (Book book : getBooks()) {
            if (book.getStatus() == Book.BookStatus.AVAILABLE && book.getGenre().equalsIgnoreCase(favGenre)) {
                return book;
            }
        }
        return null;
    }
}
