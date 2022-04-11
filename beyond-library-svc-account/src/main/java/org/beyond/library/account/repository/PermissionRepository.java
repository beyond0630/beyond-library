package org.beyond.library.account.repository;

import java.util.List;

import org.beyond.library.account.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Beyond
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    /**
     * 通过编码查询是否存在
     *
     * @param code code;
     * @return boolean
     */
    boolean existsByCode(String code);

    /**
     * 通过编码查询权限
     *
     * @param code code;
     * @return Permission
     */
    Permission getByCode(String code);

    /**
     * 通过请求 method 和 pattern 查询
     *
     * @param method  method
     * @param pattern pattern
     * @return Permission
     */
    Permission getByMethodAndPattern(String method, String pattern);

    /**
     * @return list
     */
    @Query("select pattern from Permission")
    List<String> listAllPattern();

}
