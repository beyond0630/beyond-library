package org.beyond.library.account.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * 角色权限表
 */
@Data
@Entity
@Table(name = "bla_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编码
     */
    @Column(name = "role_code", nullable = false)
    private String roleCode;

    /**
     * 权限编码
     */
    @Column(name = "permission_code", nullable = false)
    private String permissionCode;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

}
