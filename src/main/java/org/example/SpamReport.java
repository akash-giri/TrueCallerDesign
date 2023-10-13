package org.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spamReports")
public class SpamReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    // Constructors, getters, setters, and other methods
}
