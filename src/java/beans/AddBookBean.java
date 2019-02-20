/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GW Computers
 */
@Named(value = "addBookBean")
@RequestScoped
public class AddBookBean {
    
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyRight;
    private String author;
    private int quantity;
    private String error=" ";
    private List<Books> books;
    /**
     * Creates a new instance of AddBookBean
     */
    public AddBookBean() {
    }
        private Books initBook() {
        Books book = new Books();
        book.setIsbn(this.isbn);
        book.setTitle(this.title);
        book.setEditionNumber(this.editionNumber);
        book.setCopyRight(this.copyRight);
        book.setAuthor(this.author);
        book.setQuantity(this.quantity);
        return book;
    }
        
        private Query constructIsPresentQuerry() {
        String ql = "SELECT b FROM Books b WHERE b.isbn = :isbn";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);
        query.setParameter("isbn", isbn);
        return query;
    }
        
        
        
        public String create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = constructIsPresentQuerry();
        books = query.getResultList();
        if (books.isEmpty())        //check if books with same isbn exist, if not we are ok to go, if not we get error message
        {                           //we could make the insertion validation more thorough by checking other criteria  
        Books book = initBook();    //such as tittle and etition number to make sure there are no duplicates
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();       
        return "managerView";
        }
        else
        {
            error = "<p style=\"background-color:red;width:200px;" +
            "padding:5px\">A book with ISBN: " + getIsbn() + "already exists, please check the data and retry! </p>";
            return "";
        }
        
    }
        
        public String cancel(){
        this.isbn = "*";
        this.title = "*";
        this.editionNumber = 1;
        this.copyRight = "*";
        this.author = "*";
        this.quantity = 1;
        return "managerView";
        }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
       public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
