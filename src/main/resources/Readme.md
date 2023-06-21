### 属性配置
1. 首先在配置类上面加上 `@ConfigurationProperties(prefix = "stu")`
2. 可以使用@Component等将配置类添加到spring容器管理,
     或者使用@EnableConfigurationProperties(Student.class)


mybatisplus
create database mybatisplus default character set  utf8mb4;
create table user(
 id bigint(20) not null comment '主键',
name varchar(30) default null comment '名称',
age int(11) default null comment '年龄',
email varchar(50) default null comment '邮箱',
primary key(id)
) engine = InnoDB default charset =utf8mb4;

alter table user modify id bigint(20) auto_increment;
alter table user   AUTO_INCREMENT=8
