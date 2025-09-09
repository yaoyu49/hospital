package com.example.hospital.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity @Table(name = "sys_user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false)
  private String passwordHash;
  private String fullName;
  private String departmentCode;
  private Boolean enabled;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "sys_user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role")
  private Set<String> roles; // e.g. ADMIN, DOCTOR, NURSE
}