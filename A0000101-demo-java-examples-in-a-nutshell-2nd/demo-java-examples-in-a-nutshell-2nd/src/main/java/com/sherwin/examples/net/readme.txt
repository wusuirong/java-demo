用URL类下载URL内容 GetURL
处理协议底层细节 GetURLInfo
发送邮件 SendMail
用socket下载URL内容 HttpClient
模擬http服務器 HttpMirror，运行后可以用HttpClient调用模拟服务器
代理服务器 SimpleProxyServer，运行HttpMirror在8080端口，运行代理在10000端口，用HttpClient访问本机的8080和10000端口得到的结果是一样的
通用客户端 GenericClient, 可做telnet工具用
多线程代理 ProxyServer
UDP收发 UDPReceive和UDPSend

多线程服务器 Server，一个复杂的例子
使用参数调用 -control secret 3000 com.sherwin.examples.net.Server$Time 3001 com.sherwin.examples.net.Server$Reverse 3002
然后用GenericClient分别连3000,3001和3002看结果