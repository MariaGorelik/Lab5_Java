package dto;

import java.util.Date;

public class IssueDetailsDTO {
    private int issueId;
    private String readerName;
    private String bookTitle;
    private Date issueDate;
    private Date returnDate;

    public IssueDetailsDTO(int issueId, String readerName, String bookTitle, Date issueDate, Date returnDate) {
        this.issueId = issueId;
        this.readerName = readerName;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

