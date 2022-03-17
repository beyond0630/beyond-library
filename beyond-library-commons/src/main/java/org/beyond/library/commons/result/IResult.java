package org.beyond.library.commons.result;

public interface IResult<T> {

    String getCode();

    String getMessage();

    T getData();

}
