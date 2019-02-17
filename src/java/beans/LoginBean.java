/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ModelClasses.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author GW Computers
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

 private String email;
 private String password;
 private char type;
 private List<Users> users;
    
    public LoginBean() {
    }

    public LoginBean(String email, String password, char type, List<Users> users) {
        this.email = email;
        this.password = password;
        this.users = users;
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    
    
    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
    


        private Query constructLoginQuerry() {
        String ql = "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query;
    }
        public String attemptLogin() {
        Query query = constructLoginQuerry();
        users = query.getResultList();
        //type = users.get(0).getType();
        if(users.isEmpty())
        {
        return "";             
        }
        else{
            type = users.get(0).getType();
            if(type=='A')
             return "managerView";
            else 
             return "index";
        }

    }
        
        public String logout() {
        this.email = "";
        this.password = "";
        return "login";
    }
        
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
