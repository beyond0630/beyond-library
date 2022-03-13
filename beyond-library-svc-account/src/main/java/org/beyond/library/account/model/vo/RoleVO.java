package org.beyond.library.account.model.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @NotNull(message = "id can not null")
    private Integer id;


    /**
     * 角色编码
     */
    @NotNull(message = "code can not null")
    private String code;


    /**
     * 角色名称
     */
    private String name;


    /**
     * 是否禁用
     */
    @NotNull(message = "disabled can not null")
    private Boolean disabled;

}
