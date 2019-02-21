/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GW Computers
 */
@Named(value = "updateBookBean")
@RequestScoped
public class updateBookBean {

    private String ISBN;
    private String title;
    private int editionNumber;
    private String CopyRight;
    private String author;
    private int quantity;
    private Books book;
    
    @ManagedProperty(value="#{listBooksBean}")
    private ListBooksBean listBooksBean;
    
    public updateBookBean() {
    }
    
        public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.ISBN = params.get("bookIsbn");
        book = em.find(Books.class, this.ISBN);  
        populateBeanFromModel(book);
    }
      
        
        private void populateModelFromBean(Books book) {
        book.setIsbn(this.ISBN);
        book.setTitle(this.title);
        book.setEditionNumber(this.editionNumber);
        book.setCopyRight(this.CopyRight);
        book.setAuthor(this.author);
        book.setQuantity(this.quantity);       
    }
        private void populateBeanFromModel(Books book) {
        ISBN = book.getIsbn();
        title = book.getTitle();
        editionNumber = book.getEditionNumber();
        CopyRight = book.getCopyRight();
        author = book.getAuthor();
        quantity = book.getQuantity();
    }
    
    public String update() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        book = em.find(Books.class, ISBN);
        populateModelFromBean(book);
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        //listBooksBean.fillBooks();
        return "managerView";
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

    public updateBookBean(String ISBN, String title, int editionNumber, String CopyRight, String author, int quantity) {
        this.ISBN = ISBN;
        this.title = title;
        this.editionNumber = editionNumber;
        this.CopyRight = CopyRight;
        this.author = author;
        this.quantity = quantity;
    }
    
    
}
