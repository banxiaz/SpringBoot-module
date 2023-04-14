package com.bai.FastAutoGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class TestGenCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/study_spring?useSSL=false&autoReconnect=true&characterEncoding=utf8", "root", "123456")
                .globalConfig(builder ->
                        builder.author("bai") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                )
                .packageConfig(builder ->
                        builder.parent("com.bai") // 设置父包名
                                .moduleName("genCode") // 设置父包模块名
                )
                .strategyConfig(builder ->
                        builder.addInclude("tb_user", "tb_role", "tb_user_role")
                )
                .execute();
    }

}
