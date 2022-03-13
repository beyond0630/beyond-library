package org.beyond.library.account.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * 权限表
 */
@Data
@Entity
@Table(name = "bla_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限编码
     */
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 访问地址
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

}
