package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Address;
import cn.wolfcode.domain.User;
import cn.wolfcode.service.IAddressService;
import cn.wolfcode.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressServiceImplTest {
    @Autowired
    IAddressService addressService;
    @Autowired
    IUserService    userService;

    @Test
    public void deleteByPrimaryKey() throws Exception {
        addressService.deleteByPrimaryKey(1l);
    }

    @Test
    public void insert() throws Exception {
        User user = userService.selectByPrimaryKey(1L);
        Address address = new Address(null,user,"云南");
        addressService.insert(address);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        Address address = addressService.selectByPrimaryKey(1l);
        System.out.println(address);
    }

    @Test
    public void selectAll() throws Exception {
        List<Address> list = addressService.selectAll();
        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        User user = userService.selectByPrimaryKey(1L);
        Address address = new Address(1l,user,"云南");
        addressService.updateByPrimaryKey(address);
    }

}