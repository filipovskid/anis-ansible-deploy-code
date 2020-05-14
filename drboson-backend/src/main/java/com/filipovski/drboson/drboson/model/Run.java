package com.filipovski.drboson.drboson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.filipovski.drboson.drboson.common.RunStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.jackson.JsonObjectSerializer;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"project", "dataset"})
public class Run {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private Dataset dataset;

    @Enumerated(EnumType.STRING)
    private RunStatus status;

    @OneToMany(mappedBy = "run",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
//    @JsonManagedReference
    @JsonIgnore
    private List<DRBosonFile> files;
}
