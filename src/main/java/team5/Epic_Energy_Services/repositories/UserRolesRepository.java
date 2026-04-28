package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.UserRole;

import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, UUID> {
    void deleteByUserId(UUID userId);
    UserRole findByUserId(UUID userId);
}