/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import wms1.pojos.MySessionFactory;
import wms1.util.ReportUtil;

/**
 *
 * @author Aadhya
 */
public class ReportsDao {

    public List<Object[]> getReportData(String reportName, Map<String, Object> fileters, int page, int size) {
        List<Object[]> data = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "";
            JSONObject qu = ReportUtil.getReportQueries(reportName);
            System.out.println("qu : "+qu);
            sql = qu.get("main").toString();
            for (String key : fileters.keySet()) {
                String nk=ReportUtil.conKey(key);
                if (qu.containsKey(nk)) {
                    sql += qu.get(nk).toString();
                }
            }
            sql += qu.get("order").toString();
             System.out.println("sql : "+sql);
            Query q = session.createQuery(sql);
            JSONArray lower= (JSONArray) qu.get("lower");
            for (String key : fileters.keySet()) {
                 String nk=ReportUtil.conKey(key);
                if (qu.containsKey(nk)) {
                    if(lower.contains(nk))
                        q.setParameter(nk+"_ft", fileters.get(key).toString().toLowerCase());
                    else
                        q.setParameter(nk+"_ft", fileters.get(key));
                }
            }
            if (size > 0) {
                q.setFirstResult(page).setMaxResults(size);
            }
            data= q.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }
}
