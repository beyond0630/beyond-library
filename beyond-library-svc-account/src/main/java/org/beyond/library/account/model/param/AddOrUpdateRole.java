package org.beyond.library.account.model.param;

import java.io.Serializable;

/**
 * @author Beyond
 */
public class AddOrUpdateRole implements Serializable {

    private String code;

    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
