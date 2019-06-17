package com.example.dao;

import com.example.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RoleMapper {
    @Delete({
            "delete from role",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into role (id, role, ",
            "permission)",
            "values (#{id,jdbcType=BIGINT}, #{role,jdbcType=VARCHAR}, ",
            "#{permission,jdbcType=VARCHAR})"
    })
    int insert(Role record);


    @Select({
            "select",
            "id, role, permission",
            "from role",
            "where id = #{id,jdbcType=BIGINT}"
    })
    Role selectByPrimaryKey(Long id);


    @Update({
            "update role",
            "set role = #{role,jdbcType=VARCHAR},",
            "permission = #{permission,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);
}