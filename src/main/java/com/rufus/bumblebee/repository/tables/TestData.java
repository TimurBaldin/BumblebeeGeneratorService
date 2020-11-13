package com.rufus.bumblebee.repository.tables;

//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_data", schema = "repository")
@Getter
@Setter
@TypeDefs(@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class))
public class TestData implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "repository.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Type(type = "jsonb")
    @Column(name = "value")
    private String value;

    @Column(name = "container_ref")
    private Long containerRef;
}
