package cn.wolfcode.service;


import cn.wolfcode.domain.Address;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.AddressQueryObject;

import java.util.List;

public interface IAddressService {
	int deleteByPrimaryKey(Long id);
    int insert(Address record);
    Address selectByPrimaryKey(Long id);
    List<Address> selectAll();
    int updateByPrimaryKey(Address record);
	Pagination list(AddressQueryObject qo);
}
