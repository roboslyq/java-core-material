<b>ASM 详解</b>

[TOC]



# 版本约定

ASM版本使用`asm7.1`

# 基本概念

## ASM是什么

一切技术最好的资料就是官方文档，ASM也不例外。官方地址：https://asm.ow2.io/。打开首页有以下描述：

> **ASM** is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar functionality as other Java bytecode frameworks, but is focused on [performance](https://asm.ow2.io/performance.html). Because it was designed and implemented to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a static way too, e.g. in compilers).

简单理解，有以下几个关键点：

- asm存在目的：java bytecode(字节码)处理和分析的框架。具体处理包括修改已经存在的class文件或者动态生成二进制class文件。
- 提供了一些常用的字节码转换和分析的算法。
- ASM聚集于performance，因此设计得很小并且性能很高。

## 常用ASM框架

> ASM is used in many projects, including:
>
> - the [**OpenJDK**](http://openjdk.java.net/), to generate the [lambda call sites](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/lang/invoke/InnerClassLambdaMetafactory.java), and also in the [Nashorn](https://en.wikipedia.org/wiki/Nashorn_(JavaScript_engine)) [compiler](http://hg.openjdk.java.net/jdk8/jdk8/nashorn/file/096dc407d310/src/jdk/nashorn/internal/codegen/ClassEmitter.java),
> - the [**Groovy**](http://www.groovy-lang.org/) [compiler](https://github.com/apache/groovy/blob/GROOVY_2_4_15/src/main/org/codehaus/groovy/classgen/AsmClassGenerator.java) and the [**Kotlin**](https://kotlinlang.org/) [compiler](https://github.com/JetBrains/kotlin/blob/v1.2.30/compiler/backend/src/org/jetbrains/kotlin/codegen/ClassBuilder.java),
> - [**Cobertura**](http://cobertura.github.io/cobertura/) and [**Jacoco**](http://www.eclemma.org/jacoco/), to [instrument](https://github.com/cobertura/cobertura/blob/v1_9_4/src/net/sourceforge/cobertura/instrument/ClassInstrumenter.java) [classes](https://github.com/jacoco/jacoco/blob/v0.8.1/org.jacoco.core/src/org/jacoco/core/instr/Instrumenter.java) in order to measure code coverage,
> - [**CGLIB**](https://github.com/cglib/cglib), to dynamically generate [proxy](https://github.com/cglib/cglib/blob/RELEASE_3_2_6/cglib/src/main/java/net/sf/cglib/core/ClassEmitter.java) classes (which are used in other projects such as [**Mockito**](http://site.mockito.org/) and [**EasyMock**](http://easymock.org/)),
> - [**Gradle**](https://gradle.org/), to [generate](https://github.com/gradle/gradle/blob/v4.6.0/subprojects/core/src/main/java/org/gradle/api/internal/AsmBackedClassGenerator.java) some classes at runtime.

上面这些框架是官网提供的，都非常有名，相应作为一个Java程序应该都有接触过至少听过。其实还有更多的框架使用了ASM：AspectWerkz、AspectJ、BEA WebLogic、IBM AUS、OracleBerkleyDB、Oracle TopLink、Terracotta、RIFE、EclipseME、Proactive、Speedo、Fractal、EasyBeans、BeanShell、Jamaic、dynaop、Cobertura、JDBCPersistence、JiP、SonarJ、Substance L&F、Retrotranslator 等。Hibernate 和 Spring 也通过 cglib，另一个更高层一些的自动代码生成工具使用了 ASM。

## Java Class类文件结构

即然ASM是操作Class字节码的工具，那么我们有必要先了认识一下Class文件的结构。因为ASM所有相关的功能都是和class文件结构相关，只是对其抽象封装，让我们普通使用者更加的简单方便的操作class字节码。

![](https://www.ibm.com/developerworks/cn/java/j-lo-asm30/fig003.jpg)

从上图中可以看到，一个 Java 类文件大致可以归为 10 个项：

- **Magic：**该项存放了一个 Java 类文件的魔数（magic number）和版本信息。一个 Java 类文件的前 4 个字节被称为它的魔数。每个正确的 Java 类文件都是以 0xCAFEBABE 开头的，这样保证了 Java 虚拟机能很轻松的分辨出 Java 文件和非 Java 文件。
- **Version：**该项存放了 Java 类文件的版本信息，它对于一个 Java 文件具有重要的意义。因为 Java 技术一直在发展，所以类文件的格式也处在不断变化之中。类文件的版本信息让虚拟机知道如何去读取并处理该类文件。
- **Constant Pool：**该项存放了类中各种文字字符串、类名、方法名和接口名称、final 变量以及对外部类的引用信息等常量。虚拟机必须为每一个被装载的类维护一个常量池，常量池中存储了相应类型所用到的所有类型、字段和方法的符号引用，因此它在 Java 的动态链接中起到了核心的作用。常量池的大小平均占到了整个类大小的 60% 左右。
- **Access_flag：**该项指明了该文件中定义的是类还是接口（一个 class 文件中只能有一个类或接口），同时还指名了类或接口的访问标志，如 public，private, abstract 等信息。
- **This Class：**指向表示该类全限定名称的字符串常量的指针。
- **Super Class：**指向表示父类全限定名称的字符串常量的指针。
- **Interfaces：**一个指针数组，存放了该类或父类实现的所有接口名称的字符串常量的指针。以上三项所指向的常量，特别是前两项，在我们用 ASM 从已有类派生新类时一般需要修改：将类名称改为子类名称；将父类改为派生前的类名称；如果有必要，增加新的实现接口。
- **Fields：**该项对类或接口中声明的字段进行了细致的描述。需要注意的是，fields 列表中仅列出了本类或接口中的字段，并不包括从超类和父接口继承而来的字段。
- **Methods：**该项对类或接口中声明的方法进行了细致的描述。例如方法的名称、参数和返回值类型等。需要注意的是，methods 列表里仅存放了本类或本接口中的方法，并不包括从超类和父接口继承而来的方法。使用 ASM 进行 AOP 编程，通常是通过调整 Method 中的指令来实现的。
- **Class attributes：**该项存放了在该文件中类或接口所定义的属性的基本信息。

事实上，使用 ASM 动态生成类，不需要像早年的 class hacker 一样，熟知 class 文件的每一段，以及它们的功能、长度、偏移量以及编码方式。ASM 会给我们照顾好这一切的，我们只要告诉 ASM 要改动什么就可以了 —— 当然，我们首先得知道要改什么：对类文件格式了解的越多，我们就能更好地使用 ASM 这个利器。

## ASM结构

ASM 通过树这种数据结构来表示复杂的字节码结构，并利用 Push 模型来对树进行遍历，在遍历过程中对字节码进行修改。所谓的 Push 模型类似于简单的 Visitor 设计模式，因为需要处理字节码结构是固定的，所以不需要专门抽象出一种 Vistable 接口，而只需要提供 Visitor 接口。所谓 Visitor 模式和 Iterator 模式有点类似，它们都被用来遍历一些复杂的数据结构。Visitor 相当于用户派出的代表，深入到算法内部，由算法安排访问行程。Visitor 代表可以更换，但对算法流程无法干涉，因此是被动的，这也是它和 Iterator 模式由用户主动调遣算法方式的最大的区别。

#  ClassReader类

​	字节码的读取与分析引擎。它采用类似SAX的事件读取机制，每当有事件发生时，调用注册的ClassVisitor、AnnotationVisitor、FieldVisitor、MethodVisitor做相应的处理。

ClassReader`可以直接由字节数组或由 class 文件间接的获得字节码数据，它能正确的分析字节码，构建出抽象的树在内存中表示字节码。

TODO

# ClassVisitor抽象类

此抽象类在旧版本中为接口。使用了delegate(委派)模式，大部分方法实现均委派给`ClassVisitor cv`属性实现 。

> 定义在读取Class字节码时会触发的事件，如类头解析完成、注解解析、字段解析、方法解析等。

```java

package org.objectweb.asm;
/**
 * 一个抽象的visitor，用来访问一个Class对象。这个类的方法调用必须要有一定顺序,否则会报错，因为class类的 
 * 结构就是有序的。具体顺序如下：
 * 1、visit() -->必选,访问类的开始
 * 2、visitSource() --> 可选
 * 3、visitModule() --> 可选
 * 4、visitNestHost() --> 可选
 * 5、visitOuterClass()  
 *  visitAnnotation() 
 *  visitTypeAnnotation() 
 *  isitAttribute()
 *  visitNestMember()
 *  visitInnerClass()
 *  visitField()
 *  visitMethod()
 *  6、visitEnd() -->必选，最后一步调用
 */
public abstract class ClassVisitor {
  /**
   * ASM的API版本，值为{@link Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6} or    * {@link Opcodes#ASM7}四者中的一个
   */
  protected final int api;

  /** 实现责任链调用方法，可以为空. */
  protected ClassVisitor cv;
    
  /**
   * 构造函数
   * ASM的API版本，值为{@link Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}        * {@link Opcodes#ASM7}四者中的一个
   */
  public ClassVisitor(final int api) {
    this(api, null);
  }

  /**
   * 包含delegate(委派调用)的ClassVisitor
   */
  public ClassVisitor(final int api, final ClassVisitor classVisitor) {
    if (api != Opcodes.ASM7 && api != Opcodes.ASM6 && api != Opcodes.ASM5 && api != Opcodes.ASM4) {
      throw new IllegalArgumentException("Unsupported api " + api);
    }
    this.api = api;
    this.cv = classVisitor;
  }

  /**
   * 访问方法的头部。为第一个方法
   *
   * @param version ：2字节(16 bit/位)的主版本号及次版本号信息
   * @param access ： 访问控制权限，详情可以参考Opcodes.
   * @param name the :类型全路径。形如“"com/roboslyq/core/bytecode/AsmTest"” 详情可以参考{@link Type#getInternalName()}).
   * @param signature ：如果类不是通用类，并且不扩展或实现泛型类或接口，则可能为null。
   * @param superName :父类名称.详情可以参考{@link Type#getInternalName()}).不能为空，如果没有父    * 类，请使用"java/lang/Object"
   * @param interfaces ：接口列表，形如{"com/roboslyq/core/bytecode/asm/ICalculator"}
   */
  public void visit(
      final int version,
      final int access,
      final String name,
      final String signature,
      final String superName,
      final String[] interfaces) {
    if (cv != null) {
      cv.visit(version, access, name, signature, superName, interfaces);
    }
  }

  /**
   * Visits the source of the class.
   */
  public void visitSource(final String source, final String debug) {
    if (cv != null) {
      cv.visitSource(source, debug);
    }
  }

  /**
   * Visit the module corresponding to the class.
   */
  public ModuleVisitor visitModule(final String name, final int access, final String version) {
    if (api < Opcodes.ASM6) {
      throw new UnsupportedOperationException("This feature requires ASM6");
    }
    if (cv != null) {
      return cv.visitModule(name, access, version);
    }
    return null;
  }

  /**
   * Visits the nest host class of the class.
   */
  public void visitNestHost(final String nestHost) {
    if (api < Opcodes.ASM7) {
      throw new UnsupportedOperationException("This feature requires ASM7");
    }
    if (cv != null) {
      cv.visitNestHost(nestHost);
    }
  }

  /**
   */
  public void visitOuterClass(final String owner, final String name, final String descriptor) {
    if (cv != null) {
      cv.visitOuterClass(owner, name, descriptor);
    }
  }

  /**
   * Visits an annotation of the class.
   */
  public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
    if (cv != null) {
      return cv.visitAnnotation(descriptor, visible);
    }
    return null;
  }

  /**
   * Visits an annotation on a type in the class signature.
   */
  public AnnotationVisitor visitTypeAnnotation(
      final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
    if (api < Opcodes.ASM5) {
      throw new UnsupportedOperationException("This feature requires ASM5");
    }
    if (cv != null) {
      return cv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }
    return null;
  }

  /**
   * Visits a non standard attribute of the class.
   */
  public void visitAttribute(final Attribute attribute) {
    if (cv != null) {
      cv.visitAttribute(attribute);
    }
  }

  /**
   * Visits a member of the nest. 
   * nest指一个包下面的所
   */
  public void visitNestMember(final String nestMember) {
    if (api < Opcodes.ASM7) {
      throw new UnsupportedOperationException("This feature requires ASM7");
    }
    if (cv != null) {
      cv.visitNestMember(nestMember);
    }
  }

  /**
   * Visits information about an inner class.
   * 内部类
   */
  public void visitInnerClass(
      final String name, final String outerName, final String innerName, final int access) {
    if (cv != null) {
      cv.visitInnerClass(name, outerName, innerName, access);
    }
  }

  /**
   * Visits a field of the class.
   * @param access : 类变量或者对象属性的访问控制权限，详情可以参考Opcodes.
   * @param name ：属性名称
   * @param descriptor：属性描述
   * @param signature ：属性签名
   * @param value:属性初始值. 仅对static属性有效，非static属性可以通过方法或者构造函数初始化.
   * @return 返回一个FieldVisitor
   */
  public FieldVisitor visitField(
      final int access,
      final String name,
      final String descriptor,
      final String signature,
      final Object value) {
    if (cv != null) {
      return cv.visitField(access, name, descriptor, signature, value);
    }
    return null;
  }
  /**
   *访问方法，每次调用此方法时必须返回一个新的MethodVisitor实例(与class中的一个具体方法相对应)。可以对	  * 方法进行相应的操作。通过每次返回一个新的MethodVisitor从而实现类中不同方法的定义。
   * 
   * @param access ：访问控制权限，详情可以参考Opcodes.
   * @param name ：方法名称
   * @param descriptor：方法描述
   * @param signature：当返回类型或者抛出异常不包含泛型（generic type）时为空.
   * @param exceptions :异常.
   * @return 返回一个可以文访问这方法的MethodVistor
   */
  public MethodVisitor visitMethod(
      final int access,
      final String name,
      final String descriptor,
      final String signature,
      final String[] exceptions) {
    if (cv != null) {
      return cv.visitMethod(access, name, descriptor, signature, exceptions);
    }
    return null;
  }

  /**
   * 访问Class的结尾，表明这个class已经完成访问。必须最后一个调用。
   */
  public void visitEnd() {
    if (cv != null) {
      cv.visitEnd();
    }
  }
}
```



# AnnotationVisitor接口

定义在解析注解时会触发的事件，如解析到一个基本值类型的注解、enum值类型的注解、Array值类型的注解、注解值类型的注解等。

# FieldVisitor接口

​	定义在解析字段时触发的事件，如解析到字段上的注解、解析到字段相关的属性等。

# MethodVisitor接口

定义在解析方法时触发的事件，如方法上的注解、属性、代码等。

# ClassWriter类

它实现了ClassVisitor接口，用于拼接字节码。

```java
public class ClassWriter extends ClassVisitor
```



ASM 的最终的目的是生成可以被正常装载的 class 文件，因此其框架结构为客户提供了一个生成字节码的工具类 —— `ClassWriter`。

此类实现了 `ClassVisitor`接口，而且含有一个 `toByteArray()`函数，返回生成的字节码的字节流，将字节流写回文件即可生产调整后的 class 文件。一般它都作为职责链的终点，把所有 visit 事件的先后调用（时间上的先后），最终转换成字节码的位置的调整（空间上的前后）。



## 构造函数

```java
  public ClassWriter(final int flags) {
    this(null, flags);
  }

```

```java
public ClassWriter(final ClassReader classReader, final int flags) {
    super(Opcodes.ASM7);
    symbolTable = classReader == null ? new SymbolTable(this) : new SymbolTable(this, classReader);
    //注意此处用法，是通过位与运算不为0，所以只要保证相应位(从右起第2位)不为零即可
    if ((flags & COMPUTE_FRAMES) != 0) {
      this.compute = MethodWriter.COMPUTE_ALL_FRAMES;
    } else if ((flags & COMPUTE_MAXS) != 0) {
      this.compute = MethodWriter.COMPUTE_MAX_STACK_AND_LOCAL;
    } else {
      //默认为手动计算栈的大小及本地变量大小
      this.compute = MethodWriter.COMPUTE_NOTHING;
    }
  }
```

其中参数flags为`ClassWriter`定义 的内部常量，一共两个：

```java
/**
* 自动计算方法最大栈和方法本地最大变量数
* 此最， MethodVisitor.visitMaxs无效
* 注意：由于JDK1.6引入了Stack Map Frames特性，所以当Class版本超过JDK1.6（JDK1.7及之后）后，请使用* * COMPUTE_FRAMES替代。
*/
public static final int COMPUTE_MAXS = 1;
/**
* 自动计算方法最大栈和方法本地最大变量数,1.7之后使用
*/
public static final int COMPUTE_FRAMES = 2;
```

关于`Stack Map Frames`简介

> Java字节码中的Stack Map Frames特性是Java 6引入的，当时是可选使用的。但是从Java 7开始，字节码默认使用该特性。Stack Map Frames特性的主要目的是在字节码指令中跟踪局部变量表的类型、操作数的类型。
> 因为字节码在编译时已经加入了类型的信息，所以在JVM运行时加载字节码的时候，对字节码的验证能够更快速。但是该特性的引入导致了一系列操作Java字节码的工具的不兼容。
>
> 在Java 7中，设置JVM参数-XX:-UseSplitVerifier，可以禁用该特性。
>
> 但是在Java 8中，已经去掉了JVM参数-XX:-UseSplitVerifier，该特性无法回避。
>
> 作者：易生一世 
> 来源：CSDN 
> 原文：https://blog.csdn.net/taiyangdao/article/details/53027480 
> 版权声明：本文为博主原创文章，转载请附上博文链接！

# AnnotationWriter类

它实现了AnnotationVisitor接口，用于拼接注解相关字节码。

# FieldWriter类

它实现了FieldVisitor接口，用于拼接字段相关字节码。

# MethodWriter类

它实现了MethodVisitor接口，用于拼接方法相关字节码。

# SignatureReader类

对类定义、字段定义、方法定义、本地变量定义的签名的解析。Signature因范型引入，用于存储范型定义时的元数据（因为这些元数据在运行时会被擦除）。

# SignatureVisitor接口

定义在解析Signature时会触发的事件，如正常的Type参数、类或接口的边界等。

# SignatureWriter类

它实现了SignatureVisitor接口，用于拼接范型相关字节码。

# Attribute类

字节码中属性的类抽象。

# ByteVector类

字节码二进制存储的容器。

# Opcodes接口

字节码指令的一些常量定义。

# Type类

类型相关的常量定义以及一些基于其上的操作。  

# ClassReader


## accept

接受一个实现了 `ClassVisitor`接口的对象实例作为参数，然后依次调用 `ClassVisitor`接口的各个方法。

# ClassVisitor



# ClassWriter



# 小结

最后，我们比较一下 ASM 和其他实现 AOP 的底层技术：

##### 表 1. AOP 底层技术比较

| AOP 底层技术        | 功能             | 性能                                         | 面向接口编程 | 编程难度                                          |
| :------------------ | :--------------- | :------------------------------------------- | :----------- | :------------------------------------------------ |
| 直接改写 class 文件 | 完全控制类       | 无明显性能代价                               | 不要求       | 高，要求对 class 文件结构和 Java 字节码有深刻了解 |
| JDK Instrument      | 完全控制类       | 无论是否改写，每个类装入时都要执行 hook 程序 | 不要求       | 高，要求对 class 文件结构和 Java 字节码有深刻了解 |
| JDK Proxy           | 只能改写 method  | 反射引入性能代价                             | 要求         | 低                                                |
| ASM                 | 几乎能完全控制类 | 无明显性能代价                               | 不要求       | 中，能操纵需要改写部分的 Java 字节码              |

# 参考资料 

- https://www.ibm.com/developerworks/cn/java/j-lo-asm30
- https://asm.ow2.io/
- http://www.blogjava.net/DLevin/
- <https://dzone.com/articles/fully-dynamic-classes-with-asm>
- <https://www.baeldung.com/almost-done>

