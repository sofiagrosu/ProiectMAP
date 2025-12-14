package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.AirportEmployee;
import com.example.flight_management_system.specification.filter.AirportEmployeeFilter;
import org.springframework.data.jpa.domain.Specification;

public class AirportEmployeeSpecifications {

    private AirportEmployeeSpecifications() {}

    public static Specification<AirportEmployee> withFilter(AirportEmployeeFilter f) {

        Specification<AirportEmployee> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(nameContains(f.getName()));
        spec = spec.and(departmentContains(f.getDepartment()));
        spec = spec.and(designationContains(f.getDesignation()));
        spec = spec.and(employeeNumberContains(f.getEmployeeNumber()));

        return spec;
    }

    // Staff.name (moștenit) - trebuie să fie "name" în Staff
    public static Specification<AirportEmployee> nameContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirportEmployee> departmentContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("department")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirportEmployee> designationContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("designation")), "%" + value.toLowerCase() + "%");
        };
    }

    public static Specification<AirportEmployee> employeeNumberContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("employeeNumber")), "%" + value.toLowerCase() + "%");
        };
    }
}
