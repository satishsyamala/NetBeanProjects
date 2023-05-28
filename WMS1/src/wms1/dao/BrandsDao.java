/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wms1.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wms1.pojos.Brands;
import wms1.pojos.MySessionFactory;
import wms1.pojos.ServiceTypes;
import wms1.pojos.Users;

/**
 *
 * @author Aadhya
 */
public class BrandsDao {

    public List<Brands> getBrands(long id, String code, String name) {
        List<Brands> brandsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From Brands a where 1=1 ";
            if (id > 0) {
                sql += " and a.brandId=" + id;
            }
            if (code != null && code.trim().length() > 0) {
                sql += " and lower(a.brandCode) = :code ";
            }
            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.brandName) = :name ";
            }
            sql += " order by lower(a.brandCode) ";
            Query q = session.createQuery(sql);
            if (code != null && code.trim().length() > 0) {
                q.setParameter("code", code.trim().toLowerCase());
            }
            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            brandsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return brandsList;
    }

    public List<ServiceTypes> getServiceTypes(long id, String name) {
        List<ServiceTypes> brandsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "From ServiceTypes a where 1=1 ";
            if (id > 0) {
                sql += " and a.serviceTypeId=" + id;
            }

            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.serviceType) = :name ";
            }
            sql += " order by lower(a.serviceType) ";
            Query q = session.createQuery(sql);

            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            brandsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Size  : "+brandsList.size());
        return brandsList;
    }

    public List<Object[]> getBrandsDetails(int id, String code, String name, String nameOrCode) {
        List<Object[]> brandsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.brandId,a.brandCode,a.brandName From Brands a where 1=1 ";
            if (id > 0) {
                sql += " and a.brandId=" + id;
            }
            if (code != null && code.trim().length() > 0) {
                sql += " and lower(a.brandCode) = :code ";
            }
            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.brandName) = :name ";
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                sql += " and (lower(a.brandCode) like :fullname||'%' or lower(a.brandName) like :fullname||'%') ";
            }
            sql += " order by lower(a.brandName) ";
            Query q = session.createQuery(sql);
            if (code != null && code.trim().length() > 0) {
                q.setParameter("code", code.trim().toLowerCase());
            }
            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                q.setParameter("fullname", nameOrCode.trim().toLowerCase());
            }
            brandsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return brandsList;
    }

    public List<Object[]> getServiceTypes(int id, String name, String nameOrCode) {
        List<Object[]> brandsList = new ArrayList<>();
        Session session = null;
        try {
            session = MySessionFactory.getSession();
            String sql = "select a.serviceTypeId,a.serviceType,a.price From ServiceTypes a where 1=1 ";
            if (id > 0) {
                sql += " and a.serviceTypeId=" + id;
            }

            if (name != null && name.trim().length() > 0) {
                sql += " and lower(a.serviceType) = :name ";
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                sql += " and lower(a.serviceType) like :fullname||'%' ";
            }
            sql += " order by lower(a.serviceType) ";
            Query q = session.createQuery(sql);

            if (name != null && name.trim().length() > 0) {
                q.setParameter("name", name.trim().toLowerCase());
            }
            if (nameOrCode != null && nameOrCode.trim().length() > 0) {
                q.setParameter("fullname", nameOrCode.trim().toLowerCase());
            }
            brandsList = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return brandsList;
    }

    public String saveBrand(Brands brands) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Brands> ls = getBrands(0, brands.getBrandCode(), null);
            if (ls.isEmpty()) {
                ls = getBrands(0, null, brands.getBrandName());
                if (ls.isEmpty()) {
                    session = MySessionFactory.getSession();
                    tr = session.beginTransaction();
                    session.save(brands);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Brand name already exist";
                }
            } else {
                result = "Brand name already exist";
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

    public String updateBrand(Brands brands) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<Brands> ls = getBrands(0, brands.getBrandCode(), null);
            if (ls.isEmpty() || ls.get(0).getBrandId() == brands.getBrandId()) {
                ls = getBrands(0, null, brands.getBrandName());
                if (ls.isEmpty() || ls.get(0).getBrandId() == brands.getBrandId()) {
                    Brands br = getBrands(brands.getBrandId(), null, null).get(0);
                    session = MySessionFactory.getSession();
                    tr = session.beginTransaction();
                    br.setBrandCode(brands.getBrandCode());
                    br.setBrandName(brands.getBrandName());
                    session.update(br);
                    tr.commit();
                    result = "success";
                } else {
                    result = "Brand name already exist";
                }
            } else {
                result = "Brand name already exist";
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

    public String saveServiceTypes(ServiceTypes serviceTypes) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<ServiceTypes> ls = getServiceTypes(0, serviceTypes.getServiceType());
            if (ls.isEmpty()) {
                session = MySessionFactory.getSession();
                tr = session.beginTransaction();
                session.save(serviceTypes);
                tr.commit();
                result = "success";
            } else {
                result = "Service Type already exist";
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

    public String updateServiceTypes(ServiceTypes serviceTypes) {
        String result = "";
        Session session = null;
        Transaction tr = null;
        try {
            List<ServiceTypes> ls = getServiceTypes(0, serviceTypes.getServiceType());
            if (ls.isEmpty() || ls.get(0).getServiceTypeId() == serviceTypes.getServiceTypeId()) {
                ServiceTypes br = getServiceTypes(serviceTypes.getServiceTypeId(), null).get(0);
                session = MySessionFactory.getSession();
                tr = session.beginTransaction();
                br.setServiceType(serviceTypes.getServiceType());
                br.setPrice(serviceTypes.getPrice());
                session.update(br);
                tr.commit();
                result = "success";
            } else {
                result = "Service Type already exist";
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
}
