/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hes.model;

import com.hes.pojo.MySessionFactory;
import com.hes.pojo.UserLogin;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author SATISH SYAMALA
 */
public class LoginModel {

    private UserLogin userLogin;

    public String loginValidation(String userid, String password) {
        String result = "";
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List<UserLogin> userLogins = session.createQuery("From UserLogin ur where ur.loginId='" + userid + "' and ur.password='" + password + "'").list();
            if (!userLogins.isEmpty()) {
                result = "success";
                setUserLogin(userLogins.get(0));
            } else {
                result="Wrong user id and password !";
            }

        } catch (Exception e) {
            result="Wrong user id and password !";
        }
        finally{
            session.close();
        }
        return result;
    }

    /**
     * @return the userLogin
     */
    public UserLogin getUserLogin() {
        return userLogin;
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
