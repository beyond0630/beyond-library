package org.beyond.library.account.model.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class UserRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @NotNull(message = "id can not null")
    private Integer id;


    /**
     * 用户 id
     */
    @NotNull(message = "userId can not null")
    private Long userId;


    /**
     * 角色编码
     */
    @NotNull(message = "roleCode can not null")
    private String roleCode;


    /**
     * 是否删除
     */
    @NotNull(message = "disabled can not null")
    private Boolean disabled;

}
