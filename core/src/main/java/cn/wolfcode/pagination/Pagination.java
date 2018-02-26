package cn.wolfcode.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Pagination {
    //参数名称要和前台参数一致
    private Integer total; //count
    private List rows;     //list

    public Pagination(){}

    public Pagination(Integer total, List rows){
        this.total = total;
        this.rows = rows;
    }
}
