# Seata1.3+nacos+mysql8.x整合配置

## 1.先确定Seata版本

- spring-cloud-alibaba使用2.2.1.RELEASE版
- seata使用1.3版本，包括`seata服务端`（就是seata-server）、使用seata的各种微服务端（后面统称`seata客户端`），都使用1.3版本

## 2.下载Seata1.3版本的源码和Server版

1. https://github.com/seata/seata/releases/download/v1.3.0/seata-server-1.3.0.zip
2. https://github.com/seata/seata/archive/v1.3.0.zip
3. 两个文件解压到不含有中文、没有空格的路径下（如果有空格会在后面配置推送到nacos报错）

## 3.配置Seata服务端的mysql数据库

1. 先去mysql创建数据库seata
2. 去源码目录`/script/server/db`找到mysql.sql，在seata数据库下执行
3. 执行成功，可以看到多出了3张表![image-20201005151921677](seata1.3+nacos+mysql8.x整合\image-20201005151921677.png)

## 4.配置Seata服务端的配置列表

1. 到源码目录`/script/config-center`中配置config.txt

2. config.txt列表很长，都是默认配置，不需要全部保留，***只保留修改项即可***。我们是用mysql进行数据存储，所以可以保留如下配置。注意：笔者下面文件的注释是方便读者理解的，实际上不能保留，自己配置的时候请删除（否则后面推送到nacos会报错）。

   ```properties
   # 【重要】my_test_tx_group这个事务分组所使用的seata-server的集群名称，要与后面seata-server中registry.conf的registry.nacos.cluster保持一致
   service.vgroupMapping.my_test_tx_group=testCluster
   # 使用db存储
   store.mode=db
   store.db.datasource=druid
   store.db.dbType=mysql
   # 因为是mysql8.x版本，驱动位置发生了改变（有了.cj）
   store.db.driverClassName=com.mysql.cj.jdbc.Driver
   # mysql8.x版本serverTimezone需要按照下面设定（否则会报serverTimezone错误，一堆乱码）。另外 这个属性在推送到nacos之后参数可能会消失，需要手动修改以下
   store.db.url=jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true&serverTimezone=GMT%2B8
   store.db.user=root
   store.db.password=root
   ```

3. 到源码目录`/script/config-center/nacos`目录下，执行`nacos-config.sh`脚本（此命令依赖于git，可能需要读者自己安装）

   ```sh
   .\nacos-config sh -h nacos的Ip地址 -p nacos的端口号 -u nacos用户名 -w nacos密码 -g 本配置列表的所属分组（要与后面seata服务端config.nacos.group保持一致，默认为SEATA_GROUP）
   ```

   例如笔者命令为

   ```sh
   .\nacos-config sh -h localhost -p 1111 -u nacos -w nacos -g SEATA_GROUP
   ```

   推送之后，可以查看nacos的配置列表![image-20201005160648140](seata1.3+nacos+mysql8.x整合\nacos配置列表.png)

   另外需要查看store.db.url中参数是否丢失，如果丢失要重新配置一下

## 5.配置Seata服务端并启动

1. 到seata-server目录`/conf/`中

2. 配置registry.conf

   ```properties
   # 注册中心
   registry {
     # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
     # 如果是file，则需要配置file.conf
     # 因为本例使用nacos，此处填写nacos
     type = "nacos"
   
     # 【重要】此处是nacos中的服务注册配置
     nacos {
     	# 【重要】本服务端在nacos中的微服务名称，后面seata客户端的application.yml中要用到
       application = "seata-server"
       # nacos地址（默认为8848，笔者此处做了负载均衡改了端口）
       serverAddr = "localhost:1111"
       # seata-server微服务的分组
       group = "SEATA_GROUP"
       # seata-server微服务的命名空间，此处省略，使用默认值public
       namespace = ""
       # 【重要】seata-server作为集群时的集群名字，与前面nacos中设定事务分组属性（service.vgroupMapping.my_test_tx_group）保持一致
       cluster = "testCluster"
       # nacos1.2加入了鉴权，账号密码不可省略
       username = "nacos"
       password = "nacos"
     }
   }
   
   # 配置中心
   config {
     # file、nacos 、apollo、zk、consul、etcd3
     # 因为本例使用nacos，此处填写nacos
     type = "nacos"
   
     nacos {
       serverAddr = "localhost:1111"
       namespace = ""
       # 【重要】本服务端的配置在nacos配置列表哪个分组下，要与上面推送到nacos的分组列表保持一致，默认是SEATA_GROUP。
       group = "SEATA_GROUP"
       username = "nacos"
       password = "nacos"
     }
   }
   ```

3. 因为服务注册和服务配置都是使用的nacos，所以读者这里并不需要file.conf中的任何属性，自然也不需要改动。后面需要修改配置直接在nacos中修改，SEATA_GROUP分组。

