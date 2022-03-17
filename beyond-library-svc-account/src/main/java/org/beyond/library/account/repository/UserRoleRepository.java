package org.beyond.library.account.repository;

import java.util.List;

import org.beyond.library.account.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Beyond
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

    /**
     * 通过 userId 查询
     *
     * @param userId user Id
     * @return list
     */
    List<UserRole> getAllByUserId(long userId);

    /**
     * 通过角色删除
     *
     * @param roleCode 角色 code
     * @return 删除行数
     */
    int deleteByRoleCode(String roleCode);

    /**
     * 判断是否已存在记录
     *
     * @param userId   user Id
     * @param roleCode 角色编码
     * @return
     */
    boolean existsByUserIdAndRoleCode(long userId, String roleCode);

}
