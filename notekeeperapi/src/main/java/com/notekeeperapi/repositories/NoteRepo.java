package com.notekeeperapi.repositories;

import com.notekeeperapi.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, String> {
}
