package com.example.autobase.repository;
import com.example.autobase.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DriverRepository extends JpaRepository<Driver, Long> {
}