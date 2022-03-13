package org.beyond.library.account.model.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class RolePermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;


    /**
     * 角色编码
     */
    @NotNull(message = "roleCode can not null")
    private String roleCode;


    /**
     * 权限编码
     */
    @NotNull(message = "permissionCode can not null")
    private String permissionCode;


    /**
     * 是否禁用
     */
    @NotNull(message = "disabled can not null")
    private Boolean disabled;

}
