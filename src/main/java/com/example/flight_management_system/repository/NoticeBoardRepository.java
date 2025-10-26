package com.example.flight_management_system.repository;

import com.example.flight_management_system.model.NoticeBoard;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardRepository {
    private List<NoticeBoard> noticeBoards = new ArrayList<>();
    private long nextId = 1;

    public NoticeBoard save(NoticeBoard noticeBoard) {
        if (noticeBoard.getId() == null) {
            noticeBoard.setId(String.valueOf(nextId));
            nextId++;
        }
        noticeBoards.add(noticeBoard);
        return noticeBoard;
    }

    public List<NoticeBoard> findAll() {
        return new ArrayList<>(noticeBoards);
    }

    public NoticeBoard findById(String id) {
        for (NoticeBoard noticeBoard : noticeBoards) {
            if (noticeBoard.getId().equals(id)) {
                return noticeBoard;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        NoticeBoard noticeBoard = findById(id);
        if (noticeBoard != null) {
            noticeBoards.remove(noticeBoard);
            return true;
        }
        return false;
    }
}
