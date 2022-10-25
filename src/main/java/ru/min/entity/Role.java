package ru.min.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Erole name;

    public Role() {
    }

    public Role(Erole name) {

        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Erole getName() {
        return name;
    }

    public void setName(Erole roleName) {
        this.name = roleName;
    }
}
