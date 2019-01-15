/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import ModelClasses.Books;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author elton.ballhysa
 */
@ManagedBean(name="listBooksBean")
@SessionScoped
public class ListBooksBean {
    
    private String filterISBN;
    private String filterTitle;
    private String filterAuthor;
            
    private List<Books> books;
    
    public ListBooksBean() {
        fillBooks();
    }

    public void fillBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        books = em.createNamedQuery("Books.findAll").getResultList();        
    }

    public List<Books> getBooks() {
        return books;
    }

    public String getFilterISBN() {
        return filterISBN;
    }

    public void setFilterISBN(String filterISBN) {
        this.filterISBN = filterISBN;
    }

    
    
    public void setBooks(List<Books> books) {
        this.books = books;
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

    private boolean isParameterPresent(String paramValue) {
        return paramValue != null && ! paramValue.trim().isEmpty();
    }
    
    //since you have changed the variable that was filtercontinet to filterAuthor you will have to adapt the querries to the books table
    
    private Query constructFilterByTitleQuery() {
        String ql = "SELECT b FROM Books b WHERE LOWER(b.title) LIKE LOWER(:title)";                  
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
        String ql = "SELECT b FROM Books b WHERE b.isbn = :isbn";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterISBN))
            query.setParameter("isbn", filterISBN);
        else
            query.setParameter("isbn", "" + "%");

        return query;
    }
    
        private Query constructFilterByAuthorQuery() {
        String ql = "SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(:author)";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterAuthor))
            query.setParameter("author","%"+ filterAuthor + "%");
        else
            query.setParameter("author", "" + "%");

        return query;
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
     
}

