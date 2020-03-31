# JOL使用简介

## 使用场景

定位分析对象在JVM的大小和分布

## 官网

http://openjdk.java.net/projects/code-tools/jol/

## 依赖 

Maven配置 

```xml
<dependency>  
    <groupId>org.openjdk.jol</groupId>  
    <artifactId>jol-core</artifactId>  
    <version>put-the-version-here</version>  
</dependency> 


```

```

```

## Java命令行

`-XX:+PrintCommandLineFlags`查看命令行参数： 

```powershell
Microsoft Windows [版本 6.1.7601]
版权所有 (c) 2009 Microsoft Corporation。保留所有权利。

C:\Users\luo.yongqian>java -XX:+PrintCommandLineFlags -version
-XX:G1ConcRefinementThreads=4 -XX:InitialHeapSize=126245760 -XX:MaxHeapSize=2019
932160 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+Segme
ntedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1G
C -XX:-UseLargePagesIndividualAllocation
java version "9"
Java(TM) SE Runtime Environment (build 9+181)
Java HotSpot(TM) 64-Bit Server VM (build 9+181, mixed mode)

```

 注意：

- -XX:+UseCompressedClassPointers 

  开启Classic指针压缩，如果是64位JDK，指针应该是8字节，开启压缩后指针为4字节。

- -XX:+UseCompressedOops

  开启普通对象指针压缩，如果是64位JDK，指针应该是8字节，开启压缩后指针为4字节。

​	