package com.filipovski.drboson.drboson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.filipovski.drboson.drboson.common.RunStatus;
import lombok.*;
import org.springframework.boot.jackson.JsonObjectSerializer;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"project", "dataset"})
public class Run {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Dataset dataset;

    @Enumerated(EnumType.STRING)
    private RunStatus status;
}
