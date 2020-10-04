package com.learn.jeffrey.jeffreylearnquartz.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * BasePageDTO
 * <p>分页(不分页)Dto列表</p>
 *
 * @Author huang.chy
 * @Date 2019/12/7
 */
@Setter
@Getter
public class BasePageDTO<T> extends BaseDTO {

    private List<T> rows;

    private long total;

    public BasePageDTO(String code, List<T> dataList, long total) {
        super(code);
        this.rows = dataList;
        this.total = total;
    }
}
