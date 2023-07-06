package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "role")

public class Role {
    @Id
    @SequenceGenerator(name = "role_sequences",sequenceName = "role_sequences",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "role_sequences")
    private Integer id;

    private String name;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
