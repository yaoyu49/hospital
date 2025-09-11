package com.hospital.service;

import com.hospital.model.Registration;
import com.hospital.model.Registration.RegistrationStatus;
import com.hospital.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Transactional
    public BatchCancelResult cancelRegistrations(List<Long> registrationIds) {
        // Find active registrations that can be cancelled
        List<Registration> activeRegistrations = registrationRepository
            .findByIdInAndStatus(registrationIds, RegistrationStatus.ACTIVE);
        
        if (activeRegistrations.isEmpty()) {
            return new BatchCancelResult(0, registrationIds.size(), "No active registrations found with the provided IDs");
        }
        
        // Extract IDs of active registrations
        List<Long> activeIds = activeRegistrations.stream()
            .map(Registration::getId)
            .toList();
        
        // Perform batch cancellation
        int cancelledCount = registrationRepository.batchCancelRegistrations(
            activeIds, 
            RegistrationStatus.CANCELLED, 
            LocalDateTime.now()
        );
        
        int failedCount = registrationIds.size() - cancelledCount;
        
        return new BatchCancelResult(cancelledCount, failedCount, 
            String.format("Successfully cancelled %d registrations, failed %d", 
                cancelledCount, failedCount));
    }
    
    public static class BatchCancelResult {
        private final int successCount;
        private final int failureCount;
        private final String message;
        
        public BatchCancelResult(int successCount, int failureCount, String message) {
            this.successCount = successCount;
            this.failureCount = failureCount;
            this.message = message;
        }
        
        public int getSuccessCount() {
            return successCount;
        }
        
        public int getFailureCount() {
            return failureCount;
        }
        
        public String getMessage() {
            return message;
        }
    }
}