package org.example.mymapper.mapper;

import org.example.mymapper.annotation.MyMapperScan;

/**
 * @author zhoushiya
 * @date 2020/10/11 18:39
 */
@MyMapperScan
public interface OrderMapper {
    String selectById(int id);
}
