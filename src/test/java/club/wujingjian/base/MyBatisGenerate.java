package club.wujingjian.base;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class MyBatisGenerate {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir"); //   /Users/a123/code/base
        String dbUrl = "jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&autoReconnect=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
        String dbUserName = "root";
        String dbPwd = "root";
        FastAutoGenerator.create(dbUrl, dbUserName, dbPwd)
                .globalConfig(builder -> {
                    builder.author("wujingjian") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(userDir+"/logs"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    if (typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("club.wujingjian") // 设置父包名
                            .moduleName("base") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, userDir+"/logs")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
