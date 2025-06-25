package LibraryManagementPackage;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<LibraryBranch, Map<Book, Integer>> inventory;

    public Inventory(){
        this.inventory = new HashMap<>();
    }

    public void addToInventory(LibraryBranch branch, Book book, int quantity) {
        inventory.computeIfAbsent(branch, k -> new HashMap<>()).merge(book, quantity,Integer::sum);
    }

    public boolean transferBook(LibraryBranch fromBranch, LibraryBranch toBranch, Book book, int quantity) {
        if(getBookQuantity(fromBranch,book) >= quantity) {
            addToInventory(fromBranch, book, -quantity);
            addToInventory(toBranch, book, quantity);
            return true;
        }
        return false;
    }

    public int getBookQuantity(LibraryBranch branch, Book book) {
        return inventory.getOrDefault(branch, new HashMap<>()).getOrDefault(book, 0);
    }

    public Map<Book, Integer> branchInventory(LibraryBranch branch) {
        return inventory.getOrDefault(branch, new HashMap<>());
    }
}
