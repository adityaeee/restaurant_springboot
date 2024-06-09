package com.aditya.restaurant.specification;

import com.aditya.restaurant.dto.request.SearchMenuRequest;
import com.aditya.restaurant.entity.Menu;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {
    public static Specification<Menu> getSpecification (SearchMenuRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isFalse(root.get("isDeleted")));

            if (request.getName() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+request.getName().toLowerCase()+"%")
                );
            }

            if(request.getPrice() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getPrice())
                );
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
