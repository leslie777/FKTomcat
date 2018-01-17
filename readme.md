### 这是一个模拟tomcat的demo
#### 模拟tomcat的几个问题
- web.xml
    > 告诉Web容器，浏览器的url和业务对象进行一个关联相当于一个注册的作用
- 为什么要继承servlet
    > java语言就是一门面共享接口编程语言，一旦继承了话就解决了动态强制转型的问题，这是模板模式
- 为什么要重写doGet
    > 用于拓展我们自己的核心业务，就是填空
- HttpRequest和HttpResponse
    > 实际上是Socket的封装，Http协议都是基于Tcp/Ip之上的
    InputStream Request
    OutputStream Responseue
- url是如何被转换为调用java方法
    > ServletMapping Map键值对，键url ，值Servlet对象
