package org.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, setters, and other methods
}
