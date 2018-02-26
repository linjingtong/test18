package cn.wolfcode.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jingtong on 2017/11/5.
 * 封装Ajax请求需要的数据
 */
@Setter
@Getter
public class AjaxResult {
    private boolean success;
    private String msg;

    public AjaxResult(){}

    public AjaxResult(String msg){
        this.msg = msg;
    }

    public AjaxResult(boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }
}
