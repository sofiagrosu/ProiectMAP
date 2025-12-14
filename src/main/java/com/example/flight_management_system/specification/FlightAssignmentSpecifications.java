package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.FlightAssignment;
import com.example.flight_management_system.specification.filter.FlightAssignmentFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FlightAssignmentSpecifications {

    private FlightAssignmentSpecifications() {}

    public static Specification<FlightAssignment> withFilter(FlightAssignmentFilter f) {

        Specification<FlightAssignment> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(flightIdEquals(f.getFlightId()));
        spec = spec.and(staffIdEquals(f.getStaffId()));
        spec = spec.and(dateBetween(f.getDateFrom(), f.getDateTo()));

        return spec;
    }

    // ----------- JOIN filters -----------

    public static Specification<FlightAssignment> flightIdEquals(Long flightId) {
        return (root, query, cb) -> {
            if (flightId == null) return cb.conjunction();
            return cb.equal(root.get("flight").get("id"), flightId);
        };
    }

    public static Specification<FlightAssignment> staffIdEquals(Long staffId) {
        return (root, query, cb) -> {
            if (staffId == null) return cb.conjunction();
            return cb.equal(root.get("staff").get("id"), staffId);
        };
    }

    // ----------- date range -----------

    public static Specification<FlightAssignment> dateBetween(
            LocalDate from, LocalDate to) {

        return (root, query, cb) -> {
            if (from == null && to == null)
                return cb.conjunction();

            if (from != null && to != null)
                return cb.between(root.get("assignmentDate"), from, to);

            if (from != null)
                return cb.greaterThanOrEqualTo(root.get("assignmentDate"), from);

            return cb.lessThanOrEqualTo(root.get("assignmentDate"), to);
        };
    }
}
