package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Review> courseReviews = new ArrayList<>();

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addEnrolledStudent(Student student) {
        student.addEnrolledCourse(this);
        this.enrolledStudents.add(student);
    }

    public List<Review> getCourseReviews() {
        return courseReviews;
    }

    public void addCourseReview(Review review) {
        this.courseReviews.add(review);
    }

    @Override
    public String toString() {
        return String.format("%d. Course is %s. Enrolled students are: %d", id, title, enrolledStudents.size());
    }
}
