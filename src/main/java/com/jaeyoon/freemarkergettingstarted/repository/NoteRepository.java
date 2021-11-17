package com.jaeyoon.freemarkergettingstarted.repository;

import com.jaeyoon.freemarkergettingstarted.domain.Note;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long>, JpaSpecificationExecutor<Note> {
}