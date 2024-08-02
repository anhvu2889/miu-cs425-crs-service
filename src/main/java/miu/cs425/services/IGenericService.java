package miu.cs425.services;

import miu.cs425.dtos.requests.DynamicFilterSortRequest;
import miu.cs425.repositories.specification.GenericSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public interface IGenericService<T> {

    default Result<T> filterAndSort(DynamicFilterSortRequest dynamicFilterSortRequest) {
        List<DynamicFilterSortRequest.FilterItem> filterItems = dynamicFilterSortRequest.getFilterModel().getItems();
        List<Specification<T>> specs = filterItems.stream()
                .map(GenericSpecification<T>::new)
                .collect(Collectors.toList());

        Specification<T> filter = specs.stream()
                .reduce(Specification::and)
                .orElse(null);

        List<DynamicFilterSortRequest.SortItem> sortItems = dynamicFilterSortRequest.getSortModels();
        List<Sort.Order> orders = sortItems.stream()
                .map(e -> new Sort.Order(Sort.Direction.fromString(e.getSort()), e.getField()))
                .collect(Collectors.toList());
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(dynamicFilterSortRequest.getPage(), dynamicFilterSortRequest.getPageSize(), sort);
        return new Result<T>(filter, pageable);
    }

    record Result<T>(Specification<T> filter, Pageable pageable) {
    }
}
