package com.sakeenahstudios.wgutermtrackerandroid.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Assessment {
    @PrimaryKey
    private int assessmentID;

    public void setAssessmentID (int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getAssessmentID () {
        return assessmentID;
    }
}
