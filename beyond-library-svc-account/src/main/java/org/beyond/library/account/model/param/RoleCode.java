package org.beyond.library.account.model.param;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author Beyond
 */
public class RoleCode implements Serializable {

    @NotEmpty(message = "角色编码不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
