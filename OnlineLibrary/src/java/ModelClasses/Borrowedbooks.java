/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelClasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GW Computers
 */
@Entity
@Table(name = "borrowedbooks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Borrowedbooks.findAll", query = "SELECT b FROM Borrowedbooks b")
    , @NamedQuery(name = "Borrowedbooks.findByIsbn", query = "SELECT b FROM Borrowedbooks b WHERE b.isbn = :isbn")
    , @NamedQuery(name = "Borrowedbooks.findByTitle", query = "SELECT b FROM Borrowedbooks b WHERE b.title = :title")
    , @NamedQuery(name = "Borrowedbooks.findByEditionNumber", query = "SELECT b FROM Borrowedbooks b WHERE b.editionNumber = :editionNumber")
    , @NamedQuery(name = "Borrowedbooks.findByCopyRight", query = "SELECT b FROM Borrowedbooks b WHERE b.copyRight = :copyRight")
    , @NamedQuery(name = "Borrowedbooks.findByAuthors", query = "SELECT b FROM Borrowedbooks b WHERE b.authors = :authors")
    , @NamedQuery(name = "Borrowedbooks.findByQuantity", query = "SELECT b FROM Borrowedbooks b WHERE b.quantity = :quantity")
    , @NamedQuery(name = "Borrowedbooks.findByBorrowedOn", query = "SELECT b FROM Borrowedbooks b WHERE b.borrowedOn = :borrowedOn")
    , @NamedQuery(name = "Borrowedbooks.findByDaysOverdue", query = "SELECT b FROM Borrowedbooks b WHERE b.daysOverdue = :daysOverdue")})
public class Borrowedbooks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISBN")
    private Integer isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editionNumber")
    private int editionNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "copyRight")
    private String copyRight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Authors")
    private String authors;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrowedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowedOn;
    @Column(name = "daysOverdue")
    private Integer daysOverdue;
    @JoinColumn(name = "borrowedBy", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users borrowedBy;

    public Borrowedbooks() {
    }

    public Borrowedbooks(Integer isbn) {
        this.isbn = isbn;
    }

    public Borrowedbooks(Integer isbn, String title, int editionNumber, String copyRight, String authors, int quantity, Date borrowedOn) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyRight = copyRight;
        this.authors = authors;
        this.quantity = quantity;
        this.borrowedOn = borrowedOn;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(Date borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    public Integer getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(Integer daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public Users getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Users borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrowedbooks)) {
            return false;
        }
        Borrowedbooks other = (Borrowedbooks) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModelClasses.Borrowedbooks[ isbn=" + isbn + " ]";
    }
    
}
