package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Address;
import cn.wolfcode.mapper.AddressMapper;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.AddressQueryObject;
import cn.wolfcode.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service@Transactional
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private AddressMapper addressMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return addressMapper.deleteByPrimaryKey(id);
	}

	public int insert(Address record) {
		return addressMapper.insert(record);
	}

	public Address selectByPrimaryKey(Long id) {
		return addressMapper.selectByPrimaryKey(id);
	}

	public List<Address> selectAll() {
		return addressMapper.selectAll();
	}

	public int updateByPrimaryKey(Address record) {
		return addressMapper.updateByPrimaryKey(record);
	}

	@Override
	public Pagination list(AddressQueryObject qo) {
		int total = addressMapper.count(qo);
		if(total <= 0){
			return new Pagination(0, Collections.emptyList());
		}
		List<Address> rows = addressMapper.query(qo);
		return new Pagination(total, rows);
	}
}
