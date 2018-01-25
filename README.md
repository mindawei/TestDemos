TestDemos - 测试相关的简单使用例子
---

# Junit 5
## 一些资源
* [JUnit 5 用户指南 英文](http://junit.org/junit5/docs/current/user-guide/#overview)
* [JUnit 5 用户指南 中文](http://sjyuan.cc/junit5/user-guide-cn/)
* [JUnit 5 交流网址](https://gitter.im/junit-team/junit5)

## Junit 5 介绍(摘自指南)
JUnit 5 跟以前的JUnit版本不一样，它由几大不同的模块组成，这些模块分别来自三个不同的子项目。<br>
JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
* JUnit Platform是在JVM上 启动测试框架 的基础平台。它还定义了 TestEngine API，该API可用于开发在平台上运行的测试框架。此外，平台还提供了一个从命令行或者 Gradle 和 Maven 插件来启动的 控制台启动器 ，它就好比一个 基于JUnit 4的Runner 在平台上运行任何TestEngine。
* JUnit Jupiter 是一个组合体，它是由在JUnit 5中编写测试和扩展的新 编程模型 和 扩展模型 组成。另外，Jupiter子项目还提供了一个TestEngine，用于在平台上运行基于Jupiter的测试。
* JUnit Vintage 提供了一个TestEngine，用于在平台上运行基于JUnit 3和JUnit 4的测试。

这三个部分的依赖关系如下图所示：
![依赖关系图](/imgs/component-diagram.svg)

所有的核心注解都位于junit-jupiter-api模块的 org.junit.jupiter.api 包中。

JUnit 5需要Java 8（或更高）的运行时环境。

## Demo路径
[src/test/java/junit5/Junit5DemoJunit5Demo](https://github.com/mindawei/TestDemos/blob/master/src/test/java/junit5/Junit5Demo.java) 把用户指南中的部分例子集合到了一起，其余例子可以去指南中查看。

## 导入



使用的是IDEA，在build.gradle中的dependencies 中加入依赖的库，如下所示。配置时参考了：[Junit 5 配置介绍](https://blog.codefx.org/libraries/junit-5-setup/)。

```
dependencies {
    // 包含测试相关的API
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.3")
}
```

在Junit5介绍中提到了Junit5由三个部分组成，但是这里只导入了一个包，其余部分应该是IDE会提供支持。



