package dte.dao;

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

import dte.model.AnimalType;

/**
 * Created by ElZu on 09.04.2018.
 */
@Repository
public class DictionaryDaoImpl<T> extends AbstractDaoImpl<T> implements DictionaryDao<T> {
    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query  = builder.createQuery(getObjectClass()).distinct(true);
        Root<T> root = query.from(getObjectClass());
        query.select(root);
        query.orderBy(builder.asc(root.get("name")));

        TypedQuery<T> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void save(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }
    private Class<T> getObjectClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
