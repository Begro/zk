package liu.yan;

import com.sun.org.apache.xpath.internal.SourceTree;
import liu.yan.config.ZkConfigManager;
import liu.yan.config.ZkManager;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) throws Exception {

//        Map conf = ZkConfigManager.getConfig();
//        ZkManager zkManager = new ZkManager();
//        CuratorFramework curatorFramework = zkManager.mkClient(conf, "root", (state, type, path) -> {
//            System.out.println(state);
//            System.out.println(type);
//            System.out.println(path);
//        });
//
//        System.out.println("%%%%%%%%%%%%%%%%%" + zkManager.exists(curatorFramework, "/root"));
//        String liuyan = zkManager.createNode(curatorFramework, "/liuyan", "liuyan".getBytes());
//        System.out.println("******************" + liuyan);
//        byte[] liuyans = zkManager.getData(curatorFramework, "/liuyan");
//        zkManager.deleteNode(curatorFramework, "/liuyan");


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.52.80:2181", retryPolicy);
        client.start();
        String s = client.create().forPath("/test", "liuyan".getBytes());
        Stat stat = client.checkExists().forPath("/test");
        System.out.println(stat.toString());
        System.out.println(s);
        client.delete().forPath("/test");
        Stat iiii = client.checkExists().forPath("/test");
        System.out.println(iiii.toString());

    }
}
