/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.src.dao;

import java.util.List;
import org.hibernate.Session;
import com.src.db.Connect;
/**
 *
 * @author emmanuel
 */
public class GeneralDao <ClassName>{
    
    private Class<ClassName> type;
    
    public GeneralDao(Class<ClassName> type){
        this.type = type;
    }
    
    Session session = null;
    
    public ClassName create(ClassName cObj){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        
        try {
            session.save(cObj);
            session.getTransaction().commit();
            session.close();
            return  cObj;
        } catch (Exception e) {
            return null;
        }  
    }
    public ClassName update(ClassName cObj){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(cObj);
            session.getTransaction().commit();
            session.close();
            return cObj;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean delete(ClassName cObj){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(cObj);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public ClassName findByINT_PK(int pk){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        
        try {
            ClassName  cObj = (ClassName)session.get(type, pk);
            session.getTransaction().commit();
            session.close();
            return cObj;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ClassName findBySTRING_PK(String pk){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        try {
             ClassName cObj = (ClassName)session.get(type, pk);
            session.getTransaction().commit();
            session.close();
            return cObj;
        } catch (Exception e) {
            return null;
        }
    }
    public List<ClassName> listAll(){
        session = Connect.getSessionFactory().openSession();
        session.beginTransaction();
        try {
             List<ClassName> list = session.createCriteria(type.getName()).list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
