package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.specification.filter.NoticeBoardFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class NoticeBoardSpecifications {

    private NoticeBoardSpecifications() {}

    public static Specification<NoticeBoard> withFilter(NoticeBoardFilter f) {

        Specification<NoticeBoard> spec =
                (root, query, cb) -> cb.conjunction();

        if (f == null) return spec;

        spec = spec.and(dateBetween(f.getDateFrom(), f.getDateTo()));

        return spec;
    }

    public static Specification<NoticeBoard> dateBetween(
            LocalDate from, LocalDate to) {

        return (root, query, cb) -> {

            if (from == null && to == null)
                return cb.conjunction();

            if (from != null && to != null)
                return cb.between(root.get("date"), from, to);

            if (from != null)
                return cb.greaterThanOrEqualTo(root.get("date"), from);

            return cb.lessThanOrEqualTo(root.get("date"), to);
        };
    }
}
