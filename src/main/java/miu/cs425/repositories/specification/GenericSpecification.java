package miu.cs425.repositories.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> implements Specification<T> {

    private final DynamicFilterSortRequest.FilterItem searchCriteria;

    public GenericSpecification(DynamicFilterSortRequest.FilterItem searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getOperator().equalsIgnoreCase("contains")) {
            if (root.get(searchCriteria.getField()).getJavaType() == String.class) {
                String value = searchCriteria.getValue() != null ? searchCriteria.getValue().toString() : "";
                return criteriaBuilder.like(root.get(searchCriteria.getField()), "%" + value + "%");
            }
            return null;
        }

        if (searchCriteria.getOperator().equalsIgnoreCase("equals")) {
            return criteriaBuilder.equal(root.get(searchCriteria.getField()), searchCriteria.getValue());
        }

        if (searchCriteria.getOperator().equalsIgnoreCase("startsWith")) {
            if (root.get(searchCriteria.getField()).getJavaType() == String.class) {
                String value = searchCriteria.getValue() != null ? searchCriteria.getValue().toString() : "";
                return criteriaBuilder.like(root.get(searchCriteria.getField()), value + "%");
            }
            return null;
        }

        if (searchCriteria.getOperator().equalsIgnoreCase("endsWith")) {
            if (root.get(searchCriteria.getField()).getJavaType() == String.class) {
                String value = searchCriteria.getValue() != null ? searchCriteria.getValue().toString() : "";
                return criteriaBuilder.like(root.get(searchCriteria.getField()), "%" + value);
            }
            return null;
        }

        if (searchCriteria.getOperator().equalsIgnoreCase("isEmpty")) {
            return criteriaBuilder.isNull(root.get(searchCriteria.getField()));
        }

        if (searchCriteria.getOperator().equalsIgnoreCase("isNotEmpty")) {
            return criteriaBuilder.isNotNull(root.get(searchCriteria.getField()));
        }
        return null;
    }
}
