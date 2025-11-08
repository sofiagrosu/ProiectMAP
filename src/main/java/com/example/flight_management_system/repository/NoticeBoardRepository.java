package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.AirlineEmployee;
import com.example.flight_management_system.model.NoticeBoard;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeBoardRepository extends InFileRepository<NoticeBoard>  {

    public NoticeBoardRepository() {
        super("notice_boards.json", NoticeBoard.class);
    }
}
