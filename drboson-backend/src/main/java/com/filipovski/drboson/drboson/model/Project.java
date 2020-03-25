package com.filipovski.drboson.drboson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    private String description;

    private String repository;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "project",
            orphanRemoval = true)
    @JsonManagedReference
    private Set<Dataset> datasets;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private Set<Run> runs;
}