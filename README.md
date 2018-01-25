TestDemos - 测试相关的简单使用例子
---
# 1 背景
之前翻译了一篇文章[《2018年Java开发者应该学习的9个方面》](https://mindawei.github.io/2018/01/24/2018%E5%B9%B4Java%E5%BC%80%E5%8F%91%E8%80%85%E5%BA%94%E8%AF%A5%E5%AD%A6%E4%B9%A0%E7%9A%849%E4%B8%AA%E6%96%B9%E9%9D%A2/)，里面提到：

> 如果你想在2018年更上一层楼，那么你需要继续提高你的单元测试技巧。这里的测试不仅仅是指单元测试，而且包括通常说的自动化测试，当然也包括集成测试。你可以学习JUnit5以及一些其它比较高级的单元测试库，例如：Mockito、 PowerMock、Cucumber、Robot 等，从而使你的测试能力提高一个等级。

考虑到测试没怎么具体接触过，需要了解一下，故创建此项目。只是希望有个大致的了解，然后一些链接和概念记录一下，方便以后使用。


# 2 Junit 5
## 2.1 一些资源
* [JUnit 5 用户指南 英文](http://junit.org/junit5/docs/current/user-guide/#overview)
* [JUnit 5 用户指南 中文](http://sjyuan.cc/junit5/user-guide-cn/)
* [JUnit 5 交流网址](https://gitter.im/junit-team/junit5)

## 2.2 Junit 5 介绍(摘自指南)
JUnit 5 跟以前的JUnit版本不一样，它由几大不同的模块组成，这些模块分别来自三个不同的子项目。<br>
JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
* JUnit Platform是在JVM上 启动测试框架 的基础平台。它还定义了 TestEngine API，该API可用于开发在平台上运行的测试框架。此外，平台还提供了一个从命令行或者 Gradle 和 Maven 插件来启动的 控制台启动器 ，它就好比一个 基于JUnit 4的Runner 在平台上运行任何TestEngine。
* JUnit Jupiter 是一个组合体，它是由在JUnit 5中编写测试和扩展的新 编程模型 和 扩展模型 组成。另外，Jupiter子项目还提供了一个TestEngine，用于在平台上运行基于Jupiter的测试。
* JUnit Vintage 提供了一个TestEngine，用于在平台上运行基于JUnit 3和JUnit 4的测试。

这三个部分的依赖关系如下图所示：
![依赖关系图](/imgs/component-diagram.svg)

所有的核心注解都位于junit-jupiter-api模块的 org.junit.jupiter.api 包中。

JUnit 5需要Java 8（或更高）的运行时环境。

## 2.3 Demo路径
[src/test/java/junit5/Junit5Demo](https://github.com/mindawei/TestDemos/blob/master/src/test/java/junit5/Junit5Demo.java) 把用户指南中的部分例子集合到了一起，其余例子可以去指南中查看。

## 2.4 导入
开发中：编译器是IDEA，仓库管理是gradle。需要在build.gradle中的dependencies 中加入依赖的库，如下所示。配置时参考了：[Junit 5 配置介绍](https://blog.codefx.org/libraries/junit-5-setup/) （maven 构建可以参考这篇文章中的问题部分）。

```
dependencies {
    // 包含测试相关的API
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.3")
}
```

在Junit5介绍中提到了Junit5由三个部分组成，但是这里只导入了一个包，其余部分应该是IDE会提供支持。



