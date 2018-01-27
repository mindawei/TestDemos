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

# 3 mockito
## 3.1 一些资源
* [mockito 概览](http://site.mockito.org/)
* [mockito 文档 英文](https://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/Mockito.html)
* [mockito 项目地址](https://github.com/mockito/mockito)
* [使用强大的 Mockito 来测试你的代码 译文](https://www.jianshu.com/p/f6e3ab9719b9)
* 为了方便版本发布，[mockito使用了一个自动化发布工具shipkit](https://github.com/mockito/shipkit)

## 3.2 mockito 介绍
以下翻译了 mockito 项目wiki中[特性和动机部分内容](https://github.com/mockito/mockito/wiki/Features-And-Motivations)。
* Java mocking机制中占主导地位的是EasyMock、jMock这样的expect-run-verify库。与它们相比，Mockito提供了更简单和更直观的方法：在执行后询问有关交互的问题。使用mockito，你可以更专注地验证期望结果。而在使用expect-run-verify库时，你经常被迫去关注不相关的交互。
* 不采用expect-run-verify这样的模式，意味着Mockito不需要昂贵的启动开销，并可以使得开发者专注于目标测试行为。
* Mockito的API非常精简，很快可以上手。 这里只由一种mock的类型和创建形式。你只需要记住：打桩（stubbing，可理解为注入代码）在执行之前，验证和交互在执行之后。你很快可以发现它可以让你非常自然地写出基于测试驱动的Java代码.

以下内容可以帮助理解mock，摘自[使用强大的 Mockito 来测试你的代码](https://www.jianshu.com/p/f6e3ab9719b9)。
> 测试类的分类
> * dummy object 做为参数传递给方法但是绝对不会被使用。譬如说，这种测试类内部的方法不会被调用，或者是用来填充某个方法的参数。
> * Fake 是真正接口或抽象类的实现体，但给对象内部实现很简单。譬如说，它存在内存中而不是真正的数据库中。（译者注：Fake 实现了真正的逻辑，但它的存在只是为了测试，而不适合于用在产品中。）
> * stub 类是依赖类的部分方法实现，而这些方法在你测试类和接口的时候会被用到，也就是说 stub 类在测试中会被实例化。stub 类会回应任何外部测试的调用。stub 类有时候还会记录调用的一些信息。
> * mock object 是指类或者接口的模拟实现，你可以自定义这个对象中某个方法的输出结果。
>
> 测试替代技术能够在测试中模拟测试类以外对象。因此你可以验证测试类是否响应正常。譬如说，你可以验证在 Mock 对象的某一个方法是否被调用。这可以确保隔离了外部依赖的干扰只测试测试类。
>
> 我们选择 Mock 对象的原因是因为 Mock 对象只需要少量代码的配置。

综上所述，个人认为，mock 是帮我们解决测试依赖的一种方式，而 mockito 是实现该方式的一个高效简洁的开发库，这个库没有采用expect-run-verify这样的模式，通过封装一些类似工作、提供简洁API可以让开发者专注于目标行为的测试。此外，个人感觉它与Junit关系的是：Junit 提供了运行测试类和判断结果等功能; mockito 帮助提供依赖类，能使测试更加专注和隔离；这两个框架关注点不同，结合在一起可以提高开发效率。

## 3.3 Demo路径
[src/test/java/mockito/MockitoDemo](https://github.com/mindawei/TestDemos/blob/master/src/test/java/mockito/MockitoDemo.java) 提供了部分基本的使用例子，其余例子可以去英文文档中查看。

## 3.4 导入
开发中：编译器是IDEA，仓库管理是gradle。需要在build.gradle中的dependencies 中加入依赖的库，如下所示。

```
dependencies {
    // mockito 相关API
    testCompile "org.mockito:mockito-inline:2.13.0"
}
```

## 3.5 库最近变化
[mockito开发者博客](https://www.linkedin.com/pulse/mockito-vs-powermock-opinionated-dogmatic-static-mocking-faber/)中提到：2017年夏天，作者们开始觉得需要提供一个更好的API，从而与Powermock更好地协作。新的API不是为那些想进行单元测试的人准备的，而是为想进行测试工具开发的人准备的（可以理解为：新版本主要是底层机制变化，方便二次开发，用户看到的新功能不是很多）。

# 4 PowerMock
## 4.1 一些资源
* [PowerMock项目](https://github.com/powermock/powermock)
* [单元测试之Mockito与PowerMock](https://www.jianshu.com/p/51930cc5dcf9)

## 4.2 PowerMock 介绍
以下摘自[单元测试之Mockito与PowerMock](https://www.jianshu.com/p/51930cc5dcf9)。
> Mockito提供了可读性较强、易于使用的mocking测试方法，而PowerMock提供了Mockito-like API是它使用方法基本与Mockito有很大类似，且它能解决静态方法、私有方法等“问题方法” 。





