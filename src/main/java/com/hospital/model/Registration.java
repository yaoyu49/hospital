package com.hospital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String patientName;
    
    @Column(nullable = false)
    private String doctorName;
    
    @Column(nullable = false)
    private String department;
    
    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime cancelledAt;
    
    // Constructors
    public Registration() {}
    
    public Registration(String patientName, String doctorName, String department, 
                       LocalDateTime appointmentTime) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.department = department;
        this.appointmentTime = appointmentTime;
        this.status = RegistrationStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public String getDoctorName() {
        return doctorName;
    }
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
    
    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    
    public RegistrationStatus getStatus() {
        return status;
    }
    
    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
    
    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
    
    public enum RegistrationStatus {
        ACTIVE, CANCELLED, COMPLETED
    }
}