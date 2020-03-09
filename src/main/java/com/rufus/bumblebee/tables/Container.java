package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTAINERS", schema = "REPOSITORY")
@Getter
@Setter
public class Container {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "container_id", sequenceName = "REPOSITORY.container_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_DATE")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTAINER_REF")
    private List<TestData> data = new ArrayList<>();

    @Column(name = "CLIENT_REF")
    private Long clientRef;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTAINER_REF")
    private List<Report> reports = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        return new EqualsBuilder()
                .append(id, container.id)
                .append(name, container.name)
                .append(clientRef, container.clientRef)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(clientRef)
                .toHashCode();
    }
}
