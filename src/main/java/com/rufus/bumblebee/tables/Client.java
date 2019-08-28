package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CLIENTS", schema = "REPOSITORY")
@Getter
@Setter
@ToString
public class Client implements Serializable {

    @Id
    @SequenceGenerator(name = "client_id", sequenceName = "repository.client_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id")
    @Column(name = "ID", unique = true, nullable = false)
    private int user_id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_REF")
    private List<Container> containers;

}
