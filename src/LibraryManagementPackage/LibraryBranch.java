package LibraryManagementPackage;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranch extends Library {
    private String branchid;
    private String branchManager;
    private List<TransferBook> transferBooks;

    public LibraryBranch(String name, String location, String branchid, String branchManager) {
        super(name, location);
        this.branchid = branchid;
        this.branchManager = branchManager;
        this.transferBooks = new ArrayList<>();
    }

    public String getBranchid() {
        return branchid;
    }

    public String getBranchManager() {
        return branchManager;
    }

    public List<TransferBook> getTransferBooks() {
        return transferBooks;
    }

    public TransferBook initiateTransfer(Book book,LibraryBranch toBranch) {
        if(this.getBooks().contains(book) && book.getStatus() == Book.BookStatus.AVAILABLE) {
            book.setStatus(Book.BookStatus.RESERVED);
            TransferBook transferBook = new TransferBook(this,toBranch,book);
            transferBooks.add(transferBook);
            toBranch.getTransferBooks().add(transferBook);
            return transferBook;
        }
        return null;
    }

    public boolean completeTransfer(TransferBook transfer){
        if(transferBooks.contains(transfer)){
            if(transfer.getFromBranch() == this){
                this.removeBook(transfer.getBook().getISBN());
                transfer.getToBranch().addBook(transfer.getBook());
            }else{
                this.addBook(transfer.getBook());
                transfer.getFromBranch().removeBook(transfer.getBook().getISBN());
            }
            transfer.completeTransfer();
            transferBooks.remove(transfer);
            return true;
        }
        return false;
    }
}
