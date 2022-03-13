package org.beyond.library.account.model.vo;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;


    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 注册时间
     */
    private LocalDateTime registerTime;


    /**
     * 是否禁用
     */
    private Boolean disabled;


    /**
     * 是否删除
     */
    private Boolean deleted;


    /**
     * 创建人
     */
    private Long createdBy;


    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


    /**
     * 更新人
     */
    private Long modifiedBy;


    /**
     * 更新时间
     */
    private LocalDateTime modifiedAt;

}
