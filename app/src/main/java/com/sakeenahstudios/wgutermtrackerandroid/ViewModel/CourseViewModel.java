package com.sakeenahstudios.wgutermtrackerandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sakeenahstudios.wgutermtrackerandroid.Database.CourseEntity;
import com.sakeenahstudios.wgutermtrackerandroid.Database.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepository repository;
    public LiveData<List<CourseEntity>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    public void insertCourse(CourseEntity course){
        repository.insertCourse(course);
    }

    public void deleteCourse(CourseEntity course){
        repository.deleteCourse(course);
    }

    public void deleteAllCourses(){
        repository.deleteAllCourses();
    }

    public LiveData<List<CourseEntity>> getAllCourses() {
        return allCourses;
    }

    public CourseEntity getCourseByID(int courseID){
        return repository.getCourseByID(courseID);
    }
    public LiveData<List<CourseEntity>> getCoursesByTerm(int currentTermID){
        return repository.getCoursesByTerm(currentTermID);
    }
    public void setCurrentTerm(int currentTerm){
        allCourses = repository.getCoursesByTerm(currentTerm);
    }
}
