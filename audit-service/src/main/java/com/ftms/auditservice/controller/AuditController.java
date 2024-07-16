package com.ftms.auditservice.controller;

import com.ftms.auditservice.model.Transaction;
import com.ftms.auditservice.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/audits")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping
    public List<Transaction> getAllAudits() {
        return auditService.findAll();
    }
}