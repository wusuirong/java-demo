package net.未整理和验证的例子;

import org.apache.zookeeper.server.ZooKeeperServerMain;
//import org.apache.zookeeper.test.ClientBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestMainServer extends ZooKeeperServerMain {
	public static final int CLIENT_PORT = 3181;

	public static class MainServerThread extends Thread {
		final File confFile;
		final TestMainServer zooKeeperServer;

		public MainServerThread(int clientPort) throws IOException {
			super("Standalone server with clientPort:" + clientPort);
			File tmpDir = new File("c:/tmp"); // ClientBase.createTmpDir();
			tmpDir.mkdir();
			confFile = new File(tmpDir, "zoo.cfg");

			FileWriter fwriter = new FileWriter(confFile);
			fwriter.write("tickTime=2000\n");
			fwriter.write("initLimit=10\n");
			fwriter.write("syncLimit=5\n");

			File dataDir = new File(tmpDir, "data");
			if (!dataDir.exists()) {
				if (!dataDir.mkdir()) {
					fwriter.flush();
					fwriter.close();
					throw new IOException("unable to mkdir " + dataDir);
				}
			}

			String df = dataDir.toString().replaceAll("\\\\", "/"); // org.apache.commons.lang.StringUtils.replace(dataDir.toString(),"\\","/");
			fwriter.write("dataDir=" + df + "\n");

			fwriter.write("clientPort=" + clientPort + "\n");
			fwriter.flush();
			fwriter.close();

			zooKeeperServer = new TestMainServer();
		}

		public void run() {
			String args[] = new String[1];
			args[0] = confFile.toString();
			try {
				System.out.println("zookeeper server 运行中");
				zooKeeperServer.initializeAndRun(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startServer() {

		MainServerThread mainServerThread = null;
		try {
			mainServerThread = new MainServerThread(CLIENT_PORT);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		mainServerThread.start();
	}

	public static void main(String[] args) {
		TestMainServer.startServer();
	}
}
