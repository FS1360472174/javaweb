配置目录是META-INF/services,不是META-INF.services，虽然在IDEA中显示都是META.INF.services


1. Spring 中很简单


2. 是一种服务发现机制。SPI 的本质是将接口实现类的全限定名配置在文件中，并由服务加载器读取配置文件，加载实现类。这样可以在运行时，动态为接口替换实现类。正因此特性，
我们可以很容易的通过 SPI 机制为我们的程序提供拓展功能。SPI 机制在第三方框架中也有所应用，比如 Dubbo 就是通过 SPI 机制加载所有的组件。

SPI的实现依赖于破坏双亲委派机制。

sun.misc.Launcher
static class AppClassLoader extends URLClassLoader {

}
static class ExtClassLoader extends URLClassLoader {

}


没有依赖注入，
dubbo就是实现了IOC