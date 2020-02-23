package hashcode;
import java.util.*;

public class Library {

    private HashSet<Integer> bookIDs;
    private int booksPerDay, signUpProcess, totalBooks;
    private int signedUpAt = -1;
    private int id;

    public Library(int id, HashSet<Integer> bookIDs, int totalBooks, int booksPerDay, int signUpProcess){
        this.id = id;
        this.totalBooks = totalBooks;
        this.bookIDs = bookIDs;
        this.booksPerDay = booksPerDay;
        this.signUpProcess = signUpProcess;
    }

    public int getBooksPerDay() {
        return booksPerDay;
    }

    public HashSet<Integer> getBookIDs() {
        return bookIDs;
    }

    public int getSignUpProcess() {
        return signUpProcess;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getSignedUpAt() {
        return signedUpAt;
    }

    public void setSignedUpAt(int signedUpAt) {
        this.signedUpAt = signedUpAt;
    }

    public int getId() {
        return id;
    }
}