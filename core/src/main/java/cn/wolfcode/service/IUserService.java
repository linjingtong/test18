package cn.wolfcode.service;


import cn.wolfcode.domain.User;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.UserQueryObject;

import java.util.List;

public interface IUserService {
	int deleteByPrimaryKey(Long id);
    int insert(User record);
    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
	Pagination list(UserQueryObject qo);
}
