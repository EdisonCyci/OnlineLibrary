/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GW Computers
 */
@Named(value = "addBookBean")
@Dependent
@RequestScoped
public class AddBookBean {

    @ManagedProperty(value="#{listBooksBean}")
    private ListBooksBean listBooksBean;
    
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyRight;
    private String author;
    private int quantity;
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
    
        public String create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Books book = initBook();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();       
        listBooksBean.fillBooks();
        return "index";
        
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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
