package com.rufus.bumblebee.tables;

import com.rufus.bumblebee.configurer.enums.ClientStatus;
import com.rufus.bumblebee.configurer.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CLIENTS", schema = "REPOSITORY")
public class Client implements Serializable {

    @Id
    @SequenceGenerator(name = "client_id", sequenceName = "REPOSITORY.client_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id")
    @Column(name = "ID", unique = true, nullable = false)
    private Long userId;

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

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "STATUS")
    private ClientStatus status;

    @Column(name = "ROLE")
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_REF")
    private List<Container> containers;

}
