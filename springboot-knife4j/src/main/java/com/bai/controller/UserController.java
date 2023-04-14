package com.bai.controller;

import com.bai.entity.UserAddRequest;
import com.bai.entity.UserEditRequest;
import com.bai.entity.UserQueryRequest;
import com.bai.entity.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("添加")
    @PostMapping("/add")
    public UserVO add(@RequestBody @Valid UserAddRequest userAddRequest) {
        // 将数据写到数据库
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userAddRequest, userVO);
        userVO.setId(1L);
        userVO.setCreateTime(LocalDateTime.now());
        userVO.setUpdateTime(LocalDateTime.now());
        return userVO;
    }

    @ApiOperation("修改")
    @PostMapping("/edit")
    public UserVO edit(@RequestBody @Valid UserEditRequest userEditRequest) {
        // 修改数据库的数据
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEditRequest, userVO);
        userVO.setUpdateTime(LocalDateTime.now());
        return userVO;
    }

    @ApiOperation("查找")
    @GetMapping("/find")
    public List<UserVO> find(UserQueryRequest userQueryRequest) {
        return new ArrayList<>();
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public void delete(Long id) {
        // 将数据库数据删除
    }
}
