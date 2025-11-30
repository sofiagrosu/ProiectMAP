package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticeboards")
public class NoticeBoardController {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    @GetMapping
    public String listNoticeBoards(Model model) {
        model.addAttribute("noticeboards", noticeBoardRepository.findAll());
        return "noticeboards/index";
    }

    @GetMapping("/new")
    public String createNoticeBoardForm(Model model) {
        model.addAttribute("noticeBoard", new NoticeBoard());
        return "noticeboards/form";
    }

    @PostMapping("/save")
    public String saveNoticeBoard(@Valid @ModelAttribute("noticeBoard") NoticeBoard noticeBoard,
                                  BindingResult result) {
        // JSR-303 Validation check (Requirement 1.6 a)
        if (result.hasErrors()) return "noticeboards/form";

        // No complex Business Validation implemented here (assuming Date uniqueness is not required)

        noticeBoardRepository.save(noticeBoard);
        return "redirect:/noticeboards";
    }

    @GetMapping("/edit/{id}")
    public String editNoticeBoardForm(@PathVariable("id") Long id, Model model) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeboards/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNoticeBoard(@PathVariable("id") Long id) {
        // GlobalExceptionHandler will catch DataIntegrityViolationException if relations exist (Requirement 1.6 b & c)
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        noticeBoardRepository.delete(noticeBoard);
        return "redirect:/noticeboards";
    }

    @GetMapping("/details/{id}")
    public String noticeBoardDetails(@PathVariable("id") Long id, Model model) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeboards/details";
    }
}