package cn.wolfcode.mapper;


import cn.wolfcode.domain.User;
import cn.wolfcode.query.UserQueryObject;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    int insert(User record);
    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
	Integer count(UserQueryObject qo);
	List<User> query(UserQueryObject qo);
}