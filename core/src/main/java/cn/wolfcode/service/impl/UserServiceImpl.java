package cn.wolfcode.service.impl;

import cn.wolfcode.domain.User;
import cn.wolfcode.exception.Username_NullException;
import cn.wolfcode.mapper.UserMapper;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.UserQueryObject;
import cn.wolfcode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) {
        if (record.getUsername() == null || record.getUsername() == "") {
            throw new Username_NullException("用户名不能为空");
        }
        return userMapper.insert(record);
    }

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public Pagination list(UserQueryObject qo) {
        int total = userMapper.count(qo);
        if (total <= 0) {
            return new Pagination(0, Collections.emptyList());
        }
        List<User> rows = userMapper.query(qo);
        return new Pagination(total, rows);
    }
}
