package com.jaeyoon.freemarkergettingstarted.service;

import com.jaeyoon.freemarkergettingstarted.domain.Note;
import com.jaeyoon.freemarkergettingstarted.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    private boolean existsById(Long id) {
        return noteRepository.existsById(id);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public List<Note> findAll(int pageNumber, int rowPerPage) {
        List<Note> notes = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("updatedOn").descending());
        noteRepository.findAll(sortedByLastUpdateDesc).forEach(notes::add);
        return notes;
    }

    public Note save(Note note) throws Exception {
        if (note.getTitle().isEmpty()) {
            throw new Exception("Title is required");
        }
        if (note.getContent().isEmpty()) {
            throw new Exception("Content is required");
        }
        if (note.getId() != null && existsById(note.getId())) {
            throw new Exception("Note with id: " + note.getId() + " already exists");
        }
        return noteRepository.save(note);
    }

    public void update(Note note) throws Exception {
        if (StringUtils.isEmpty(note.getTitle())) {
            throw new Exception("Title is required");
        }
        if (StringUtils.isEmpty(note.getContent())) {
            throw new Exception("Content is required");
        }
        if (!existsById(note.getId())) {
            throw new Exception("Cannot find Note with id: " + note.getId());
        }
        noteRepository.save(note);
    }

    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find Note with id: " + id);
        }
        else {
            noteRepository.deleteById(id);
        }
    }

    public Long count() {
        return noteRepository.count();
    }
}