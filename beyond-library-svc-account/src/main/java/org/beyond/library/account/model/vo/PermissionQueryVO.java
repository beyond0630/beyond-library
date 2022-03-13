package org.beyond.library.account.model.vo;


import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Integer id;


    /**
     * 权限编码
     */
    private String code;


    /**
     * 权限名称
     */
    private String name;


    /**
     * 访问地址
     */
    private String url;


    /**
     * 是否禁用
     */
    private Boolean disabled;

}
