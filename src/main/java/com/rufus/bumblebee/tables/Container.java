package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "containers", schema = "repository")
@Getter
@Setter
public class Container implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "container_id", sequenceName = "repository.container_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "container_ref")
    private List<TestData> data = new ArrayList<>();

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "user_ref")
    private String userRef;

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", data=" + data +
                ", updateDate=" + updateDate +
                '}';
    }

}
