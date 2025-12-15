package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.Role;
import com.example.flight_management_system.specification.filter.AirlineEmployeeFilter;
import org.springframework.data.jpa.domain.Specification;

public class AirlineEmployeeSpecifications {

    private AirlineEmployeeSpecifications() {}

    public static Specification<AirlineEmployee> withFilter(AirlineEmployeeFilter f) {
        Specification<AirlineEmployee> spec = (root, query, cb) -> cb.conjunction();
        if (f == null) return spec;

        spec = spec.and(nameContains(f.getName()));
        spec = spec.and(companyContains(f.getCompany()));
        spec = spec.and(employeeNumberContains(f.getEmployeeNumber()));
        spec = spec.and(roleEquals(f.getRole()));

        return spec;
    }


    public static Specification<AirlineEmployee> nameContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirlineEmployee> companyContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("company")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirlineEmployee> employeeNumberContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("employeeNumber")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirlineEmployee> roleEquals(Role role) {
        return (root, query, cb) -> {
            if (role == null) return cb.conjunction();
            return cb.equal(root.get("role"), role);
        };
    }
}
