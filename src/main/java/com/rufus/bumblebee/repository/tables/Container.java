package com.rufus.bumblebee.repository.tables;

import com.rufus.bumblebee.repository.ContainerStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "containers", schema = "repository")
@Getter
@Setter
public class Container implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "containers_id", sequenceName = "repository.containers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "containers_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private LocalDateTime date;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "authenticated")
    private Boolean isAuthenticated;

    @Enumerated(EnumType.STRING)
    private ContainerStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "container")
    private List<TestData> data;
}
