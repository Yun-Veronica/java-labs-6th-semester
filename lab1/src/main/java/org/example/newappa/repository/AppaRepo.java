package org.example.newappa.repository;
import org.example.newappa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppaRepo extends JpaRepository<Tasks, Long> {
}

