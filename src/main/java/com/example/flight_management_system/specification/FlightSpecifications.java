package com.example.flight_management_system.specification;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.specification.filter.FlightFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FlightSpecifications {

        private FlightSpecifications() {}

        public static Specification<Flight> withFilter(FlightFilter f) {
            // spec "TRUE" (nu filtrează nimic)
            Specification<Flight> spec = (root, query, cb) -> cb.conjunction();

            if (f == null) return spec;

            spec = spec.and(nameContains(f.getName()));
            spec = spec.and(gateContains(f.getGateNumber()));
            spec = spec.and(airplaneIdEquals(f.getAirplaneId()));
            spec = spec.and(noticeBoardIdEquals(f.getNoticeBoardId()));

            return spec;
        }

        // --------- filtre concrete ---------

        public static Specification<Flight> nameContains(String value) {
            return (root, query, cb) -> {
                if (value == null || value.isBlank()) return cb.conjunction();
                return cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%");
            };
        }

        public static Specification<Flight> gateContains(String value) {
            return (root, query, cb) -> {
                if (value == null || value.isBlank()) return cb.conjunction();
                return cb.like(cb.lower(root.get("gateNumber")), "%" + value.toLowerCase() + "%");
            };
        }

        /**
         * Flight.airplane este ManyToOne, deci filtrăm pe root.get("airplane").get("id")
         */
        public static Specification<Flight> airplaneIdEquals(Long airplaneId) {
            return (root, query, cb) -> {
                if (airplaneId == null) return cb.conjunction();
                return cb.equal(root.get("airplane").get("id"), airplaneId);
            };
        }

        /**
         * Flight.noticeBoard este ManyToOne
         */
        public static Specification<Flight> noticeBoardIdEquals(Long noticeBoardId) {
            return (root, query, cb) -> {
                if (noticeBoardId == null) return cb.conjunction();
                return cb.equal(root.get("noticeBoard").get("id"), noticeBoardId);
            };
        }
}
