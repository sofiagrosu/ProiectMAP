package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.NoticeBoard;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeBoardRepository implements GenericRepository<NoticeBoard> {
    private List<NoticeBoard> noticeBoards = new ArrayList<>();
    private long nextId = 1;

    @Override
    public void save(NoticeBoard noticeBoard) {
        if (noticeBoard.getId() == null) {
            noticeBoard.setId(String.valueOf(nextId));
            nextId++;
        }
        noticeBoards.add(noticeBoard);

    }
    @Override
    public List<NoticeBoard> findAll() {
        return new ArrayList<>(noticeBoards);
    }
    @Override
    public NoticeBoard findById(String id) {
        for (NoticeBoard noticeBoard : noticeBoards) {
            if (noticeBoard.getId().equals(id)) {
                return noticeBoard;
            }
        }
        return null;
    }
    @Override
    public boolean delete(NoticeBoard noticeBoard) {
        return noticeBoards.remove(noticeBoard);
    }
}
