package org.beyond.library.account.model.vo;


import java.io.Serializable;

import lombok.Data;

@Data
public class UserRoleQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Integer id;


    /**
     * 用户 id
     */
    private Long userId;


    /**
     * 角色编码
     */
    private String roleCode;


    /**
     * 是否删除
     */
    private Boolean disabled;

}
