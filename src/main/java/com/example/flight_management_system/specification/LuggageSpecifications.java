package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.Status;
import com.example.flight_management_system.specification.filter.LuggageFilter;
import org.springframework.data.jpa.domain.Specification;

public class LuggageSpecifications {

    private LuggageSpecifications() {}

    public static Specification<Luggage> withFilter(LuggageFilter f) {

        Specification<Luggage> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(ticketIdEquals(f.getTicketId()));
        spec = spec.and(statusEquals(f.getStatus()));

        return spec;
    }

    // ----------- JOIN filter -----------

    public static Specification<Luggage> ticketIdEquals(Long ticketId) {
        return (root, query, cb) -> {
            if (ticketId == null) return cb.conjunction();
            return cb.equal(root.get("ticket").get("id"), ticketId);
        };
    }

    // ----------- enum filter -----------

    public static Specification<Luggage> statusEquals(Status status) {
        return (root, query, cb) -> {
            if (status == null) return cb.conjunction();
            return cb.equal(root.get("status"), status);
        };
    }
}
