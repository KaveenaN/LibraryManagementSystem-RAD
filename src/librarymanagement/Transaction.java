package librarymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private int memberId;
    private int bookId;
    private Date dueDate;

    public Transaction(int transactionId, int memberId, int bookId, Date dueDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Transaction ID: " + transactionId + ", Member ID: " + memberId +
                ", Book ID: " + bookId + ", Due Date: " + sdf.format(dueDate);
    }
}
