package com.example.flight_management_system.service;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardService extends CrudService<NoticeBoard> {

    @Autowired
    public NoticeBoardService(NoticeBoardRepository noticeBoardRepository) {
        super(noticeBoardRepository);
    }
}
