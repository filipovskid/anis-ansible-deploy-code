package com.filipovski.drboson.drboson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dataset {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
