# SpringBoot学习笔记

1.springBoot简介

1.1 设计目的

springBoot采用建立生产就绪Spring应用程序的观点，用来简化新Spring应用的初始搭建以及开发过程。在不需要编写各种配置文件的情况下，只需引入相关的依赖就能迅速搭建起一个web工程。
SpringBoot采用优先于配置的惯例，旨在快速启动和运行应用程序。

1.2 springBoot特点

- 创建独立的Spring应用程序
- 嵌入的Tomcat，无需部署WAR文件
- 简化Maven配置
- 自动配置Spring
- 提供生产就绪型功能，如指标，健康检查和外部配置
- 绝对没有代码生成和对XML没有要求配置

1.3 目录结构

- src
    - main
        - java
            - package
                - Application.java                        应用程序入口
        - resources                                       资源文件
            - static                                      静态资源
            - templates                                   模板资源
            - application.yml(或 application.properties)  配置文件
    - test
        - java
            - package
                ApplicationTests.java
- pom.xml

2.配置文件

2.1 获取配置文件中的值@Value("${属性名}")

application.yml文件配置：

    index:
      name: black

controller获取配置文件中的值:

    @Controller
    public class IndexController {

        @Value("${index.name}")
        private String name;

        @RequestMapping(value = "/index")
        @ResponseBody
        public String index() {
            return name + ", you are welcome to SpringBoot!";
        }
    }

2.2 将application.yml配置文件的值赋给实体类

- 在JavaBean中添加@ConfigurationProperties(prefix = "index")注解
- 在Controller类或Application类添加@EnableConfigurationProperties({IndexBean.class})

2.3 自定义配置文件

indexBean.properties文件配置：

    index.name=java
    index.age=30
    index.sex=m
    index.id=1

需在JavaBean中添加：

    @Configuration
    @PropertySource(value = "indexBean.properties")
    @ConfigurationProperties(prefix = "index")
    public class IndexBean {
        private int id;
        private String name;
        private String sex;
        private int age;

        ...setter/getter...
    }

2.4 配置多环境配置文件

格式为application-{profile}.properties，其中{profile}对应你的环境标识
- application-test.properties：测试环境
- application-dev.properties：开发环境
- application-prod.properties：生产环境

application.yml中配置:

    spring:
      profiles:
        active: dev

application-dev.properties:

    server.port=8082

<font color=#FF0000>注：[单元测试] 异常</font>

    Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: Failed to parse configuration class [com.spring.boot.Application];
    nested exception is java.io.FileNotFoundException: Could not open ServletContext resource [/indexBean.properties]

<font color=#FF0000>解决方案：</font>在@SpringBootTest中增加加webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class PersonTest {
    }

3.springBoot集成MyBatis访问MySQL数据库

3.1 添加pom依赖

    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>6.0.6</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.3</version>
    </dependency>

