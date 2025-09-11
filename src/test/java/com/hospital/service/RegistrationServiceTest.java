package com.hospital.service;

import com.hospital.model.Registration;
import com.hospital.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RegistrationServiceTest {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private RegistrationService registrationService;
    
    @BeforeEach
    public void setUp() {
        registrationRepository.deleteAll();
    }
    
    @Test
    public void testBatchCancelRegistrations() {
        // Create test registrations
        Registration reg1 = new Registration("Patient1", "Doctor1", "Cardiology", 
            LocalDateTime.now().plusDays(1));
        Registration reg2 = new Registration("Patient2", "Doctor2", "Neurology", 
            LocalDateTime.now().plusDays(2));
        Registration reg3 = new Registration("Patient3", "Doctor3", "Orthopedics", 
            LocalDateTime.now().plusDays(3));
        
        registrationRepository.saveAll(Arrays.asList(reg1, reg2, reg3));
        
        // Test batch cancellation
        List<Long> idsToCancel = Arrays.asList(reg1.getId(), reg2.getId());
        RegistrationService.BatchCancelResult result = registrationService.cancelRegistrations(idsToCancel);
        
        assertEquals(2, result.getSuccessCount());
        assertEquals(0, result.getFailureCount());
        
        // Verify registrations are cancelled
        Registration updatedReg1 = registrationRepository.findById(reg1.getId()).orElse(null);
        Registration updatedReg2 = registrationRepository.findById(reg2.getId()).orElse(null);
        Registration updatedReg3 = registrationRepository.findById(reg3.getId()).orElse(null);
        
        assertNotNull(updatedReg1);
        assertNotNull(updatedReg2);
        assertNotNull(updatedReg3);
        
        assertEquals(Registration.RegistrationStatus.CANCELLED, updatedReg1.getStatus());
        assertEquals(Registration.RegistrationStatus.CANCELLED, updatedReg2.getStatus());
        assertEquals(Registration.RegistrationStatus.ACTIVE, updatedReg3.getStatus());
        
        assertNotNull(updatedReg1.getCancelledAt());
        assertNotNull(updatedReg2.getCancelledAt());
        assertNull(updatedReg3.getCancelledAt());
    }
    
    @Test
    public void testBatchCancelWithNonExistentIds() {
        List<Long> idsToCancel = Arrays.asList(999L, 1000L);
        RegistrationService.BatchCancelResult result = registrationService.cancelRegistrations(idsToCancel);
        
        assertEquals(0, result.getSuccessCount());
        assertEquals(2, result.getFailureCount());
    }
}