package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.Flight;
import com.example.flight_management_system.model.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long>, JpaSpecificationExecutor<NoticeBoard> {
}
