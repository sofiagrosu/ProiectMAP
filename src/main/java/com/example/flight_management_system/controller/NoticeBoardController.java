package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.service.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticeboards")
public class NoticeBoardController extends AbstractCrudController<NoticeBoard> {

    public NoticeBoardController(CrudService<NoticeBoard> service) {
        super(service,
                "/noticeboards",
                "noticeboards/index",
                "noticeboards/form",
                "noticeBoards",
                "noticeBoard",
                NoticeBoard::new);
    }

    @Override
    @PostMapping
    public String create(@ModelAttribute("noticeBoard") NoticeBoard noticeBoard) {
        service.save(noticeBoard);
        return "redirect:/noticeboards";
    }
}
