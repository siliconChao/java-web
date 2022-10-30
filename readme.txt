1 HTTP协议 :
    1) HTTP成为超文本传输协议
    2) HTTP是无状态的
    3) HTTP请求响应包含两部分 :
        - 请求 :
            请求包含三部分 : 请求行 请求头 请求体
                1)请求行包含三个信息 : 1 请求的方式 2 请求的URL 3 请求的HTTP版本
                Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/
                2)请求消息头中包含了很多客户端需要告诉服务器的信息 比如客户端的浏览器版本  浏览器型号  能接受的内容的类型 给服务器发送的内容的
                类型
                Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36
                3)请求体 三种情况 :
                    get方式,没有请求体 但是有一个 queryString
                        下面的请求中问号后面的就是查询字符串
                        // http://localhost:8080/javaweb2/test1?uname=konglc&age=26&address=chengdu
                    post方式  有请求体
                    json格式 有请求体 Request payload
        - 响应
            响应也包含三个部分
                1) 响应状态行 :
                    包含三个信息 : 1 协议 2 响应状态码(200) 3响应状态(ok)
                2) 响应头 : 包含服务器的信息 服务器发送给浏览器的信息(内容的媒体类型 编码 内容长度)

                3) 响应体 : 响应的实际内容

2 会话 :
    HTTP是无状态的 : 两次请求服务器无法判断这两次请求是同一个客户端发过来的 还是不同的客户端发过来的
    -无状态带来的现实问题 :
        第一次请求是添加商品到购物车 第二次请求是结账 如果这两次请求服务器无法区分是同一个用户那么就会导致混乱
    - 通过会话跟踪技术来解决 混乱问题

    2)会话跟踪技术 :
        客户端第一次给服务器发送请求的时候 如果没有SessionId 那么 服务器会给客户端分配一个SessionId 这个ID就是JSessionId
        客户端关闭之后 服务器会重新分配 SessionId
        第一访问客户端 不会带上 JSessionId
        以后访问就会带上 JSessionId
        Cookie: JSESSIONID=5979D0BAA0767DFFF716C0BD76BAB9FF;
        - 客户端第一次给服务器发送请求 服务器获取Session 如果获取不到 就会创建新的
        - 下次服务器给客户端发送请求时 会把SessionId 带过去 那么服务器就可以获取到了 那么服务器就可以判断这次访问和上一次访问是否是同一个客户端
        request.getSession(); // 获取当前会话 没有则创建信的会话
        request.getSession(true); // 效果和不带参数一样
        request.getSession(false); 获取当前会话 没有则返回 null 不会创建新的

        session.getId() // 获取SessionId
        session.isNew() // 判断当前Session是否是新的
     3) Session保存作用域 :
        Object getAttribute(String var1);
        void setAttribute(String var1, Object var2);

3 服务器内部转发及客户端重定向 :
     // 服务器内部转发 test1 客户端的地址不会发生变化
     request.getRequestDispatcher("/test1").forward(ServletRequest,ServletResponse);
     一次请求响应的过程 对于客户端而言 服务端内部经过了多少次转发 客户端是不知道的
     地址栏是不会发生变化的

     // 客户端重定向 立即会向服务器发送请求 访问 test1 两次请求 两次响应
     response.sendRedirect("test1");
     // 两次请求响应的过程 客户端肯定知道 URL 有变化
     // 浏览器的地址栏是会发生变化的


4 Thymeleaf  - 视图模板技术
    在页面上加载java内存中的数据的过程称之为 渲染(render)
    Thymeleaf 是用来做视图渲染的技术
    1) 添加Thymeleaf的jar包
        两个方法
            - processTemplate
            - init

    2) 新建一个 Servlet类 ViewBaseServlet   使得我们的Servlet继承ViewBaseServlet
    3) 在web.xml中添加配置 :
        上下文参数 :
            <context-param>
        配置视图前缀  : view-prefix
        配置视图后缀 :  view-suffix
    4) 根据逻辑视图名称 得到物理视图名称
        此处的视图名称是 index
        那么Thymeleaf 会将这个逻辑视图名称 对应到物理视图 名称上去
        逻辑视图名称 : index
        物理视图名称 view-prefix + 逻辑视图名称 + view-suffix
        所以真实的视图名称是 /index.html

     5) 使用 Thymeleaf 的标签 :
        th:if th:unless th:each th:text
5 保存作用域 :
    原始情况下 保存作用域有4个 :
        - page (页面级别 现在几乎不用)
        - request (一次请求响应范围)
        - session (一次会话范围)
        - application (整个应用程序范围)
    1) request : 一次请求响应范围
        服务器内部转发 : 浏览器的网址不会发生变化 对于浏览器而言就是一次请求 请求域中的数据是可以获取到的
        客户端重定向 : 浏览器的地址会发生变化 相当于是两次请求 第二次请求是拿不到第一次请求的请求域中的数据的
    2) Session : 一次会话范围有效
        不管是服务器端转发 还是客户端重定向 只要是一次会话 都可以获取到请求域中的数据

    3 ) application 作用域
6 路径问题 :
    相对路径 :
    绝对路径 :
    WEB-INF
    css
    images
    js
    user/member/shopping.html
    user/login.html
    在 shopping.html 页面访问 : login.html
    // 当前页面所有路径都是以这个为基础的
    <base href="http://localhost:8080/fruit/">
    // http://localhost:8080/fruit/shopping.css 真实的路径
    <link href="css/shopping.css">
    th:href="@{}"
    <link th:href="@{css/shopping.css}">





     review :
     1   post提交方式下的设置编码 防止中文乱码 :
             request.setCharacterEncoding("UTF-8");
        get提交方式 : tomcat8开始 编码不需要设置
     2 Servlet继承关系及生命周期
        1) Servlet接口 : init() service() destroy()
            GenericServlet抽象子类 abstract service();
            HttpServlet抽象子类 实现了 service方法 在service方法内部通过request.getMethod()来判断请求的方式
        然后根据请求方式调用内部的 do 方法 每一个 do 方法 进行了简单的实现 主要是如果请求方式不符合 则报405错误
        目的是让 Servlet子类去重写对应的方法(如果重写的不对 则使用父类的405错误实现)
        2) 生命周期 :
            实例化 初始化 服务 销毁
            - Tomcat负责维护 Servlet实例的生命周期
            - 每个 Servlet在 Tomcat中只有一个实例 是线程不安全的
            - Servlet的启动时机 : <load-on-startup> 配置的数值越小越优先
            - Servlet3.0开始支持注解 @WebServlet
     3 Http 协议 :
        1) 由 Request 和 Response 两部分组成
        2) 请求包含三个部分 : 请求行 请求头 请求体: queryString(普通的get方式) post方式-formData;json格式
            request payload
        3) 响应包含三个部分 :
            响应行 响应头 响应主体
     4 HttpSession
     1) HttpSession : 表示会话
     2) 为什么需要 HttpSession 原因是 Http协议是无状态的
     3) Session保存作用域 一次会话范围内有效 session.setAttribute(key,value) session.getAttribute(key)
          其它的API : session.getId()  session.getCreationTime() session.isNew()
     5 服务器端转发和客户端重定向
        1) 服务端转发 : request.getRequestDispatcher("index.html").forward(request,response)
        2) 客户端重定向 : request.sendRedirect("index.html")
     6 Thymeleaf





// 405 请求的方式不允许
// 500服务器内部错误
// 响应状态码 302 表示重定向
// 200 正常响应











