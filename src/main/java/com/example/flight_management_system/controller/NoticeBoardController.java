package com.example.flight_management_system.controller;

import com.example.flight_management_system.model.Luggage;
import com.example.flight_management_system.model.NoticeBoard;
import com.example.flight_management_system.repository.NoticeBoardRepository;
import com.example.flight_management_system.service.NoticeBoardService;
import com.example.flight_management_system.specification.filter.NoticeBoardFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticeboards")
public class NoticeBoardController {

    @Autowired
    private NoticeBoardService noticeBoardService;

    @GetMapping
    public String listNoticeBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @ModelAttribute("filter") NoticeBoardFilter filter,
            Model model)
    {

        Sort sort = buildSort(sortBy, ascending);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<NoticeBoard> objectPage =  noticeBoardService.search(filter,pageable);
        model.addAttribute("noticeBoards", objectPage.getContent());     // pentru tabel
        model.addAttribute("page", objectPage);                     // pentru paginare (opțional)
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("ascending", ascending);
        model.addAttribute("noticeBoards", objectPage.getContent());

        return "noticeboards/index";


    }
    private Sort buildSort(String sortBy, boolean ascending) {
        Sort.Direction dir = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;

        return switch (sortBy) {
            case "id" -> Sort.by(dir, "id");
            case "date" -> Sort.by(dir, "date");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };
    }

    @GetMapping("/new")
    public String createNoticeBoardForm(Model model) {
        model.addAttribute("noticeBoard", new NoticeBoard());
        return "noticeboards/form";
    }

    @PostMapping("/save")
    public String saveNoticeBoard(@Valid @ModelAttribute("noticeBoard") NoticeBoard noticeBoard,
                                  BindingResult result) {
        if (result.hasErrors()) return "noticeboards/form";


        noticeBoardService.save(noticeBoard);
        return "redirect:/noticeboards";
    }

    @GetMapping("/edit/{id}")
    public String editNoticeBoardForm(@PathVariable("id") Long id, Model model) {
        NoticeBoard noticeBoard = noticeBoardService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeboards/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNoticeBoard(@PathVariable("id") Long id) {
        NoticeBoard noticeBoard = noticeBoardService.findById(id);
                //.orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        noticeBoardService.delete(noticeBoard.getId());
        return "redirect:/noticeboards";
    }

    @GetMapping("/details/{id}")
    public String noticeBoardDetails(@PathVariable("id") Long id, Model model) {
        NoticeBoard noticeBoard = noticeBoardService.findById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid NoticeBoard Id:" + id));
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeboards/details";
    }
}