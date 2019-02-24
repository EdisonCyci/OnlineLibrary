/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import ModelClasses.Borrowedbooks;
import ModelClasses.Users;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GW Computers
 */
@Named(value = "lendBookBean")
@RequestScoped
public class lendBookBean {

    private String ISBN;
    private String title;
    private int editionNumber;
    private String CopyRight;
    private String author;
    private int quantity;
    private Books book;
    private String userEmail;
    private int userID;
    private Users user;
    private Borrowedbooks borrowedBook;
    private String error=" ";
    private List<Borrowedbooks> Bbooks;
    private Date borrowedOn;
    private List<Users> users;
    
    public lendBookBean() {
    }
        public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.ISBN = params.get("bookIsbn");
        book = em.find(Books.class, this.ISBN);
        /*String ql = "SELECT b FROM Books b WHERE b.isbn = :isbn";
        Query query = em.createQuery(ql);
        query.setParameter("isbn", ISBN);
        book = (Books) query.getSingleResult();*/
        populateBeanFromModel(this.book);
    }

        
        private void populateBeanFromModel(Books book) {
        ISBN = book.getIsbn();
        title = book.getTitle();
        editionNumber = book.getEditionNumber();
        CopyRight = book.getCopyRight();
        author = book.getAuthor();
        quantity = book.getQuantity();
    }   
        
        private Borrowedbooks initBook() {
        Borrowedbooks borrowedBook = new Borrowedbooks();
        borrowedBook.setIsbn(this.ISBN);
        borrowedBook.setTitle(this.title);
        borrowedBook.setEditionNumber(this.editionNumber);
        borrowedBook.setCopyRight(this.CopyRight);
        borrowedBook.setAuthors(this.author);
        borrowedBook.setQuantity(this.quantity);
        borrowedBook.setBorrowedBy(this.user);
        borrowedOn = new Date();
        borrowedBook.setBorrowedOn(this.borrowedOn);
        borrowedBook.setDaysOverdue(0);
        return borrowedBook;
    }
        
        private Query constructIsPresentQuerry() {
        String ql = "SELECT b FROM Borrowedbooks b WHERE b.isbn = :isbn";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);
        query.setParameter("isbn", ISBN);
        return query;
    }
        
        private Query constructIsUserPresentQuerry() {
        String ql = "SELECT u FROM Users u WHERE u.email = :email AND u.userid = :userid";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);
        query.setParameter("userid", userID);
        query.setParameter("email", userEmail);
        return query;
    }
        
        public String lendBook() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = constructIsPresentQuerry();
        Bbooks = query.getResultList();
        if (Bbooks.isEmpty())        //check if books with same isbn exist, if not we are ok to go, if not we get error message
        {                           //we could make the insertion validation more thorough by checking other criteria  
                                    //such as tittle and etition number to make sure there are no duplicates
            query = constructIsUserPresentQuerry();
            users = query.getResultList();
            if(users.isEmpty())
            {
            error = "<p style=\"background-color:red;width:200px;" +
            "padding:5px\">Incorrect UserID or Email, please doublecheck and try again! </p>";    
            return "";             
            }
            query = constructIsUserPresentQuerry();
            this.user = (Users) query.getSingleResult();
            borrowedBook = initBook();            
            em.getTransaction().begin();
            em.persist(borrowedBook);
            em.getTransaction().commit();
            book = em.find(Books.class, ISBN);
            book.setQuantity(quantity-1);
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
            return "managerView";
            }
        else
        {
            error = "<p style=\"background-color:red;width:200px;" +
            "padding:5px\">User Has Already borrowd book with ISBN: " + getISBN() + " , please check the data and retry! </p>";
            return "";
        }
        
    }
        
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
        return CopyRight;
    }

    public void setCopyRight(String CopyRight) {
        this.CopyRight = CopyRight;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Borrowedbooks getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Borrowedbooks borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
        
        
        
}
