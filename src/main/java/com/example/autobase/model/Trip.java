package com.example.autobase.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Vehicle vehicle;
    @OneToOne
    private Request request;
    private boolean successful;
    private String completionNote;
}