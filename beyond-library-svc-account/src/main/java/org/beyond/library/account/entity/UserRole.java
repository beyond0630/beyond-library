package org.beyond.library.account.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * 用户角色表
 */
@Data
@Entity
@Table(name = "bla_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户 id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 角色编码
     */
    @Column(name = "role_code", nullable = false)
    private String roleCode;

    /**
     * 是否删除
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

}
