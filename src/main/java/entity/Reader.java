package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "READERS")
@NamedQueries({
        @NamedQuery(name = "findAllReaders", query = "SELECT r FROM Reader r"),
        @NamedQuery(name = "findReaderByName", query = "SELECT r FROM Reader r WHERE r.name = :name")
})
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private int readerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "overdue_status")
    private boolean overdueStatus;

    @Column(name = "birth_year")
    private int birthYear;

    // Getters and Setters
    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isOverdueStatus() {
        return overdueStatus;
    }

    public void setOverdueStatus(boolean overdueStatus) {
        this.overdueStatus = overdueStatus;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
