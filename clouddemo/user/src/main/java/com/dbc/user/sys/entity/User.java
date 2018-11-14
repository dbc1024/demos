package com.dbc.user.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.reflect.Field;

import com.dbc.user.annotation.DictionaryCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.velocity.runtime.directive.Foreach;

/**
 * <p>
 * 
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String address;

    private String name;

    private String sex;

    private String phone;

    private String createTime;


    /** 非数据库字段*/
    @TableField(exist = false)
    @DictionaryCode("sex")
    private String sexName;

    public void setSex(String sex) {
        this.sex = sex;
        this.sexName = sex;
    }
}
