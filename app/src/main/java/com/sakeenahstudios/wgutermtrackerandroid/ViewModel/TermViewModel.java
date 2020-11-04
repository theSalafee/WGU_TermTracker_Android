package com.sakeenahstudios.wgutermtrackerandroid.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sakeenahstudios.wgutermtrackerandroid.Database.TermEntity;
import com.sakeenahstudios.wgutermtrackerandroid.Database.TermRepository;

import java.util.List;

public class TermViewModel extends AndroidViewModel {
    private TermRepository repository;
    private LiveData<List<TermEntity>> allTerms;
    private LiveData<List<TermEntity>> allTermsSpinner;

    public TermViewModel(@NonNull Application application) {
        super(application);
        repository = new TermRepository(application);
        allTerms = repository.getAllTerms();
    }

    public void insertTerm(TermEntity term){
        repository.insertTerm(term);
    }

    public void deleteTerm(TermEntity term){
        repository.deleteTerm(term);
    }

    public void deleteAllTerms(){
        repository.deleteAllTerms();
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }

    public LiveData<List<TermEntity>> getAllTermsSpinner() {
        return allTermsSpinner;
    }

    public TermEntity getTermByID(int termID){
        return repository.getTermByID(termID);
    }

}
