package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private StudentCard studentCard;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> enrolledCourses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Review> reviewedCourses = new ArrayList();

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCard = new StudentCard();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addEnrolledCourse(Course course) {
        this.enrolledCourses.add(course);
    }

    public List<Review> getReviewedCourses() {
        return reviewedCourses;
    }

    public void addReviewedCourse(Review review) {
        this.reviewedCourses.add(review);
    }

    @Override
    public String toString() {
        return String.format("%d. Name is: %s %s.", id, firstName, lastName);
    }
}
