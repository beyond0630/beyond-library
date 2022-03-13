package org.beyond.library.account.repository;

import org.beyond.library.account.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer>, JpaSpecificationExecutor<RolePermission> {

}
