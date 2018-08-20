package dte.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dte.model.Person;

/**
 * Created by ElZu on 09.04.2018.
 */
public abstract class AbstractDaoImpl<T> {
    @Autowired
    protected SessionFactory sessionFactory;

    //final Class<T> typeParameterClass;

    protected AbstractDaoImpl() {
        //
    }

    public  Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /*public Class<T> getTypeParameterClass() {
        return typeParameterClass;
    }*/
}
