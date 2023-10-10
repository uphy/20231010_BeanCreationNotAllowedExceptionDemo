# BeanCreationNotAllowedDemo

This repository contains the reproduction code for the following issue:

https://github.com/spring-projects/spring-framework/issues/29730

## Execution

To execute the code, run the following command:

```shell
./gradlew bootRun
```


Upon execution, the `defaultValidator` bean gets destroyed first, resulting in the following exception:

```plaintext
org.springframework.beans.factory.BeanCreationNotAllowedException: Error creating bean with name 'defaultValidator': Singleton bean creation not allowed while singletons of this factory are in destruction (Do not request a bean from a BeanFactory in a destroy method implementation!)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:220)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1417)
        MyBean.destroy
        at org.springframework.beans.factory.support.DefaultListableBeanFactory$DependencyObjectProvider.getObject(DefaultListableBeanFactory.java:2014)
        at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:116)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
        at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:751)
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:703)
        at com.example.beancreationnotallowedexception.BeanCreationNotAllowedExceptionApplication$MyBean$$SpringCGLIB$$0.method(<generated>)
        at com.example.beancreationnotallowedexception.BeanCreationNotAllowedExceptionApplication$MyService.destroy(BeanCreationNotAllowedExceptionApplication.java:36)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleMethod.invoke(InitDestroyAnnotationBeanPostProcessor.java:457)
        at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata.invokeDestroyMethods(InitDestroyAnnotationBeanPostProcessor.java:415)
        at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor.postProcessBeforeDestruction(InitDestroyAnnotationBeanPostProcessor.java:239)
        at org.springframework.beans.factory.support.DisposableBeanAdapter.destroy(DisposableBeanAdapter.java:191)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroyBean(DefaultSingletonBeanRegistry.java:587)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroySingleton(DefaultSingletonBeanRegistry.java:559)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.destroySingleton(DefaultListableBeanFactory.java:1189)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroySingletons(DefaultSingletonBeanRegistry.java:520)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.destroySingletons(DefaultListableBeanFactory.java:1182)
        at org.springframework.context.support.AbstractApplicationContext.destroyBeans(AbstractApplicationContext.java:1110)
        at org.springframework.context.support.AbstractApplicationContext.doClose(AbstractApplicationContext.java:1076)
        at org.springframework.context.support.AbstractApplicationContext.close(AbstractApplicationContext.java:1027)
        at org.springframework.boot.SpringApplicationShutdownHook.closeAndWait(SpringApplicationShutdownHook.java:145)
        at java.base/java.lang.Iterable.forEach(Iterable.java:75)
        at org.springframework.boot.SpringApplicationShutdownHook.run(SpringApplicationShutdownHook.java:114)
        at java.base/java.lang.Thread.run(Thread.java:833)
```
