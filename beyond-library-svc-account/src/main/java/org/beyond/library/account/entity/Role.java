package org.beyond.library.account.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * 角色表
 */
@Data
@Entity
@Table(name = "bla_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编码
     */
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

}
