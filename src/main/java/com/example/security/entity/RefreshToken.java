package com.example.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String refreshToken;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public RefreshToken(String refreshToken, Instant expiryDate, User user) {
        this.refreshToken = refreshToken;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}
