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
@Named(value = "addUserBean")
@RequestScoped
public class AddUserBean {

    private Integer userId;
    private String name;
    private String lastName;
    private String email;
    private char type;
    private String password;
    private String confirmPassword;
    private String error=" ";
    private List<Users> users;
    
    public AddUserBean() {
    }

    public AddUserBean(Integer userId, String name, String lastName, String email, char type, String password, String confirmPassword, List<Users> users) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.users = users;
    }
    
        private Users initUser() {
        Users user = new Users();
        user.setUserid(userId);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        return user;
    }
    
        private Query constructIsPresentQuerry() {
        String ql = "SELECT u FROM Users u WHERE u.userid = :userid AND u.email = :email";                  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);
        query.setParameter("userid", userId);
        query.setParameter("email", email);
        return query;
    }
        
        public String create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineLibraryPU");
        EntityManager em = emf.createEntityManager();
        Query query = constructIsPresentQuerry();
        users = query.getResultList();
        if (users.isEmpty())        //check if user with same userID or email exist, if not we are ok to go, if not we get error message
        {                           //we could make the insertion validation more thorough by checking other criteria
                                    //such as tittle and etition number to make sure there are no duplicates
            if(this.password.equals(this.confirmPassword)) //checking if password is correct
            {                            
            Users user = initUser();    
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();       
            return "managerView";
            }
            else
            {
                error = "<p style=\"background-color:red;width:200px;" +
                "padding:5px\">Passwords do not match, please check the data and retry! </p>";
                return "";
            }
        }
        else
        {
            error = "<p style=\"background-color:red;width:200px;" +
            "padding:5px\">User with userID: " + getUserId() + "and email" + getEmail() +
                    " already exists, please check the data and retry! </p>";
            return "";
        }
        
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
    
}
