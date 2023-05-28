/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hes.model;

import com.hes.pojo.BasicInformation;
import com.hes.pojo.MySessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SATISH SYAMALA
 */
public class SettingModel {

    public BasicInformation getBasicInformation()
    {
        BasicInformation basicInformation = null;
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            List<BasicInformation> ls = session.createQuery("From BasicInformation cm ").list();
            if(!ls.isEmpty())
               basicInformation=ls.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return basicInformation;
    }

    public String setSettings(BasicInformation bi)
    {
        String result="";
        Session session = null;
        Transaction tr=null;
        try {
            session = MySessionFactory.getSession();
            tr=session.beginTransaction();
            session.createQuery("delete from BasicInformation bi").executeUpdate();
            session.save(bi);
            tr.commit();
            result="Setting updated successfully";

        } catch (Exception e) {
            if(tr!=null)
                tr.rollback();
            e.printStackTrace();
            result="Setting updated failed";
        } finally {
            session.close();
        }
        return result;
    }

}
