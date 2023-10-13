package org.example;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "user")
    private List<BlockedContact> blockedContacts;

    @OneToMany(mappedBy = "user")
    private List<SpamReport> spamReports;

    @OneToMany(mappedBy = "user")
    private List<PremiumPlan> premiumPlans;

    @OneToMany(mappedBy = "user")
    private List<Business> businesses;

    // Constructors, getters, setters, and other methods
}
