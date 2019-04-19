1、java序列化的结果文件与class文件规则不一致，不通通过反编译获取相应的结果，只能通过反序列化进行。
2、如何将对象保存为class文件呢？


时候会遇到查看当前jvm虚拟机中的Class对象,比如查看jvm内存中动态生成class.除了修改保存动态生成类文件的开关
（jdk动态代理sun.misc.ProxyGenerator.saveGeneratedFiles:
byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags)
private static final boolean saveGeneratedFiles = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"))).booleanValue();

）
我们还可以使用java -classpath "%JAVA_HOME%/lib/sa-jdi.jar" sun.jvm.hotspot.HSDB中ClassBrower,查找类名并点击Create .class File.
注:VM options:-verbose:class查看加载Class对象的过程