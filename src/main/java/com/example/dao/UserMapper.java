package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Delete({
            "delete from user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into user (id, username, ",
            "password, role, ",
            "permission, ban)",
            "values (#{id,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{role,jdbcType=VARCHAR}, ",
            "#{permission,jdbcType=VARCHAR}, #{ban,jdbcType=INTEGER})"
    })
    int insert(User record);

    @Select({
            "select",
            "id, username, password, role, permission, ban",
            "from user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    User selectByPrimaryKey(Long id);

    @Update({
            "update user",
            "set username = #{username,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "role = #{role,jdbcType=VARCHAR},",
            "permission = #{permission,jdbcType=VARCHAR},",
            "ban = #{ban,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

    /**
     * 获得密码
     *
     * @param username 用户名
     */
    @Select({
            "select password from user where username = #{username}"
    })
    String getPassword(String username);

    /**
     * 检查用户状态
     *
     * @param username
     * @return
     */
    @Select({
            "select ban from user where username = #{username}"
    })
    int checkUserBanStatus(String username);

    /**
     * 获得角色权限
     *
     * @param username
     * @return
     */
    @Select({
            "select role from user where username = #{username}"
    })
    String getRole(String username);

    /**
     * 获得用户角色默认的权限
     *
     * @param username
     * @return
     */
    @Select({
            "select r.permission FROM user u, role r where u.username = #{username} and r.role = u.role"
    })
    String getRolePermission(String username);

    /**
     * 获得用户的权限
     *
     * @param username
     * @return
     */
    @Select({
            "select permission from user where username = #{username}"
    })
    String getPermission(String username);
}