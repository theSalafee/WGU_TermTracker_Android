package com.sakeenahstudios.wgutermtrackerandroid.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {

    @PrimaryKey
    private int courseID;

    public void setCourseID (int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID () {
        return courseID;
    }
}
