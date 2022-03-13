package org.beyond.library.account.model.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class PermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @NotNull(message = "id can not null")
    private Integer id;


    /**
     * 权限编码
     */
    @NotNull(message = "code can not null")
    private String code;


    /**
     * 权限名称
     */
    private String name;


    /**
     * 访问地址
     */
    @NotNull(message = "url can not null")
    private String url;


    /**
     * 是否禁用
     */
    @NotNull(message = "disabled can not null")
    private Boolean disabled;

}
