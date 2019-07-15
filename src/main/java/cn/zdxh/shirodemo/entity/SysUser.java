package cn.zdxh.shirodemo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体
 */
@Data
public class SysUser {
  //ID自动增长策略
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private String username;
  private String password;
  private String salt;
  private Integer createUserId;
  private Date createTime;


}
