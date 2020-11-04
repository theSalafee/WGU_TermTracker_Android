package com.sakeenahstudios.wgutermtrackerandroid.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sakeenahstudios.wgutermtrackerandroid.Database.NoteEntity;
import com.sakeenahstudios.wgutermtrackerandroid.Database.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    public LiveData<List<NoteEntity>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insertNote(NoteEntity note){
        repository.insertNote(note);
    }

    public void deleteNote(NoteEntity note){
        repository.deleteNote(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public NoteEntity getNoteByID(int noteID){
        return repository.getNoteByID(noteID);
    }
    public LiveData<List<NoteEntity>> getNotesByAssessment(int currentAssessmentID){
        return repository.getNotesByAssessment(currentAssessmentID);
    }
    public void setCurrentAssessment(int currentAssessment){
        allNotes = repository.getNotesByAssessment(currentAssessment);
    }
}
