package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ISSUES")
@NamedQueries({
        @NamedQuery(name = "findAllIssues", query = "SELECT i FROM Issue i"),
        @NamedQuery(name = "findIssuesByReader", query = "SELECT i FROM Issue i WHERE i.reader = :reader"),
        @NamedQuery(name = "findIssuesByBook", query = "SELECT i FROM Issue i WHERE i.book = :book"),
        @NamedQuery(name = "findIssuesWithDetails",
                query = "SELECT NEW dto.IssueDetailsDTO(i.issueId, i.reader.name, i.book.title, i.issueDate, i.returnDate) " +
                        "FROM Issue i")
})
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private int issueId;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "return_date")
    private Date returnDate;

    // Getters and Setters
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
