package com.rufus.bumblebee.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TEST_DATA", schema = "REPOSITORY")
public class TestData implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "repository.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CONTAINER_REF")
    private Long containerRef;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getContainerRef() {
        return containerRef;
    }

    public void setContainerRef(Long containerRef) {
        this.containerRef = containerRef;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", containerRef=" + containerRef +
                '}';
    }

}
