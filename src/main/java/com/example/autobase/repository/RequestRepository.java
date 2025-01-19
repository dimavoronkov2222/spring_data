package com.example.autobase.repository;
import com.example.autobase.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RequestRepository extends JpaRepository<Request, Long> {}