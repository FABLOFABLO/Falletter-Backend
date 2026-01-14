package com.example.falletterbackend.falletter.service.admin.superadmin

import com.example.falletterbackend.falletter.dto.admin.superadmin.response.AdminListResponse
import com.example.falletterbackend.falletter.dto.admin.superadmin.response.AdminRequestListResponse
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.exception.admin.AdminAlreadyProcessedException
import com.example.falletterbackend.falletter.facade.admin.AdminFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SuperAdminService(
    private val adminFacade: AdminFacade
) {
    @Transactional(readOnly = true)
    fun getAdminRequests(): List<AdminRequestListResponse> {
        return adminFacade.findAll()
            .map { AdminRequestListResponse.from(it) }
    }

    @Transactional
    fun approveAdmin(adminId: Long) {
        val admin = adminFacade.getById(adminId)
        val currentAdmin = adminFacade.getCurrentAdmin()

        if (admin.status != AdminStatus.PENDING) {
            throw AdminAlreadyProcessedException
        }

        admin.status = AdminStatus.APPROVED
        admin.approvedBy = currentAdmin
        admin.processedAt = LocalDateTime.now()

        adminFacade.save(admin)
    }

    @Transactional
    fun rejectAdmin(adminId: Long) {
        val admin = adminFacade.getById(adminId)
        val currentAdmin = adminFacade.getCurrentAdmin()

        if (admin.status != AdminStatus.PENDING) {
            throw AdminAlreadyProcessedException
        }

        admin.status = AdminStatus.REJECTED
        admin.approvedBy = currentAdmin
        admin.processedAt = LocalDateTime.now()

        adminFacade.save(admin)
    }

    @Transactional(readOnly = true)
    fun getApprovedAdmins(): List<AdminListResponse> {
        return adminFacade.findAllByStatus(AdminStatus.APPROVED)
            .map { AdminListResponse.from(it) }
    }
}
