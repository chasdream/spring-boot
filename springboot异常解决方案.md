# springBoot 异常解决方案

异常一：springBoot启动异常信息

> 异常信息如下：

    If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (the profiles "test" are currently active).
    
    13-Jul-2018 11:05:47.775 SEVERE [localhost-startStop-1] org.apache.catalina.core.ContainerBase.addChildInternal ContainerBase.addChild: start: 
     org.apache.catalina.LifecycleException: Failed to start component [StandardEngine[Catalina].StandardHost[localhost].StandardContext[]]
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:154)
    	at org.apache.catalina.core.ContainerBase.addChildInternal(ContainerBase.java:724)
    	at org.apache.catalina.core.ContainerBase.addChild(ContainerBase.java:700)
    	at org.apache.catalina.core.StandardHost.addChild(StandardHost.java:697)
    	at org.apache.catalina.startup.HostConfig.deployDirectory(HostConfig.java:1071)
    	at org.apache.catalina.startup.HostConfig$DeployDirectory.run(HostConfig.java:1722)
    	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
    	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
    	at java.lang.Thread.run(Thread.java:745)
    Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Tomcat.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.apache.tomcat.jdbc.pool.DataSource]: Factory method 'dataSource' threw exception; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Cannot determine embedded database driver class for database type NONE. If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (the profiles "test" are currently active).
    	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:599)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1173)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1067)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:513)
    	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:483)
    	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306)
    	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302)
    	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197)
    	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:761)
    	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:867)
    	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:543)
    	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122)
    	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:693)
    	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:360)
    	at org.springframework.boot.SpringApplication.run(SpringApplication.java:303)
    	at org.springframework.boot.web.support.SpringBootServletInitializer.run(SpringBootServletInitializer.java:154)
    	at org.springframework.boot.web.support.SpringBootServletInitializer.createRootApplicationContext(SpringBootServletInitializer.java:134)
    	at org.springframework.boot.web.support.SpringBootServletInitializer.onStartup(SpringBootServletInitializer.java:87)
    	at org.springframework.web.SpringServletContainerInitializer.onStartup(SpringServletContainerInitializer.java:169)
    	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5159)
    	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
    	... 10 more
    	
> 解决方案: 

    在springBoot启动类上增加排除数据库自动配置相关类，如：@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) 	
    	
异常二：springBoot利用com.alibaba.dubbo.config.RegistryConfig类进行dubbo的zk集群配置方式如下：

    dubbo:
      application:
        name: test
        zkAddress: 'zookeeper://xxx.xxx.xxx.xxx:2181|zookeeper://xxx.xxx.xxx.xxx:2181|zookeeper://xxx.xxx.xxx.xxx:2181'
        providerGroup: 'test-dev'
        providerVersion: '1.0.0'
      consumer:
        cashloan:
          group: 'xxx-dev'
          version: '1.0.0'

    

    	