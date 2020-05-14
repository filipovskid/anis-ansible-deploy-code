package com.filipovski.drboson.drboson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="files")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"run"})
public class DRBosonFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private Run run;
}
