package com.example.flight_management_system.service;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeBoardService {
    private final NoticeBoardRepository noticeBoardRepository;

    @Autowired
    public NoticeBoardService(NoticeBoardRepository noticeBoardRepository) {
        this.noticeBoardRepository = noticeBoardRepository;
    }

    public List<NoticeBoard> findAll() {
        return noticeBoardRepository.findAll();
    }

    public NoticeBoard findById(String id) {
        return noticeBoardRepository.findById(id);
    }

    public void save(NoticeBoard noticeBoard) {
        noticeBoardRepository.save(noticeBoard);
    }

    public boolean delete(NoticeBoard noticeBoard) {
        return noticeBoardRepository.delete(noticeBoard);
    }
}
