package org.beyond.library.framework.exception;


import org.beyond.library.commons.constant.Code;

/**
 * @author Beyond
 */
public class DataNotFoundException extends RuntimeException {

    private String code;

    public DataNotFoundException() {
        this("找不到数据");
    }

    public DataNotFoundException(final String message) {
        super(message);
        this.code = Code.FAILED;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
