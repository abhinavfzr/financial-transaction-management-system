package com.ftms.auditservice.repository;

import com.ftms.auditservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Transaction, Long> {
}
