package com.example.demo.domain;

import com.example.demo.enums.Rating;
import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Rating rating;

    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Review() {
    }

    public Review(Rating rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Long getId() {
        return id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        course.addCourseReview(this);
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        student.addReviewedCourse(this);
        this.student = student;
    }

    @Override
    public String toString() {
        return String.format("%d. Rating is %s. %s", id, rating, reviewText);
    }
}
