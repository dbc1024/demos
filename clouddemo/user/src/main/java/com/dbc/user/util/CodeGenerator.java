package com.dbc.user.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang.StringUtils;

import java.util.Scanner;

/**
 * 代码生成器
 * @author: CSZ
 * @date: 2018/11/12 10:31
 */
public class CodeGenerator {

    /** 数据库表名 */
    private static final String Table_Name = "sys_dictionary_type";
    /** 数据库表名前缀 */
    private static final String Table_Prefix = "sys_";

    /** 模块名 */
    private static final String Module_Name = "dic";



    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();

        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "E:/Projects/DBC/clouddemo/user";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("dbc");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dbc_cloud?useUnicode=true&characterEncoding=utf8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        /**
         * 包配置
         */
        //方式一：按模块生成
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(Module_Name);
        pc.setParent("com.dbc.user");

        //方式二：按层生成
/*
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity("com.dbc.user.model");
        pc.setMapper("com.dbc.user.mapper");// Dao接口包名
        pc.setXml("com.dbc.user.mapper.xml");// XML包名
        pc.setService("com.dbc.user.service");
        pc.setServiceImpl("com.dbc.user.service.impl");
        pc.setController("com.dbc.user.controller");
*/
        mpg.setPackageInfo(pc);

        /**
         * 自定义配置
         */
        // 需要可参见官网


        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(Table_Name);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(Table_Prefix);
        mpg.setStrategy(strategy);
        //需要配置模板引擎，省略不写就是配置velocity，需要在pom中引入对应的maven依赖
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    /** 读取控制台内容 */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
