package cn.tenbit.huobi.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:25
 */
@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4854970333525572393L;

    private Integer code;

    private String message;

    private Boolean success;

    private T data;
}
