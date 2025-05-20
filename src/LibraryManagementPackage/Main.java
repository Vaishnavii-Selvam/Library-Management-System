package LibraryManagementPackage;

public class Main {
    public static void main(String args[]) {
        System.out.println("Library Management System");
        // Create library branches
        LibraryBranch branch1 = new LibraryBranch("Central Library", "Downtown", "B001", "Alice");
        LibraryBranch branch2 = new LibraryBranch("West Branch", "Uptown", "B002", "Tom");

        // Create books
        Book book1 = new Book("Java Basics", "John Doe", "ISBN001", "Programming", "2020", Book.BookStatus.AVAILABLE);
        Book book2 = new Book("Python 101", "Jane Smith", "ISBN002", "Programming", "2021", Book.BookStatus.AVAILABLE);
        Book book3 = new Book("History of Art", "Emily Clark", "ISBN003", "Art", "2019", Book.BookStatus.AVAILABLE);

        // Add books
        branch1.addBook(book1);
        branch1.addBook(book2);
        branch1.addBook(book3);

        // Update a book
        branch1.updateBook("Advanced Java", "John Doe", "ISBN001", "Programming", "2022");

        // Search for a book
        System.out.println("Search result: " + branch1.searchBook("Advanced Java", null, null).size());

        // Remove a book
        branch1.removeBook("ISBN002");

        // Create patrons
        Patron patron1 = new Patron("Bob", "P001", "bob@email.com");
        Patron patron2 = new Patron("Sara", "P002", "sara@email.com");

        // Add patrons
        branch1.addPatron(patron1);
        branch1.addPatron(patron2);

        // Update patron info
        branch1.updatePatron("P001", "bob@newmail.com");

        // Search for a patron
        branch1.searchPatron("Sara", null);

        // Borrow a book
        boolean borrowed = branch1.borrowBook(book1, patron1);
        System.out.println("Book borrowed: " + borrowed);

        // Reserve a book
        boolean reserved = branch1.reserveBook(book3, patron2);
        System.out.println("Book reserved: " + reserved);

        // Recommend a book
        Book recommended = branch1.recommendBookForPatron(patron1);
        if (recommended != null) {
            System.out.println("Recommended for " + patron1.getName() + ": " + recommended.getTitle());
        }

        // Add this inside your main method
        Inventory inventory = new Inventory();

        // Add books to inventory
        inventory.addToInventory(branch1, book1, 5);
        inventory.addToInventory(branch1, book2, 3);
        inventory.addToInventory(branch2, book3, 2);

        // Transfer books between branches
        boolean transferSuccess = inventory.transferBook(branch1, branch2, book1, 2);
        System.out.println("Transfer success: " + transferSuccess);

        // Check book quantities
        System.out.println("Branch1 book1 quantity: " + inventory.getBookQuantity(branch1, book1));
        System.out.println("Branch2 book1 quantity: " + inventory.getBookQuantity(branch2, book1));

        // Notify patrons
        book3.setStatus(Book.BookStatus.AVAILABLE);
        branch1.notifyPatronsForAvailableBook(book3);

        // Cancel a reservation
        ReserveBook reservation = new ReserveBook(book3, patron2);
        branch1.addReservation(reservation);
        branch1.cancelReservation(reservation);

        // Initiate and complete a book transfer
        branch2.addBook(book2);
        TransferBook transfer = branch1.initiateTransfer(book3, branch2);
        if (transfer != null) {
            branch1.completeTransfer(transfer);
            System.out.println("Transfer completed for book: " + book3.getTitle());
        }

        // Create a BookIssuedDetail for a borrowed book
        BookIssuedDetail issuedDetail = new BookIssuedDetail(book1, patron1);

        // Simulate returning the book late
        issuedDetail.returnBook(new java.util.Date()); // Sets returnDate to now

        // Calculate and print the fine
        double fine = issuedDetail.calculateFine();
        System.out.println("Fine for late return: " + fine);
    }
}
