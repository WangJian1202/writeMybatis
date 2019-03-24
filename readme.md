#手写简单的mybatis框架
1.mybatis核心流程:
a. 读取xml配置文件和注解信息，创建配置对象，并完成各个模块的初始化工作
b.封装iBatis的编程模型，使用mapper接口开发的初始化工作
c.通过SqlSession完成SQL的解析，参数的映射，SQL的执行，结果的反射解析过程
