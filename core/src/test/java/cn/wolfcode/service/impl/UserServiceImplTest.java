package cn.wolfcode.service.impl;

import cn.wolfcode.domain.User;
import cn.wolfcode.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    IUserService userService;

    @Test
    public void deleteByPrimaryKey() throws Exception {
        userService.deleteByPrimaryKey(6L);
    }

    @Test
    public void insert() throws Exception {
        User user = new User(null,"","123","111");
        userService.insert(user);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        User user = userService.selectByPrimaryKey(2L);
        System.out.println(user.toString());
    }

    @Test
    public void selectAll() throws Exception {
        List<User> list = userService.selectAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        User user = new User(1l,"赵云","123","111");
        userService.updateByPrimaryKey(user);
    }


}