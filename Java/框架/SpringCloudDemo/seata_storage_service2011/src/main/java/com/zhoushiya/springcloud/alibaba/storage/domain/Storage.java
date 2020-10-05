package com.zhoushiya.springcloud.alibaba.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoushiya
 * @date 2020/10/5 17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private long id;
    private long productId;
    private long total;
    private long used;
    private long residue;
}
