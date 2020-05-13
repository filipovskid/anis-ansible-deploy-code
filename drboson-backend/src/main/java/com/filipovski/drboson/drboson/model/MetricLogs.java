package com.filipovski.drboson.drboson.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;


@Entity
@Table(name="metric_logs")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricLogs {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String runId;

    private String projectId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String log;
}
