package dte.dao.pagination.provider;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dte.dao.pagination.model.Order;
import dte.dao.pagination.model.PageRequest;
import dte.dao.pagination.model.PageTypedResponse;

/**
 * Created by Eliza on 03.04.2018.
 * Implementation of page provider
 */
public abstract class PageProviderImpl<T, TView> implements PageProvider<T, TView> {
    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> typeParameterClass;
    private final Class<T> viewParameterClass;

    public PageProviderImpl() {
        this.typeParameterClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.viewParameterClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param pageRequest  The search request
     * @param additionalParams The map of common filter parameters
     * @return The list of T objects
     */
    private List<T> getPage(PageRequest pageRequest, Map<String, Object> additionalParams) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(typeParameterClass).distinct(true);

        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        //filter query
        Predicate searchPredicate = getSearchPredicate( builder, root,  pageRequest.getSearch().getValue(), additionalParams);
        if(searchPredicate != null) {
            query.where(searchPredicate);
        }

        //sort
        for(Order order: pageRequest.getOrder()){
            String[] parts = pageRequest.findColumnName(order.getColumn()).split("\\.");
            Path path = root;
            for (String part: parts) {
                path = path.get(part);
            }

            query = query.orderBy(order.isAsc()?builder.asc(path):builder.desc(path));
        }

        TypedQuery<T> typedQuery = getSession().createQuery(query);

        //with pagination
        return typedQuery.setFirstResult(pageRequest.getStart()).setMaxResults(pageRequest.getLength()).getResultList();
    }

    /**
     * Get total count of filtered objects with considering of search request
     * @param ajaxRequest The search request
     * @param additionalParams   The map of common filter parameters
     * @return The total count of filtered objects
     */
    private Long getTotalCountFiltered(PageRequest ajaxRequest, Map<String, Object> additionalParams) {
        return getTotalCountFiltered(ajaxRequest.getSearch().getValue(), additionalParams);
    }

    /**
     * Get total count of filtered objects with considering of search string
     * @param searchStr The search string
     * @param additionalParams   The map of common filter parameters
     * @return The total count of filtered objects
     */
    private Long getTotalCountFiltered(String searchStr, Map<String, Object> additionalParams) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();
        Root<T> fisPackageRoot = query.from(typeParameterClass);
        query.distinct(true).select(builder.count(fisPackageRoot));

        Predicate searchPredicate = getSearchPredicate( builder, fisPackageRoot, searchStr, additionalParams);
        if(searchPredicate != null) {
            query.where(searchPredicate);
        }

        TypedQuery<Long> typedQuery = getSession().createQuery(query);
        return typedQuery.getSingleResult();
    }

    /**
     *  Get total count of all objects
     * @param additionalParams  The map of common filter parameters
     * @return The total count of all objects
     */
    private Long getTotalCount(Map<String, Object> additionalParams) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();
        Root<T> fisPackageRoot = query.from(typeParameterClass);
        query.distinct(true).select(builder.count(fisPackageRoot));

        Predicate searchPredicate = getTotalPredicate( builder, fisPackageRoot, additionalParams);
        if(searchPredicate != null) {
            query.where(searchPredicate);
        }

        TypedQuery<Long> typedQuery = getSession().createQuery(query);
        return typedQuery.getSingleResult();
    }

    /**
     *  Get query for data filtering with search string
     * @param builder The hibernate criteria builder
     * @param root The root search object
     * @param searchStr The search string
     * @param additionalParams  The map of common filter parameters
     * @return The search predicate
     */
    protected abstract Predicate getSearchPredicate( CriteriaBuilder builder, Root<T> root, String searchStr, Map<String, Object> additionalParams);

    /**
     *  Get query for data filtering
     * @param builder The hibernate criteria builder
     * @param root The root search object
     * @param additionalParams The map of common filter parameters
     * @return The search predicate
     */
    protected abstract Predicate getTotalPredicate( CriteriaBuilder builder, Root<T> root, Map<String, Object> additionalParams);

    /**
     *  Create presenter from object (presenter class should contains constructor TView(T obj))
     * @param obj The object
     * @return The presenter of object
     * @throws Exception The Exception
     */
    @SuppressWarnings("unchecked")
    private TView createView(T obj) throws Exception {
        return (TView) viewParameterClass.getConstructor(typeParameterClass).newInstance(obj);
    }

    /**
     *  Get pagination response with transformation of object to presenter
     * @param ajaxRequest The pagination request
     * @param additionalParams The map of common filter parameters
     * @return Pagination response
     */
    @Override
    public PageTypedResponse<TView> getPageResponseView(PageRequest ajaxRequest, Map<String, Object> additionalParams) throws Exception{
        List<TView> views = new ArrayList<>();
        List<T> objs = getPage(ajaxRequest, additionalParams);
        for(T obj: objs){
            views.add(createView(obj));
        }

        return new PageTypedResponse<>(
                getTotalCount(additionalParams),
                getTotalCountFiltered(ajaxRequest, additionalParams),
                ajaxRequest.getDraw(),
                views);
    }

    /**
     *  Get pagination response
     * @param ajaxRequest The pagination request
     * @param additionalParams The map of common filter parameters
     * @return Pagination response
     */
    @Override
    public PageTypedResponse<T> getPageResponse(PageRequest ajaxRequest, Map<String, Object> additionalParams){
        return new PageTypedResponse<>(
                getTotalCount(additionalParams),
                getTotalCountFiltered(ajaxRequest, additionalParams),
                ajaxRequest.getDraw(),
                getPage(ajaxRequest, additionalParams)
            );
    }

}
