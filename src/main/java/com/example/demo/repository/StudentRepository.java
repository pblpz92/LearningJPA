package com.example.demo.repository;

import com.example.demo.domain.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        return em.createQuery("SELECT S FROM Student S").getResultList();
    }

    public void insertOrUpdate(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
