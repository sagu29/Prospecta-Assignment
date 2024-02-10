package com.prospecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospecta.entity.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    // You can add custom query methods if needed
}

