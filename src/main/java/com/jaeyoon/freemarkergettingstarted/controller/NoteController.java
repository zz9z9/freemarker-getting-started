package com.jaeyoon.freemarkergettingstarted.controller;

import com.jaeyoon.freemarkergettingstarted.domain.Note;
import com.jaeyoon.freemarkergettingstarted.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private NoteService noteService;

    @Value("${msg.title}")
    private String title;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/notes")
    public String getNotes(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Note> notes = noteService.findAll(pageNumber, ROW_PER_PAGE);

        long count = noteService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("notes", notes);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "note-list";
    }
//
//    @GetMapping(value = "/notes/{noteId}")
//    public String getNoteById(Model model, @PathVariable long noteId) {  }
//
//    @GetMapping(value = {"/notes/add"})
//    public String showAddNote(Model model) {  }
//
//    @PostMapping(value = "/notes/add")
//    public String addNote(Model model,
//                          @ModelAttribute("note") Note note) {  }
//
//    @GetMapping(value = {"/notes/{noteId}/edit"})
//    public String showEditNote(Model model, @PathVariable long noteId) {  }
//
//    @PostMapping(value = {"/notes/{noteId}/edit"})
//    public String updateNote(Model model,
//                             @PathVariable long noteId,
//                             @ModelAttribute("note") Note note) {  }
//
//    @GetMapping(value = {"/notes/{noteId}/delete"})
//    public String showDeleteNoteById(
//            Model model, @PathVariable long noteId) {  }
//
//    @PostMapping(value = {"/notes/{noteId}/delete"})
//    public String deleteNoteById(
//            Model model, @PathVariable long noteId) {  }
}
