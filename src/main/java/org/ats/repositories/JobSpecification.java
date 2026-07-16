package org.ats.repositories;

import org.ats.dto.JobCriteria;
import org.ats.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import java.math.BigDecimal;

public final class JobSpecification {
    private JobSpecification(){

    }

    public static Specification<Job> from (JobCriteria criteria){

        return Specification.allOf(
                hasKeyword(criteria.getKeyword()),
                hasLocation(criteria.getLocation()),
                hasExperience(criteria.getExperience()),
                hasJobType(criteria.getJobType()),
                hasQualification(criteria.getQualification()),
                hasPriceNearRange(criteria.getPriceRange())
        );
    }

    private static Specification<Job> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            String pattern = "%" + keyword.trim().toLowerCase() + "%";

            Predicate titlePredicate = cb.like(
                    cb.lower(root.get("title")),
                    pattern
            );

            Predicate descriptionPredicate = cb.like(
                    cb.lower(root.get("description")),
                    pattern
            );

            return cb.or(titlePredicate, descriptionPredicate);
        };
    }


    private static Specification<Job> hasLocation(String location) {
        return (root, query, cb) -> {
            if (location == null || location.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("location")),
                    "%" + location.trim().toLowerCase() + "%"
            );
        };
    }

    private static Specification<Job> hasExperience(String experience){
        return (root, query, criteriaBuilder) ->{
            if(experience == null || experience.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("experience")),
                    "%" + experience.trim().toLowerCase()+ "%");
        };
    }

    private static Specification<Job> hasJobType(String jobType){
        return ((root, query, criteriaBuilder) -> {
            if(jobType == null || jobType.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("jobType")),
                    "%"+jobType+"%");
        });
    }

    private static Specification<Job> hasQualification(String qualification){
        return ((root, query, criteriaBuilder) -> {
            if(qualification == null || qualification.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("qualification")),
                    "%"+qualification+"%");
        });
    }

    private static Specification<Job> hasPriceNearRange(BigDecimal priceRange){
        return ((root, query, criteriaBuilder) -> {
            if(priceRange == null){
                return criteriaBuilder.conjunction();
            }

            BigDecimal tolerance = priceRange.multiply(new BigDecimal("0.10"));

            BigDecimal minPrice = priceRange.subtract(tolerance);
            BigDecimal maxPrice = priceRange.add(tolerance);
            return criteriaBuilder.between(
                    root.get("priceRange"),
                    minPrice,maxPrice);
        });
    }
}