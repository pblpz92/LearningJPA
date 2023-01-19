package com.example.demo.repository;

import com.example.demo.domain.Address;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {

    @Autowired
    EntityManager em;

    public Address findById(Long id) {
        return em.find(Address.class, id);
    }

    public void insertOrUpdate(Address address) {
        if (address.getId() == null) {
            em.persist(address);
        } else {
            em.merge(address);
        }
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
