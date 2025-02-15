package com.jgiga.SpringSecurity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@AllArgsConstructor
@Builder
@Table(name = "users")
@Data
public class User {
    @Id
    private int id;
    private String username;
}
