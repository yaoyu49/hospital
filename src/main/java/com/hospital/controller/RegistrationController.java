package com.hospital.controller;

import com.hospital.service.RegistrationService;
import com.hospital.service.RegistrationService.BatchCancelResult;
import com.hospital.repository.RegistrationRepository;
import com.hospital.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @PostMapping("/cancelBatch")
    public ResponseEntity<Map<String, Object>> cancelBatch(@RequestBody BatchCancelRequest request) {
        try {
            // Validate input
            if (request.getRegistrationIds() == null || request.getRegistrationIds().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of(
                        "success", false,
                        "message", "Registration IDs list cannot be empty",
                        "successCount", 0,
                        "failureCount", 0
                    ));
            }
            
            // Process batch cancellation
            BatchCancelResult result = registrationService.cancelRegistrations(request.getRegistrationIds());
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", result.getMessage(),
                "successCount", result.getSuccessCount(),
                "failureCount", result.getFailureCount()
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "success", false,
                    "message", "Error processing batch cancellation: " + e.getMessage(),
                    "successCount", 0,
                    "failureCount", 0
                ));
        }
    }
    
    // Test endpoint to create sample registrations for demonstration
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createRegistration(@RequestBody CreateRegistrationRequest request) {
        try {
            Registration registration = new Registration(
                request.getPatientName(),
                request.getDoctorName(),
                request.getDepartment(),
                request.getAppointmentTime()
            );
            
            Registration saved = registrationRepository.save(registration);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Registration created successfully",
                "registrationId", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "success", false,
                    "message", "Error creating registration: " + e.getMessage()
                ));
        }
    }
    
    public static class CreateRegistrationRequest {
        private String patientName;
        private String doctorName;
        private String department;
        private LocalDateTime appointmentTime;
        
        public CreateRegistrationRequest() {}
        
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
    }
    
    public static class BatchCancelRequest {
        private List<Long> registrationIds;
        
        public BatchCancelRequest() {}
        
        public BatchCancelRequest(List<Long> registrationIds) {
            this.registrationIds = registrationIds;
        }
        
        public List<Long> getRegistrationIds() {
            return registrationIds;
        }
        
        public void setRegistrationIds(List<Long> registrationIds) {
            this.registrationIds = registrationIds;
        }
    }
}