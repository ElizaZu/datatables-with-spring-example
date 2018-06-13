package dte.dao;

import dte.model.Person;
import dte.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */
@Repository("personDao")
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> getPersons(){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query  = builder.createQuery(Person.class).distinct(true);
        Root<Person> root = query.from(Person.class);
        query.select(root);
        //Predicate searchPredicate = builder.equal(root.get("lastnamess").get("id"), id);
       // query.where(searchPredicate);
        query.orderBy(builder.asc(root.get("lastname"))).orderBy(builder.asc(root.get("firstname")));

        TypedQuery<Person> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();

    }
}