4. 切换到`/bin/`文件夹下，准备启动seata服务端，命令如下

   ```sh
   .\seata-server.bat -p 要使用的端口号
   ```

   笔者默认8091端口被占用了，所以使用了8093端口，命令如下

   ```sh
   .\seata-server.bat -p 8093
   ```

5. 没有报错，则去查看nacos服务列表![image-20201005161621306](seata1.3+nacos+mysql8.x整合\seata-server服务.png)

   并且也要确认集群名称是否正确![image-20201005161750532](seata1.3+nacos+mysql8.x整合\seata-server集群名称.png)

## 6.配置Seata客户端并启动

1. 引入POM依赖，因为`spring-cloud-starter-alibaba-seata`集成的1.1版本的seata，所以需要排除之后引入1.3版本的。笔者此处只写了spring-cloud-alibaba下的seata的依赖，其余依赖请读者自己配置。

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
       <exclusions>
           <exclusion>
               <groupId>io.seata</groupId>
               <artifactId>seata-spring-boot-starter</artifactId>
           </exclusion>
       </exclusions>
   </dependency>
   <dependency>
       <groupId>io.seata</groupId>
       <artifactId>seata-spring-boot-starter</artifactId>
       <version>1.3.0</version>
   </dependency>
   ```

2. 配置application.yml。同样笔者只写了seata配置，其余配置请读者自己写。

   ```yml
   seata:
     # 开启自动装配
     enabled: true
     # 本客户端的微服务名称
     application-id: seata-order-service
     # 读取哪个事务分组
     # 例如此时会读取 SEATA_GROUP 这个分组下的 service.vgroupMapping.my_test_tx_group 这个属性的值。从上面的配置可以知道笔者此处的最终值为 testCluster。后面程序运行会找到 testCluster 这个集群的seata服务端，进行通讯。
     tx-service-group: my_test_tx_group
     # 配置中心设置
     config:
       type: nacos
       nacos:
         username: nacos
         password: nacos
         server-addr: localhost:1111
         # 读取的配置分组
         group: SEATA_GROUP
     # 注册中心设置
     registry:
       type: nacos
       nacos:
         # SEATA服务中心的微服务名，此处与服务端保持一致
         application: seata-server
         server-addr: localhost:1111
         username: nacos
         password: nacos
   ```

3. 因为笔者的seata客户端配置也是使用的nacos，所以项目中并不需要file.conf这个文件。后面需要修改配置直接在nacos中修改，SEATA_GROUP分组。

4. 增加seata代理数据源的配置

   1. 添加DataSourceProxyConfig类

      ```java
      package com.zhoushiya.springcloud.alibaba.config;
      
      import com.alibaba.druid.pool.DruidDataSource;
      import io.seata.rm.datasource.DataSourceProxy;
      import org.apache.ibatis.session.SqlSessionFactory;
      import org.mybatis.spring.SqlSessionFactoryBean;
      import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.boot.context.properties.ConfigurationProperties;
      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.Configuration;
      import org.springframework.context.annotation.Primary;
      import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
      
      import javax.sql.DataSource;
      
      
      /**
       * 使用seata对数据源进行代理
       * @author zhoushiya
       * @date 2020/10/4 16:12
       */
      @Configuration
      public class DataSourceProxyConfig {
      
          // mybatis的mapper文件夹地址
          @Value("${mybatis.mapper-locations}")
          private String mapperLocations;
      
          @Bean
          @ConfigurationProperties(prefix = "spring.datasource.druid")
          public DataSource druidDataSource() {
              DruidDataSource druidDataSource = new DruidDataSource();
              return druidDataSource;
          }
      
          @Bean
          public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
              SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
              factoryBean.setDataSource(dataSource);
              factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                      .getResources(mapperLocations));
              return factoryBean.getObject();
          }
      }
      ```

   2. 修改主启动类

      ```java
      @EnableDiscoveryClient
      @EnableFeignClients
      // 取消数据源的自动创建，使用Seata对数据源进行代理
      @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
      // 导入自定义数据源配置
      @Import({DataSourceProxyConfig.class})
      public class OrderServiceMain2001 {
          public static void main(String[] args) {
              SpringApplication.run(OrderServiceMain2001.class,args);
          }
      }
      ```

5. 启动seata客户端，检查状态是否成功

   1. 控制台输出连上服务端![image-20201005165520969](seata1.3+nacos+mysql8.x整合\image-20201005165520969.png)

   2. seata服务端显示已连上![image-20201005165620467](seata1.3+nacos+mysql8.x整合\image-20201005165620467.png)

   3. nacos中服务已注册![image-20201005165710293](seata1.3+nacos+mysql8.x整合\image-20201005165710293.png)