package org.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "globalDirectory")
public class GlobalDirectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    // Constructors, getters, setters, and other methods
}
