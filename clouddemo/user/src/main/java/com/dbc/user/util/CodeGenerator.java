package com.dbc.user.util;

import com.baomidou.mybatisplus.annotation.IdType;
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

    /** 数据库表名，下划线默认转驼峰*/
    private static final String Table_Name = "SEQ_BAS_BUSINESS_TYPE";
    /** 数据库表名前缀,用于生成实体类时去掉对应前缀 */
    private static final String Table_Prefix = "sys_";


    /** 生成代码的绝对路径，不包含“/src/main/java” */
    private static final String Path = "E:/Projects/DBC/clouddemo/user";
    /** 注释中作者名字 */
    private static final String Author = "DBC";



    /** 数据库连接信息 */
//    private static final String Url = "jdbc:mysql://127.0.0.1:3306/dbc_cloud?useUnicode=true&characterEncoding=utf8&useSSL=false";
//    private static final String DriverName = "com.mysql.jdbc.Driver";
//    private static final String Username = "root";
//    private static final String Password = "123456";

    private static final String Url = "jdbc:oracle:thin:@//192.168.20.1:1521/mobopay";
    private static final String DriverName = "oracle.jdbc.OracleDriver";
    private static final String Username = "MOBAO_PAYMENT";
    private static final String Password = "mobao360#2";



    /** 按模块中分层生成代码时，请配置项目包父路径 */
    private static final String Parent = "com.dbc.user";
    /** 模块名 */
    private static final String Module_Name = "school";



    /** 按层中分模块生成代码时，请修改下列路径 */
    private static final String Entity = "com.dbc.user.model";
    private static final String Mapper = "com.dbc.user.mapper";
    private static final String MapperXml = "com.dbc.user.mapper.xml";
    private static final String Service = "com.dbc.user.service";
    private static final String ServiceImpl = "com.dbc.user.service.impl";
    private static final String Controller = "com.dbc.user.controller";


    public static void main(String[] args) {
        /**
         * 生成代码的模式
         * 0-模块中分层
         * 1-层中分模块
         */
        autoCode(0);
    }



    public static void autoCode(int way) {

        AutoGenerator mpg = new AutoGenerator();

        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(Path + "/src/main/java");
        gc.setAuthor(Author);
        gc.setOpen(false);
//        gc.setIdType(IdType.AUTO);//mysql自动递增
        gc.setIdType(IdType.INPUT);//oracle使用序列
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(Url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DriverName);
        dsc.setUsername(Username);
        dsc.setPassword(Password);
        mpg.setDataSource(dsc);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        //方式一：按模块生成
        if(way == 0){
            pc.setModuleName(Module_Name);
            pc.setParent(Parent);
        }else {
        //方式二：按层生成
            pc.setParent(null);
            pc.setEntity(Entity);
            pc.setMapper(Mapper);// Dao接口包名
            pc.setXml(MapperXml);// XML包名
            pc.setService(Service);
            pc.setServiceImpl(ServiceImpl);
            pc.setController(Controller);
        }
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
