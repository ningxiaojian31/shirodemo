package cn.zdxh.shirodemo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 用户和角色实体
 */
@Data
public class SysUserRole {
  //ID自动增长策略
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private Integer userId;
  private Integer roleId;

}
