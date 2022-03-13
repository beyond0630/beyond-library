package org.beyond.library.account.model.vo;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateVO extends UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
