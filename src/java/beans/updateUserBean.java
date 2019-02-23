/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Books;
import ModelClasses.Users;
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
@Named(value = "updateUserBean")
@RequestScoped
public class updateUserBean {

    private int userID;
    private String name;
    private String lastName;
    private String Email;
    private char type;
    private String password;
    private Users user;
    public updateUserBean() {}
    
        public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.Email = params.get("UserEmail");
        String ql = "SELECT u FROM Users u WHERE u.email = :email";
        Query query = em.createQuery(ql);
        query.setParameter("email", Email);
        user = (Users) query.getSingleResult();
        populateBeanFromModel(user);
    }
    
        private void populateModelFromBean(Users user) {
        user.setUserid(this.userID);
        user.setName(this.name);
        user.setLastName(this.lastName);
        user.setEmail(this.Email);
        user.setType(this.type);
        user.setPassword(this.password);       
    }
        private void populateBeanFromModel(Users user) {
        userID = user.getUserid();
        name = user.getName();
        lastName = user.getLastName();
        Email = user.getEmail();
        type = user.getType();
        password = user.getPassword();
    }
    
    public String update() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        user = em.find(Users.class, userID);
        populateModelFromBean(user);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return "managerView";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
