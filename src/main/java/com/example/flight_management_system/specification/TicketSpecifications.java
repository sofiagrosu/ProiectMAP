package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.Ticket;
import com.example.flight_management_system.specification.filter.TicketFilter;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecifications {

    private TicketSpecifications() {}

    public static Specification<Ticket> withFilter(TicketFilter f) {

        Specification<Ticket> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(passengerIdEquals(f.getPassengerId()));
        spec = spec.and(flightIdEquals(f.getFlightId()));
        spec = spec.and(priceBetween(f.getPriceMin(), f.getPriceMax()));
        spec = spec.and(seatNumberContains(f.getSeatNumber()));

        return spec;
    }

    // ---------------- JOIN filters ----------------

    public static Specification<Ticket> passengerIdEquals(Long passengerId) {
        return (root, query, cb) -> {
            if (passengerId == null)
                return cb.conjunction();
            return cb.equal(root.get("passenger").get("id"), passengerId);
        };
    }

    public static Specification<Ticket> flightIdEquals(Long flightId) {
        return (root, query, cb) -> {
            if (flightId == null)
                return cb.conjunction();
            return cb.equal(root.get("flight").get("id"), flightId);
        };
    }

    // ---------------- numeric range ----------------

    public static Specification<Ticket> priceBetween(Double min, Double max) {
        return (root, query, cb) -> {

            if (min == null && max == null)
                return cb.conjunction();

            if (min != null && max != null)
                return cb.between(root.get("price"), min, max);

            if (min != null)
                return cb.greaterThanOrEqualTo(root.get("price"), min);

            return cb.lessThanOrEqualTo(root.get("price"), max);
        };
    }

    // ---------------- text ----------------

    public static Specification<Ticket> seatNumberContains(String value) {
        return (root, query, cb) -> {
            if (value == null || value.isBlank())
                return cb.conjunction();
            return cb.like(
                    cb.lower(root.get("seatNumber")),
                    "%" + value.toLowerCase() + "%"
            );
        };
    }
}
