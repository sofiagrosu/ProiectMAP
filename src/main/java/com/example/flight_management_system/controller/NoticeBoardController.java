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
                "noticeboards/index",   // folder+view
                "noticeboards/form",
                "noticeboards",         // << list model key
                "noticeboard",          // << form model key
                NoticeBoard::new);
    }

    // (opțional – ajută la debug, sigur că folosești exact cheia din view)
    @Override
    @GetMapping
    public String list(org.springframework.ui.Model model) {
        model.addAttribute("noticeboards", service.findAll());
        return "noticeboards/index";
    }

    @Override
    @GetMapping("/new")
    public String form(org.springframework.ui.Model model) {
        model.addAttribute("noticeboard", new NoticeBoard());
        return "noticeboards/form";
    }
}


