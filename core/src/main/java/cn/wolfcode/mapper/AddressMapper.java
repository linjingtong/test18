package cn.wolfcode.mapper;


import cn.wolfcode.domain.Address;
import cn.wolfcode.query.AddressQueryObject;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Address record);
    Address selectByPrimaryKey(Long id);
    List<Address> selectAll();
    int updateByPrimaryKey(Address record);
	Integer count(AddressQueryObject qo);
	List<Address> query(AddressQueryObject qo);
}