package cn.zdxh.shirodemo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 角色和权限实体
 */
@Data
public class SysRolePerms {
  //ID自动增长策略
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private Integer roleId;
  private Integer permsId;

}
