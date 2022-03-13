package org.beyond.library.account.model.vo;


import java.io.Serializable;

import lombok.Data;

@Data
public class RoleQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Integer id;


    /**
     * 角色编码
     */
    private String code;


    /**
     * 角色名称
     */
    private String name;


    /**
     * 是否禁用
     */
    private Boolean disabled;

}