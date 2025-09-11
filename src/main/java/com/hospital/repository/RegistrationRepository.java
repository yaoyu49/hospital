package com.hospital.repository;

import com.hospital.model.Registration;
import com.hospital.model.Registration.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    List<Registration> findByIdInAndStatus(List<Long> ids, RegistrationStatus status);
    
    @Modifying
    @Query("UPDATE Registration r SET r.status = :status, r.cancelledAt = :cancelledAt WHERE r.id IN :ids AND r.status = 'ACTIVE'")
    int batchCancelRegistrations(@Param("ids") List<Long> ids, 
                                @Param("status") RegistrationStatus status,
                                @Param("cancelledAt") LocalDateTime cancelledAt);
}