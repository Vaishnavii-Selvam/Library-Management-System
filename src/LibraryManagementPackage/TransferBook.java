package LibraryManagementPackage;

import java.util.Date;

public class TransferBook {
    private LibraryBranch fromBranch;
    private LibraryBranch toBranch;
    private Book book;
    private Date transferDate;
    private TransferStatus status;

    public enum TransferStatus {
        PENDING, COMPLETED, CANCELLED
    }

    public TransferBook(LibraryBranch fromBranch, LibraryBranch toBranch, Book book) {
        this.fromBranch = fromBranch;
        this.toBranch = toBranch;
        this.book = book;
        this.transferDate = new Date();
        this.status = TransferStatus.PENDING;
    }

    public void cancelTransfer() {
        this.status = TransferStatus.CANCELLED;
        book.setStatus(Book.BookStatus.AVAILABLE);
    }

    public void completeTransfer() {
        this.status = TransferStatus.COMPLETED;
        book.setStatus(Book.BookStatus.AVAILABLE);
    }

    public TransferStatus getStatus() {
        return status;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public Book getBook() {
        return book;
    }

    public LibraryBranch getFromBranch() {
        return fromBranch;
    }

    public LibraryBranch getToBranch() {
        return toBranch;
    }

}
