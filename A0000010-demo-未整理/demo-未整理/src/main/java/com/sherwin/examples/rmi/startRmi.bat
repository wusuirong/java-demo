rem 启动rmi注册表
rem 首先要把包含server端的class的rmiserver.jar放在rmi注册表所在机器，然后运行注册表，服务端，客户端
set CLASSPATH=E:\temp_workspace\tmp\rmiserver.jar;%CLASSPATH%
D:\programfiles\Java\jdk1.6.0_22\bin\rmiregistry.exe
pause