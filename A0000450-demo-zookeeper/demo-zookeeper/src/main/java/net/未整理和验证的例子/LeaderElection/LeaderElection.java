package net.未整理和验证的例子.LeaderElection;

import net.未整理和验证的例子.TestMainClient;
import net.未整理和验证的例子.TestMainServer;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * LeaderElection
 * <p/>
 * Author By: junshan Created Date: 2010-9-8 10:05:41
 */
public class LeaderElection extends TestMainClient {
	public static final Logger logger = Logger.getLogger(LeaderElection.class);

	public LeaderElection(String connectString, String root) {
		super(connectString);
		this.root = root;
		if (zk != null) {
			try {
				Stat s = zk.exists(root, false);
				if (s == null) {
					zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (KeeperException e) {
				logger.error(e);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}

	void findLeader() throws InterruptedException, UnknownHostException, KeeperException {
		byte[] leader = null;
		try {
			leader = zk.getData(root + "/leader", true, null);
		} catch (KeeperException e) {
			if (e instanceof KeeperException.NoNodeException) {
				logger.error(e, e);
			} else {
				throw e;
			}
		}
		if (leader != null) {
			following();
		} else {
			String newLeader = null;
			byte[] localhost = InetAddress.getLocalHost().getAddress();
			try {
				newLeader = zk.create(root + "/leader", localhost, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			} catch (KeeperException e) {
				if (e instanceof KeeperException.NodeExistsException) {
					logger.error(e, e);
				} else {
					throw e;
				}
			}
			if (newLeader != null) {
				leading();
			} else {
				mutex.wait();
			}
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if (null != event.getPath()
						&& event.getPath().equals(root + "/leader")) {
			if (event.getType() == Event.EventType.NodeCreated) {
				System.out.println("得到通知");
				super.process(event);
				following();
			} else if (event.getType() == Event.EventType.NodeDeleted) {
				System.out.println("得到通知");
				super.process(event);
				leading();
			}
		}
		
	}

	void leading() {
		System.out.println("成为领导者");
	}

	void following() {
		System.out.println("成为组成员");
	}
	
	void doService() throws IOException {
		System.out.println("模拟执行业务代码，按任意键退出");
		System.in.read();
	}

	public static void main(String[] args) {
//		TestMainServer.startServer();
		String connectString = "localhost:" + TestMainServer.CLIENT_PORT;

		LeaderElection le = new LeaderElection(connectString, "/GroupMembers");
		try {
			le.findLeader();
			le.doService();
		} catch (Exception e) {
			logger.error(e, e);
		}
	}
}
