package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.domain.Review;
import com.example.demo.domain.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public void deleteCourse(Course course) {
        for (Student s: studentRepository.findAll()) {
            s.getEnrolledCourses().remove(course);
        }
        courseRepository.deleteById(course.getId());
    }

    public void deleteStudent(Student student) {
        for (Course c: student.getEnrolledCourses()) {
            c.getEnrolledStudents().remove(student);
        }
        studentRepository.deleteById(student.getId());
    }

    public void deleteReview(Review review) {
        Student student = studentRepository.findById(review.getStudent().getId());
        student.getReviewedCourses().remove(review);

        Course course = courseRepository.findById(review.getCourse().getId());
        course.getCourseReviews().remove(review);

        reviewRepository.deleteById(review.getId());
    }
}
