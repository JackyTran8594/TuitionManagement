package com.hta.tuitionmanagement.dto;

import com.hta.tuitionmanagement.constants.MessageConstant;
import com.hta.tuitionmanagement.constants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse<T> {
    public Integer status;
    public String result;
    public String message;
    public T data;
    public List<T> listData;

    public MessageResponse<T> success(){
        return setStatus(StatusEnum.OK);
    }

    public MessageResponse<T> success(String msg) {
        this.message = msg;
        return setStatus(StatusEnum.OK);
    }

    public MessageResponse<T> error(String msg) {
        this.message = msg;
        return setStatus(StatusEnum.FAIL);
    }

    public MessageResponse<T> error() {
        return setStatus(StatusEnum.FAIL);
    }

    public MessageResponse<T> notFoundData() {
        return setStatus(StatusEnum.DATA_NOTFOUND);
    }

    public MessageResponse<T> notFoundData(String msg) {
        this.message = msg;
        return setStatus(StatusEnum.DATA_NOTFOUND);
    }

    private  MessageResponse<T> setStatus(StatusEnum statusEnum) {
        this.setResult(statusEnum.getResult());
        this.setMessage(statusEnum.getMessage());
        return this;
    }

}
