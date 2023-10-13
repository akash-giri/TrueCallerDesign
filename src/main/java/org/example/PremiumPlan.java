package org.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "premiumPlans")
public class PremiumPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, setters, and other methods
}