3.2 配置数据源

    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8
    spring.datasource.username=admin
    spring.datasource.password=123456

    mybatis.mapper-locations=classpath*:mapping/*.xml
    mybatis.type-aliases-package=com.spring.boot.model

4.springBoot集成redis

4.1 配置pom依赖

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <version>1.5.7.RELEASE</version>
    </dependency>

4.2 配置数据源

    spring.redis.host=127.0.0.1
    spring.redis.port=6379
    spring.redis.password=
    spring.redis.database=0
    spring.redis.pool.max-active=8
    spring.redis.pool.max-wait=-1
    spring.redis.pool.max-idle=500
    spring.redis.pool.min-idle=0
    spring.redis.timeout=0

4.3 实例

    @Repository
    public class RedisUtils {

        @Autowired
        private StringRedisTemplate redisTemplate;

        public void set(String key, String value) {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value, 30, TimeUnit.SECONDS);
        }

        public String getValue(String key) {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            return valueOperations.get(key);
        }

    }

<font color=#FF0000>注：@Repository、@Service、@Controller和@Component将类标识为SpringBean</font>
 * @Repository:(Spring2.0版本开始引入)用于将数据访问层(DAO层)的类标识为SpringBean，并且它还能将所标注的类中抛出的数据访问异常封装为Spring的数据访问异常类型;
 * Spring2.5版本在@Repository的基础上增加了功能类似的额外三个注解：@Component、@Service、@Controller，它们分别用于软件系统的不同层次：
 * @Component:是一个泛化的概念，仅仅表示一个组件(Bean)，可以作用在任何层次;
 * @Service:通常作用在业务层，但是目前该功能与@Component相同;
 * @Controller:通常作用在控制层，但是目前该功能与@Component相同;

5.springBoot集成MongoDb

5.1 配置pom依赖

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
        <version>1.5.7.RELEASE</version>
    </dependency>

5.2 配置数据源

> mongodb默认配置

    spring.data.mongodb.uri=mongodb://127.0.0.1:27017/mycol

<font color=#FF0000>注：如果mongodb使用默认端口，密码未设置，可以省略配置。因为springBoot会开启默认。</font>

> mongodb设置密码配置

    spring.data.mongodb.uri=mongodb://name:pass@localhost:27017/dbname

5.3 实例

dao层继承MongoRepository<Shirt, String>接口，此接口中有几个crud接口。也可以自定义查询方法需要严格按照mongodb存入的字段进行对应，例如：字段名称model，方法名称findByModel

    public interface ShirtRepository extends MongoRepository<Shirt, String> {

        Shirt findByModel(String model);

    }

service层crud操作：

    @Service
    public class ShirtServiceImpl implements ShirtService {

        @Autowired
        private ShirtRepository shirtRepository;

        @Override
        public Shirt findByModel(String model) {
            return shirtRepository.findByModel(model);
        }

        @Override
        public void saveShirt(Shirt shirt) {
            shirtRepository.save(shirt);
        }

        @Override
        public void deleteShirt(Shirt shirt) {
            shirtRepository.delete(shirt);
        }
    }


6.springBoot集成kafka

6.1 配置pom依赖

    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
        <version>1.2.2.RELEASE</version>
    </dependency>

6.2 kafka配置文件

    #配置kafka producer
    spring.kafka.producer.bootstrap-servers=127.0.0.1:9092
    spring.kafka.producer.batch-size=16384
    spring.kafka.producer.retries=0
    spring.kafka.producer.buffer-memory=33554432
    spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
    spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

    #配置kafka consumer
    spring.kafka.consumer.bootstrap-servers=127.0.0.1:9092
    spring.kafka.consumer.group-id=spring-boot
    spring.kafka.consumer.enable-auto-commit=true
    spring.kafka.consumer.auto-commit-interval=1000
    spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
    spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

6.3 代码示例

producer:

    @Component
    public class PersonProducer {

        private final static String TOPIC = "person-test";

        @Autowired
        private KafkaTemplate<Integer, String> kafkaTemplate;

        public void sendMessage(Person person) {

            ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(TOPIC, JSON.toJSONString(person));

            listenableFuture.addCallback(param->{
                System.out.println("kafka send message successfully!");
            }, error->{
                System.err.println("kafka send message failure!");
            });
        }
    }

consumer:

    @Component
    public class PersonConsumer {

        @KafkaListener(topics = {"person-test"})
        public void onMessage(ConsumerRecord<String, String> data) {

            System.out.println("==============start==============");

            System.out.println("接收kafka消息 topic:" + data.topic() + "; value:" + data.value());

            Person person = JSONObject.parseObject(data.value(), Person.class);
            System.out.println("接收kafka消息 person:" + JSON.toJSONString(person));

            System.out.println("==============end==============");
        }
    }

<font color=#FF0000>注：通过@KafkaListener(topics = {"topics1", "topics2", ...})配置监听多个topic</font>


