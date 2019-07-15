package cn.zdxh.shirodemo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 权限实体
 */
@Data
public class SysPerms {
  //ID自动增长策略
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private String perms;


}
