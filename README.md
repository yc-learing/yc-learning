> yc-learning，基于Spring Cloud搭建的新一代微服务教学管理平台，提供权限管理、在线考试、练习等功能
>
> 题型支持单选题、多选题、不定项选择题、判断题、简答题



## 项目描述

基于 SpringCloud 搭建的分布式考试系统，系统分为前台和后台，后台是对用户和资源进行管理，统计。 前台系统目前主要功能有视频回顾功能，考试练习功能，学习统计功能，在线考试功能，教室管理功能，教室可以管理发布相关学习资源，练习和考试。学生可以通过该平台进行在线学习考试和练习。 

系统采用前后端分离模式，前台采用 vue.js 为核心框架，后台采用 Spring Cloud 为核心框架。本系统采用 Eurake 做服务注册发现功能，Spring Cloud Gateway zuul 做路由网关,对请求进行拦截验证 同时采用 Feign 做内部服务调用，Ribbon 轮询实现负载均衡, Hystrix 做服务 熔断,实现了系统的故障恢复,保证了高可用性。使用 Spring Cloud Config 做配置中心，将配置文件.上传至 Git 统-进行配置管理。采用 Redis 缓存数据,为系统的高性能提供了保证。

## 技术选型

- 服务注册与发现：`Eurake `
- 熔断器：`Hystrix` + `Turbine`
- 客户端负载均衡：`Ribbon`
- 内部服务调用：`Feign`
- 网关：`Spring Cloud Gateway`
- 程序监控：`Spring Boot Admin` / `Spring Boot Actuator`
- 分布式配置中心：`Spring Cloud Config`
- 数据库：`MySQL 5.7`
- 构建工具：`Maven`
- 后台 API 文档：`Swagger`
- 文件系统：`FastDfs`
- 缓存：`Redis`
- 前端：`vue`







## 核心依赖

| 名称           | 版本             |
| -------------- | ---------------- |
| `Spring Boot`  | `2.1.17.RELEASE` |
| `Spring Cloud` | `Greenwich.SR6`  |







## 项目架构



![image-20210911194512090](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911194512090.png)

如图： `backstage-service`是后台的服务、`base-microservice`是基础服务如：`配置中心、服务发现都在里面`，`learning-commons`主要是公共的应用的包，如： `bean类、VO类、视图类以及常用的注释等`

`yc-learning-service`是前台的考试的服务.

----



![image-20210911194913845](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911194913845.png)

其中前台和后台的服务主要分为： `web层`主要为了展示页面。`service层`是某个拆分后的服务层

----

![image-20210911195045186](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911195045186.png)

其中`service层又细分为：client（消费者）、domain（业务层）、restapi（生产者）	`





> 微服务架构图

![image-20210911202542673](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911202542673.png)

![image-20210911203135084](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203135084.png)



## 功能概述

项目分前台网站和后台管理两部分，前台主要提供考试功能，后台提供基础管理、考试管理功能。

前台主要提供在线考试、在线学习功能

后台管理分为：首页监控、系统管理、系统监控、考务管理、个人管理

首页监控：提供系统租户数、用户数、考试数

系统管理：提供用户角色、权限等基础管理

- 用户管理：用户信息增删改查、导入导出
- 角色管理：角色信息增删改查、分配权限

系统监控：监控服务、日志等

- 服务监控：`spring boot admin`服务监控
- 接口文档：`swagger api`文档

考务管理：提供课程、考试、题库、成绩等管理

- 课程管理：课程信息增删改查
- 考试管理：考试信息增删改查、题目管理、发布回收，题目管理支持简单文本、富文本输入、从题库添加等，题型支持单选题、多选题、不定项选择题、判断题、简答题，生成二维码分享
- 题库管理：题目分类增删改查、题目信息增删改查，题型支持单选题、多选题、不定项选择题、判断题、简答题
- 成绩管理：查看成绩、成绩批改、导出等功能

附件管理：项目的所有附件存储在`fastDfs`里，提供统一的管理入口

- 附件列表：管理所有附件，如用户头像、考试附件、知识库附件等，存储方式支持服务器本地目录、`fastDfs`

个人管理：管理个人资料和修改密码

- 个人资料：姓名、头像等基本信息的修改
- 修改密码：修改密码





## 系统截图



![image-20210911203435672](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203435672.png)

![image-20210911203447077](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203447077.png)

![image-20210911203455983](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203455983.png)

![image-20210911203502562](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203502562.png)

![image-20210911203512745](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203512745.png)

![image-20210911203631899](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911203631899.png)

![image-20210911204242709](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20210911204242709.png)

![img](file:///C:\Users\dell\AppData\Local\Temp\ksohtml21028\wps2.jpg)

![img](file:///C:\Users\dell\AppData\Local\Temp\ksohtml21028\wps3.jpg)

![img](file:///C:\Users\dell\AppData\Local\Temp\ksohtml21028\wps4.jpg)

![img](file:///C:\Users\dell\AppData\Local\Temp\ksohtml21028\wps5.jpg)

