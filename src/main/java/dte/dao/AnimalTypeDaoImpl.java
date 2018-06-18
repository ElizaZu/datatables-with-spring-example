package dte.dao;

import dte.model.AnimalType;
import dte.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */
@Repository("personDao")
public class AnimalTypeDaoImpl implements AnimalTypeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AnimalType> findAll(){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AnimalType> query  = builder.createQuery(AnimalType.class).distinct(true);
        Root<AnimalType> root = query.from(AnimalType.class);
        query.select(root);
        //Predicate searchPredicate = builder.equal(root.get("lastnamess").get("id"), id);
       // query.where(searchPredicate);
        query.orderBy(builder.asc(root.get("name")));

        TypedQuery<AnimalType> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public void saveOrUpdate(AnimalType obj){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }
}
