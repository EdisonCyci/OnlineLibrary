/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Users;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GW Computers
 */
@ManagedBean(name="listUsersBean")
@SessionScoped
public class ListUsersBean implements Serializable {

    private int filterByUserId;
    private String filterByName;
    private String filterByLastName;
    private String filterByEmail;
    private char type;
    private String password;
    
    private List<Users> users;

    public ListUsersBean() {
        fillusers();
    }
    
    public void fillusers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        users = em.createNamedQuery("Users.findAll").getResultList();        
    }
    
    public ListUsersBean(int userid, String name, String lastName, String email, char type, String password, List<Users> users, Long id) {
        this.filterByUserId = userid;
        this.filterByName = name;
        this.filterByLastName = lastName;
        this.filterByEmail = email;
        this.type = type;
        this.password = password;
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    
    private Query constructFilterByIDQuery() {
        String ql = "SELECT u FROM Users u WHERE u.userid = :userid";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (filterByUserId!=0)
            query.setParameter("userid", "" + "%");
        else
            query.setParameter("userid","%"+ filterByUserId + "%");

        return query;
    }
    
        private Query constructFilterByNameQuery() {
        String ql = "SELECT u FROM Users u WHERE LOWER(u.name) LIKE LOWER(:name)";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterByName))
            query.setParameter("name", filterByName + "%");
        else
            query.setParameter("name", "" + "%");

        return query;
    }
    
        private Query constructFilterByLastNameQuery() {
        String ql = "SELECT u FROM Users u WHERE LOWER(u.lastName) LIKE LOWER(:lastName)";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterByLastName))
            query.setParameter("lastName", filterByLastName + "%");
        else
            query.setParameter("lastName", "" + "%");

        return query;
    }
    
        private Query constructFilterByEmailQuery() {
        String ql = "SELECT u FROM Users u WHERE u.email = :email";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterByEmail))
            query.setParameter("email", filterByEmail + "%");
        else
            query.setParameter("email", "" + "%");

        return query;
    }
    
        public String constructFilterByID() {
        Query query = constructFilterByIDQuery();
        users = query.getResultList();                
        return "";
    }    
        
        public String constructFilterByName() {
        Query query = constructFilterByNameQuery();
        users = query.getResultList();                
        return "";
    }    
    
        public String constructFilterByLastName() {
        Query query = constructFilterByLastNameQuery();
        users = query.getResultList();                
        return "";
    }    
                
        public String constructFilterByEmail() {
        Query query = constructFilterByEmailQuery();
        users = query.getResultList();                
        return "";
    }    
    
        
        private boolean isParameterPresent(String paramValue) {
        return paramValue != null && ! paramValue.trim().isEmpty();
    }
        
    public int getFilterByUserId() {
        return filterByUserId;
    }

    public void setFilterByUserId(int filterByUserId) {
        this.filterByUserId = filterByUserId;
    }

    public String getFilterByName() {
        return filterByName;
    }

    public void setFilterByName(String filterByName) {
        this.filterByName = filterByName;
    }

    public String getFilterByLastName() {
        return filterByLastName;
    }

    public void setFilterByLastName(String filterByLastName) {
        this.filterByLastName = filterByLastName;
    }

    public String getFilterByEmail() {
        return filterByEmail;
    }

    public void setFilterByEmail(String filterByEmail) {
        this.filterByEmail = filterByEmail;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        


}
