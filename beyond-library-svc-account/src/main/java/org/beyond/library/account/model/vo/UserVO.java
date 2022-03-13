package org.beyond.library.account.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @NotNull(message = "id can not null")
    private Long id;


    /**
     * 用户名
     */
    @NotNull(message = "username can not null")
    private String username;


    /**
     * 密码
     */
    @NotNull(message = "password can not null")
    private String password;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 注册时间
     */
    @NotNull(message = "registerTime can not null")
    private LocalDateTime registerTime;


    /**
     * 是否禁用
     */
    @NotNull(message = "disabled can not null")
    private Boolean disabled;


    /**
     * 是否删除
     */
    @NotNull(message = "deleted can not null")
    private Boolean deleted;


    /**
     * 创建人
     */
    @NotNull(message = "createdBy can not null")
    private Long createdBy;


    /**
     * 创建时间
     */
    @NotNull(message = "createdAt can not null")
    private LocalDateTime createdAt;


    /**
     * 更新人
     */
    @NotNull(message = "modifiedBy can not null")
    private Long modifiedBy;


    /**
     * 更新时间
     */
    @NotNull(message = "modifiedAt can not null")
    private LocalDateTime modifiedAt;

}
