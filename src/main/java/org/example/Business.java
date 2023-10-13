package org.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String businessNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, setters, and other methods
}
