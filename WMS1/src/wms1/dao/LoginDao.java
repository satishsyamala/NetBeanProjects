/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wms1.pojos.MySessionFactory;
import wms1.pojos.Settings;
import wms1.pojos.Users;

/**
 *
 * @author Aadhya
 */
public class LoginDao {

    private Users users;

    public String loginValidation(String userid, String password) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            List<Users> userLogins = session.createQuery("From Users ur where lower(ur.userName)='" + userid.toLowerCase() + "' and ur.password='" + password + "'").list();
            System.err.println("userLogins : " + userLogins.size());
            if (!userLogins.isEmpty()) {
                result = "success";
                Users users = userLogins.get(0);
                users.setTempLastLoginTime(users.getLastLoginTime());
                users.setLastLoginTime(new Date());
                session.update(users);
                tr.commit();
                setUsers(users);
            } else {
                result = "Wrong user id or password !";
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
            result = "Wrong user id and password !";
        } finally {
            session.close();
        }
        return result;
    }

    public List<Users> getUser(long id, String userId) {
        List<Users> usersList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From Users a where 1=1 ";
            if (id > 0) {
                sql += " and a.userId=" + id;
            }
            if (userId != null && userId.trim().length() > 0) {
                sql += " and lower(a.userName) = :userid ";
            }
            sql += " order by lower(a.userName) ";
            Query q = session.createQuery(sql);
            if (userId != null && userId.trim().length() > 0) {
                q.setParameter("userid", userId.trim().toLowerCase());
            }
            usersList = q.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usersList;
    }

    public Settings getSettings() {
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From Settings a";

            List<Settings> setLs = session.createQuery(sql).list();
            if (!setLs.isEmpty()) {
                return setLs.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public void saveSettings(Settings settings) {
        Session session = null;
        Transaction tr = null;
        try {
            session = MySessionFactory.getSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(settings);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Object[]> getUserDetails(int id, String userId, String nameOrCode, String type) {
        List<Object[]> userslist = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.userId,a.userName,a.fullName,a.mobileNo,a.userType,to_char(a.lastLoginTime,'dd-mm-yy HH24:mm') From Users a where 1=1 ";
            if (id > 0) {
                sql += " and a.userId=" + id;
            }
            if (userId != null && userId.trim().length() > 0) {
                sql += " and lower(a.userName) = :userid ";
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                sql += " and (lower(a.userName) like :fullname||'%' or lower(a.fullName) like :fullname||'%') ";
            }
            if (type != null && type.trim().length() > 0 && !type.equalsIgnoreCase("All")) {
                sql += " and lower(a.userType) = '" + type.toLowerCase() + "' ";
            }
            sql += " order by lower(a.userName) ";
            Query q = session.createQuery(sql);
            if (userId != null && userId.trim().length() > 0) {
                q.setParameter("userid", userId.trim().toLowerCase());
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                q.setParameter("fullname", nameOrCode.trim().toLowerCase());
            }
            userslist = q.list();
            System.out.println("Users : " + userslist.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userslist;
    }

    public String saveUsers(Users users) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Users> ls = getUser(0, users.getUserName());

            if (ls.isEmpty()) {
                session = MySessionFactory.getSession();
                tr = session.beginTransaction();
                session.save(users);
                tr.commit();
                result = "success";
            } else {
                result = "User name already exist";
            }
        } catch (Exception e) {
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public String editUsers(Users users) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Users> ls1 = getUser(0, users.getUserName());
            if (ls1.isEmpty() || users.getUserId() == ls1.get(0).getUserId()) {
                Users ls = getUser(users.getUserId(), null).get(0);
                ls.setUserName(users.getUserName());
                ls.setFullName(users.getFullName());
                ls.setPassword(users.getPassword());
                ls.setMobileNo(users.getMobileNo());
                ls.setUserType(users.getUserType());
                session = MySessionFactory.getSession();
                tr = session.beginTransaction();
                session.update(ls);
                tr.commit();
                result = "success";
            } else {
                result = "User name already exist";
            }
        } catch (Exception e) {
            result = "Some thing went wrong";
            if (tr != null) {
                tr.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
