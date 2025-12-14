package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.Airplane;
import com.example.flight_management_system.specification.filter.AirplaneFilter;
import org.springframework.data.jpa.domain.Specification;

public class AirplaneSpecifications {

    private AirplaneSpecifications() {}

    public static Specification<Airplane> withFilter(AirplaneFilter f) {

        Specification<Airplane> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(numberBetween(f.getNumberMin(), f.getNumberMax()));

        return spec;
    }

    public static Specification<Airplane> numberBetween(
            Integer min, Integer max) {

        return (root, query, cb) -> {
            if (min == null && max == null)
                return cb.conjunction();

            if (min != null && max != null)
                return cb.between(root.get("number"), min, max);

            if (min != null)
                return cb.greaterThanOrEqualTo(root.get("number"), min);

            return cb.lessThanOrEqualTo(root.get("number"), max);
        };
    }
}
