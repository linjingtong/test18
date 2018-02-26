package cn.wolfcode.web.controller;

import cn.wolfcode.domain.User;
import cn.wolfcode.pagination.Pagination;
import cn.wolfcode.query.UserQueryObject;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("")
	public String index(){
		return "user";
	}
	@RequestMapping("/list")
	@ResponseBody
	public Pagination list(UserQueryObject qo){
		return userService.list(qo);
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(User user){
		try{
			userService.insert(user);
			return new AjaxResult(true,"保存成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(User user){
		try{
			userService.updateByPrimaryKey(user);
			return new AjaxResult(true,"更新成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		try{
			userService.deleteByPrimaryKey(id);
			return new AjaxResult(true,"删除成功");
		}catch(Exception e){
			return new AjaxResult(e.getMessage());
		}
	}

	@RequestMapping("/listForAddress")
	@ResponseBody
	public List<User> listForAddress(){
		return userService.selectAll();
	}

}
