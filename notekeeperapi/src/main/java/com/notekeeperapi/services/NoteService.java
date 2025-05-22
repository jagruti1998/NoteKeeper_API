package com.notekeeperapi.services;

import com.notekeeperapi.entities.Note;
import com.notekeeperapi.repositories.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    //@Autowired
    private NoteRepo noteRepo;

    //constructorization
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    //save
    @CachePut(value = "notes", key="#note.id")
    public Note createNote(Note note){
        note.setId(UUID.randomUUID().toString());
        return noteRepo.save(note);
    }

    //get
    public List<Note> getAll(){
        return noteRepo.findAll();
    }

    @Cacheable(value = "notes", key = "#noteId")
    //it will check if the data for the given key exists in redis or not
    //redis h to id wise data ko fetch krega and users is cache name
    //agar data present h it will retrieve from redis if not fetch from db and put in redis for future use
    public Note getById(String noteId){
        return noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));

    }

    //update

    public Note updateNote(String noteId, Note note){
        Note note1 = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));
        note1.setTitle(note.getTitle());
        note1.setContent(note.getContent());
         note1.setLive(note.isLive());

         return noteRepo.save(note1);

    }

    //delete
@CacheEvict(value = "notes", key = "#noteId")
    public void delete(String noteId){
        Note note1 = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));
        noteRepo.delete(note1);

    }
}
