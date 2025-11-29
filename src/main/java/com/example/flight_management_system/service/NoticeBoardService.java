package com.example.flight_management_system.service;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeBoardService {
    private final NoticeBoardRepository repo;
    public NoticeBoardService(NoticeBoardRepository repo){ this.repo = repo; }
    public List<NoticeBoard> findAll(){ return repo.findAll(); }
    public NoticeBoard findById(String id){ return repo.findById(id).orElse(null); }
    public NoticeBoard save(NoticeBoard n){ return repo.save(n); }
    public void delete(String id){ repo.findById(id).ifPresent(repo::delete); }
}
