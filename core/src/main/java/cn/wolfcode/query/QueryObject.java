package cn.wolfcode.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by A on 2018/1/7.
 */
@Getter
@Setter
public class QueryObject {
    private int page = 1;
    private int rows = 10;


    public int getStart(){
        return (page - 1) * rows;
    }

}
