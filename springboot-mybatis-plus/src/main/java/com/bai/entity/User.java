package com.bai.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@TableName(value = "tb_user", autoResultMap = true)
public class User implements BaseEntity {
    /**
     * user id.
     */
    private Long id;

    /**
     * username.
     */
    private String userName;

    /**
     * user pwd.
     */
    // @JsonIgnore
    private String password;

    /**
     * email.
     */
    private String email;

    /**
     * phoneNumber.
     */
    private long phoneNumber;

    /**
     * description.
     */
    private String description;

    /**
     * create date time.
     */
    private LocalDateTime createTime;

    /**
     * update date time.
     */
    private LocalDateTime updateTime;

    /**
     * join to role table.
     */
    @TableField(exist = false)
    private List<Role> roles;

}
