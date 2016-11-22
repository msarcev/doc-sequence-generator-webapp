package app.model;

import javax.persistence.*;
import java.util.Date;

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

    @Version
    @Column(name = "DATE", unique = false, nullable = false)
    private Date dateTime;

    public Sequence(){}

    public Sequence(String author, String purpose, Date dateTime){
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Sequence [id=" + id + ", author=" + author + ", purpose=" + purpose + ", date=" + dateTime;    }



}
