package com.example.demo;

import com.example.demo.domain.Address;
import com.example.demo.domain.Course;
import com.example.demo.domain.Review;
import com.example.demo.domain.Student;
import com.example.demo.enums.Rating;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.DeleteService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private DeleteService deleteService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Student student1 = new Student("Pepe", "Perez");
		student1.setAddress(new Address("Calle Pepe", 1));
		Student student2 = new Student("Paco", "Lopez");
		student2.setAddress(new Address("Calle Paco", 1));
		Student student3 = new Student("Federico", "Gutierrez");
		student3.setAddress(new Address("Calle Federico", 1));

		studentRepository.insertOrUpdate(student1);
		studentRepository.insertOrUpdate(student2);
		studentRepository.insertOrUpdate(student3);

		Course course = new Course("Learn something");
		course.addEnrolledStudent(student1);
		course.addEnrolledStudent(student2);
		courseRepository.insertOrUpdate(course);

		Course course2 = new Course("Learn some other thing");
		course2.addEnrolledStudent(student2);
		course2.addEnrolledStudent(student3);
		courseRepository.insertOrUpdate(course2);

		Course course3 = new Course("Learn to sing");
		course3.addEnrolledStudent(student1);
		course3.addEnrolledStudent(student3);
		courseRepository.insertOrUpdate(course3);

		logger.info("{} Enrolled students: {}",  courseRepository.findById(1L).getTitle(),  courseRepository.findById(1L).getEnrolledStudents());
		logger.info("{} Enrolled students: {}",  courseRepository.findById(2L).getTitle(),  courseRepository.findById(2L).getEnrolledStudents());
		logger.info("{} Enrolled students: {}",  courseRepository.findById(3L).getTitle(),  courseRepository.findById(3L).getEnrolledStudents());

		Review review = new Review(Rating.THREE, "Not bad at all");
		review.setCourse(courseRepository.findById(1L));
		review.setStudent(studentRepository.findById(1L));


		Review review2 = new Review(Rating.FIVE, "Pretty good");
		review2.setCourse(courseRepository.findById(1L));
		review2.setStudent(studentRepository.findById(2L));

		reviewRepository.insertOrUpdate(review);
		reviewRepository.insertOrUpdate(review2);

//		deleteService.deleteCourse(courseRepository.findById(1L));
//		deleteService.deleteStudent(studentRepository.findById(1L));
		deleteService.deleteReview(reviewRepository.findById(1L));

		logger.info("Student 1 reviews: {}", studentRepository.findById(1L).getReviewedCourses());
		logger.info("Course 1 reviews: {}", courseRepository.findById(1L).getCourseReviews());
	}
}
