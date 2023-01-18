package com.example.demo.repository;

import com.example.demo.domain.Review;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

    @Autowired
    EntityManager em;

    public void insertOrUpdate(Review review) {
        if (review.getId() == null) {
            em.persist(review);
        } else {
            em.merge(review);
        }
    }

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
