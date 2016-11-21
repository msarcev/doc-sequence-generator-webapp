package app.model;

import javax.persistence.*;

@Entity
@Table(name = "SEQUENCE")
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "AUTHOR", unique = false, nullable = false)
    private String author;

    @Column(name = "PURPOSE", unique = false, nullable = false)
    private String purpose;

    @Column(name = "DATE", unique = false, nullable = false)
    private String dateTime;

    public Sequence(){}

    public Sequence(String author, String purpose, String dateTime){
        this.author = author;
        this.purpose = purpose;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Sequence [id=" + id + ", author=" + author + ", purpose=" + purpose + ", date=" + dateTime;    }


}
