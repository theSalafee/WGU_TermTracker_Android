package com.sakeenahstudios.wgutermtrackerandroid.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sakeenahstudios.wgutermtrackerandroid.DAO.AssessmentDAO;
import com.sakeenahstudios.wgutermtrackerandroid.DAO.CourseDAO;
import com.sakeenahstudios.wgutermtrackerandroid.DAO.TermDAO;
import com.sakeenahstudios.wgutermtrackerandroid.Entities.Assessment;
import com.sakeenahstudios.wgutermtrackerandroid.Entities.Course;
import com.sakeenahstudios.wgutermtrackerandroid.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1)
//@TypeConverters({DateConverter.class, CourseStatusConverter.class, AssessmentTypeConverter.class})
public abstract class WGUSchedulerDB extends RoomDatabase {

    private static final String DATABASE_NAME = "AppDatabase.db";
    private static volatile WGUSchedulerDB instance;
    private static final Object LOCK = new Object();

    // Individual model daos
    public abstract TermDAO termDao ();

    public abstract CourseDAO courseDao ();

    public abstract AssessmentDAO assessmentDao ();

    public static WGUSchedulerDB getInstance (Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WGUSchedulerDB.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }
}

