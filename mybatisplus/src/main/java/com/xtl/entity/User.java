package com.xtl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/10 23:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("user")
public class User {

    /**
     * value属性用于指定id字段，type用于指定主键自增策略
     * */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField
    private String name;
    @TableField
    private Integer age;
    @TableField
    private String email;

    @TableLogic
    private Integer deleted;
}
