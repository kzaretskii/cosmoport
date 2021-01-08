package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipSearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShipSpecification implements Specification<Ship> {
    private final ShipSearch criteria;

    public ShipSpecification(ShipSearch criteria) {
        this.criteria=criteria;
    }


    @Override
    public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<Predicate>();
        if(criteria.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%"));
        }
        if(criteria.getPlanet() != null){
            predicates.add(criteriaBuilder.like(root.get("planet"), "%" + criteria.getPlanet() + "%"));
        }
        if (criteria.getShipType() != null){
            predicates.add(criteriaBuilder.equal(root.get("shipType"), criteria.getShipType()));
        }
        if (criteria.getIsUsed() != null){
            predicates.add(criteriaBuilder.equal(root.get("isUsed"), criteria.getIsUsed()));
        }
        if (criteria.getAfter() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(criteria.getAfter());
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), calendar.getTime()));
        }
        if (criteria.getBefore() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(criteria.getBefore());
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), calendar.getTime()));
        }
        if (criteria.getMinSpeed() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), criteria.getMinSpeed()));
        }
        if (criteria.getMaxSpeed() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("speed"), criteria.getMaxSpeed()));
        }
        if (criteria.getMinCrewSize() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), criteria.getMinCrewSize()));
        }
        if (criteria.getMaxCrewSize() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), criteria.getMaxCrewSize()));
        }
        if (criteria.getMinRating() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), criteria.getMinRating()));
        }
        if (criteria.getMaxRating() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("rating"), criteria.getMaxRating()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
