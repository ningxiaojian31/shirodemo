package cn.zdxh.shirodemo.utils;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result success(T t){
        Result<T> result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(t);
        return result;
    }

    public static <T> Result error(T t){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("失败");
        result.setData(t);
        return result;
    }
}
