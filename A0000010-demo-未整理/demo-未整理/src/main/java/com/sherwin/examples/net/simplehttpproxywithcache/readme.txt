java2 学习指南中的例子

演示使用socket的一个例子
实现一个带缓存的http代理服务器
功能
1 只支持get操作
2 只支持一部分mime类型
3 单线程
4 保留每个获取的文件在本地cache

说明
MimeHeader.java
	负责保存http协议中的mime信息
	http报文里MIME头是类似这样的格式
	Content-Type: text/html
	Content-length: 100
	MimeHeader负责把这些信息保存起来，他的toString方法则把信息还原为http报文中的mime字符串
	
HttpResponse.java
	