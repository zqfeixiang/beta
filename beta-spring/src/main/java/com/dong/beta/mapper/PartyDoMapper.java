package com.dong.beta.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author dzq
 * @date 2022/5/26 11:41
 */
public interface PartyDoMapper {

    @Update("UPDATE party_do SET max_version = max_version+1 WHERE do_id = #{do_id}")
    void updateVersion(@Param("do_id") int do_id);

}
