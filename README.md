TestDemos - 测试相关的简单使用例子
---

# Junit 5
## 一些资源
* [JUnit 5 用户指南 英文](http://junit.org/junit5/docs/current/user-guide/#overview)
* [JUnit 5 用户指南 中文](http://sjyuan.cc/junit5/user-guide-cn/)
* [JUnit 5 交流网址](https://gitter.im/junit-team/junit5)
## Demo路径
把用户指南中的部分例子集合到了一起，有些还未集成，具体可以在上述用户指南中查看。Demo文件：[src/test/java/junit5/Junit5DemoJunit5Demo](https://github.com/mindawei/TestDemos/blob/master/src/test/java/junit5/Junit5Demo.java)
## 导入
使用的是IDEA，在build.gradle中的dependencies 中加入依赖的库，如下所示。配置时参考了：[Junit 5 配置介绍](https://blog.codefx.org/libraries/junit-5-setup/)。
```
dependencies {
    // 包含测试相关的API
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.3")
}
```

