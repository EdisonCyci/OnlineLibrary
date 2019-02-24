/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import ModelClasses.Borrowedbooks;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GW Computers
 */
@Named(value = "listBorrowedBooksBean")
@SessionScoped

public class listBorrowedBooksBean {
    
    private int userID;
    private String filterISBN;
    private String filterTitle;
    private String filterAuthor;
            
    private List<Borrowedbooks> books;
    
    public listBorrowedBooksBean() {
        fillBooks();
    }
        public void fillBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        books = em.createNamedQuery("Borrowedbooks.findAll").getResultList();        
    }
        
        private Query constructFilterByTitleQuery() {
        String ql = "SELECT b FROM Borrowedbooks b WHERE LOWER(b.title) LIKE LOWER(:title)";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterTitle))
            query.setParameter("title", filterTitle + "%");
        else
            query.setParameter("title", "" + "%");

        return query;
    }
    
        private Query constructFilterByISBNQuery() {
        String ql = "SELECT b FROM Borrowedbooks b WHERE b.isbn = :isbn";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterISBN))
            query.setParameter("isbn", filterISBN);
        else
            query.setParameter("isbn", "" + "%");

        return query;
    }
        
    private Query constructFilterByUserIDQuery() {
        String ql = "SELECT b FROM Borrowedbooks b WHERE b.borrowedBy = :borrowedBy";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isIntParameterPresent(userID))
            query.setParameter("borrowedBy", userID);
        else
            query.setParameter("borrowedBy", "" + "%");

        return query;
    }
    
        private Query constructFilterByAuthorQuery() {
        String ql = "SELECT b FROM Borrowedbooks b WHERE LOWER(b.author) LIKE LOWER(:author)";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterAuthor))
            query.setParameter("author","%"+ filterAuthor + "%");
        else
            query.setParameter("author", "" + "%");

        return query;
    }
        public String removeBook(String ISBN) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Borrowedbooks bookBorrowed = em.find(Borrowedbooks.class, ISBN);
        em.getTransaction().begin();
        em.remove(bookBorrowed);
        em.getTransaction().commit();                       
        Books book = em.find(Books.class, ISBN);
        int quantity = book.getQuantity();
        book.setQuantity(quantity+1);
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        books = em.createNamedQuery("Borrowedbooks.findAll").getResultList();
        
        return "";
    }
        
    
        public String filterByISBN() {
        Query query = constructFilterByISBNQuery();
        books = query.getResultList();                
        return "";
    }    
        
    public String filterByTitle() {
        Query query = constructFilterByTitleQuery();
        books = query.getResultList();                
        return "";
    }
    
        public String filterByAuthor() {
        Query query = constructFilterByAuthorQuery();
        books = query.getResultList();                
        return "";
    }
        
    public String filterByUserID() {
        Query query = constructFilterByUserIDQuery();
        books = query.getResultList();                
        return "";
    }
        
    private boolean isParameterPresent(String paramValue) {
        return paramValue != null && ! paramValue.trim().isEmpty();
    }
    
        private boolean isIntParameterPresent(int paramValue) {
        return paramValue != 0;
    }

    public listBorrowedBooksBean(int userID, String filterISBN, String filterTitle, String filterAuthor, List<Borrowedbooks> bBooks) {
        this.userID = userID;
        this.filterISBN = filterISBN;
        this.filterTitle = filterTitle;
        this.filterAuthor = filterAuthor;
        this.books = bBooks;
    }
        
        

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFilterISBN() {
        return filterISBN;
    }

    public void setFilterISBN(String filterISBN) {
        this.filterISBN = filterISBN;
    }

    public String getFilterTitle() {
        return filterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    public String getFilterAuthor() {
        return filterAuthor;
    }

    public void setFilterAuthor(String filterAuthor) {
        this.filterAuthor = filterAuthor;
    }

    public List<Borrowedbooks> getBooks() {
        return books;
    }

    public void setBooks(List<Borrowedbooks> books) {
        this.books = books;
    }
        
        
    
}
