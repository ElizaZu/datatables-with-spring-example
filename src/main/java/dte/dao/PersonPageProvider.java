package dte.dao;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dte.dao.pagination.provider.PageProvider;
import dte.dao.pagination.provider.PageProviderImpl;
import dte.model.Person;
import dte.model.view.PersonView;

@Transactional(readOnly = true)
@Component
public class PersonPageProvider extends PageProviderImpl<Person, PersonView> implements PageProvider<Person, PersonView> {

	@Override
	protected Predicate getSearchPredicate(CriteriaBuilder builder, Root<Person> root, String searchStr, Map<String, Object> additionalParams) {
		//Get global list filter predicate
		Predicate searchPredicate = getBaseSearchExpression(builder, root, additionalParams);

		//Search filter
		if (searchStr.length() > 0) {
			searchStr = "%" + searchStr + "%";

			//Join animals list for search
			Join<Object, Object> animals = root.join("animals");

			//Filter all object fields by "OR"
			searchPredicate =
				//Person fields
				builder.or(builder.like(root.get("id").as(String.class), searchStr),
				builder.like(root.get("firstname").as(String.class), searchStr),
				builder.like(root.get("lastname").as(String.class), searchStr),
				builder.like(root.get("email").as(String.class), searchStr),
				builder.like(root.get("address").as(String.class), searchStr),
				builder.like(root.get("phone").as(String.class), searchStr),

				//Animal fields
				builder.like(animals.get("name").as(String.class), searchStr),
				builder.like(animals.get("yearOfBirth").as(String.class), searchStr),

				//Animal breed fields
				builder.like(animals.get("breed").get("name").as(String.class), searchStr),
				builder.like(animals.get("breed").get("animalType").get("name").as(String.class), searchStr));
		}

		return searchPredicate;
	}

	@Override
	protected Predicate getTotalPredicate(CriteriaBuilder builder, Root<Person> root, Map<String, Object> additionalParams) {
		return getBaseSearchExpression(builder, root, additionalParams);
	}

	/**
	 *  Get common search predicate for list with global no changeable by datatables filter conditions.
	 * @param builder The criteria builder
	 * @param root The root query item
	 * @param additionalParams The map of global parameters
	 * @return Search predicate
	 */
	private Predicate getBaseSearchExpression(CriteriaBuilder builder, Root<Person> root, Map<String, Object> additionalParams){
		Join<Object, Object> animals = root.join("animals");

		Predicate searchPredicate = null;
		if(additionalParams != null && additionalParams.get("animalType") != null) {
			if((Integer) additionalParams.get("animalType")>0) {
				searchPredicate = builder.equal(animals.get("breed").get("animalType"), additionalParams.get("animalType"));
			}
		}
		if(searchPredicate == null){
			searchPredicate = builder.notEqual(animals.get("breed").get("animalType"), 0);
		}

		return searchPredicate;
	}
}
