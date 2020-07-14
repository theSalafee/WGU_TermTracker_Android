package com.sakeenahstudios.wgutermtrackerandroid.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Term {

    @PrimaryKey
    private int termID;

    public void setTermID (int termID) {
        this.termID = termID;
    }

    public int getTermID () {
        return termID;
    }
}

