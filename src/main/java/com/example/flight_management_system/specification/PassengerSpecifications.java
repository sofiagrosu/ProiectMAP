package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.Passenger;
import com.example.flight_management_system.specification.filter.PassengerFilter;
import org.springframework.data.jpa.domain.Specification;

public class PassengerSpecifications {

    private PassengerSpecifications() {}

    public static Specification<Passenger> withFilter(PassengerFilter f) {

        Specification<Passenger> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(nameContains(f.getName()));
        spec = spec.and(currencyContains(f.getCurrency()));
        spec = spec.and(checkedInEquals(f.getCheckedIn()));

        return spec;
    }

    public static Specification<Passenger> nameContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank())
                return cb.conjunction();
            return cb.like(cb.lower(root.get("name")),
                    "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Passenger> currencyContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank())
                return cb.conjunction();
            return cb.like(cb.lower(root.get("currency")),
                    "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<Passenger> checkedInEquals(Boolean checkedIn) {
        return (root, query, cb) -> {
            if (checkedIn == null)
                return cb.conjunction();
            return cb.equal(root.get("isCheckedIn"), checkedIn);
        };
    }
}
