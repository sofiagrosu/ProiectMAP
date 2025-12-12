package com.example.flight_management_system.service;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class NoticeBoardService {
    private final NoticeBoardRepository repo;
    public NoticeBoardService(NoticeBoardRepository repo){ this.repo = repo; }
    public Page<NoticeBoard> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public NoticeBoard findById(Long id){ return repo.findById(id).orElse(null); }
    public NoticeBoard save(NoticeBoard n){ return repo.save(n); }
    public void delete(Long id){ repo.findById(id).ifPresent(repo::delete); }
}
