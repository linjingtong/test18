package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Address;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.AddressQueryObject;
import cn.wolfcode.service.IAddressService;
import cn.wolfcode.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private IAddressService addressService;
	
	@RequestMapping("")
	public String index(){
		return "address";
	}
	@RequestMapping("/list")
	@ResponseBody
	public Pagination list(AddressQueryObject qo){
		return addressService.list(qo);
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Address address){
		try{
			addressService.insert(address);
			return new AjaxResult(true,"保存成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Address address){
		try{
			addressService.updateByPrimaryKey(address);
			return new AjaxResult(true,"更新成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		try{
			addressService.deleteByPrimaryKey(id);
			return new AjaxResult(true,"删除成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}


}
