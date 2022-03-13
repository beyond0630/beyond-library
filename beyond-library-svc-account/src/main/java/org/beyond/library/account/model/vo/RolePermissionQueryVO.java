package org.beyond.library.account.model.vo;


import java.io.Serializable;

import lombok.Data;

@Data
public class RolePermissionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;


    /**
     * 角色编码
     */
    private String roleCode;


    /**
     * 权限编码
     */
    private String permissionCode;


    /**
     * 是否禁用
     */
    private Boolean disabled;

}