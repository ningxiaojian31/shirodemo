package cn.zdxh.shirodemo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 角色实体
 */
@Data
public class SysRole {
  //ID自动增长策略
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private String roleName;
  private String roleRemark;
  private Integer createUserId;
  private Date createTime;


}
