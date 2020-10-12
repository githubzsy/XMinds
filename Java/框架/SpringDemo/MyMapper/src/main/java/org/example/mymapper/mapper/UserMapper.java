package org.example.mymapper.mapper;

import org.example.mymapper.annotation.MyMapperScan;

/**
 * @author zhoushiya
 * @date 2020/10/11 18:38
 */
@MyMapperScan
public interface UserMapper {
    String selectById(int id);
}
