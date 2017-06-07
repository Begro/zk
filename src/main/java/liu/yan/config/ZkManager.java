package liu.yan.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyan9 on 2017/5/24.
 */
@Slf4j
public class ZkManager implements IZkOperation {
    public static interface WatcherCallBack {
        public void process(Watcher.Event.KeeperState state, Watcher.Event.EventType type, String path);
    }

    public CuratorFramework mkClient(final Map conf, final String namespace,
                                     final WatcherCallBack callback) throws Exception {
        CuratorFramework curatorFramework = ZkClientFactory.newCurator(conf, namespace);
        if (callback != null) {
            curatorFramework.getCuratorListenable().addListener((curatorFramework1, curatorEvent) -> {
                        WatchedEvent e = curatorEvent.getWatchedEvent();
                        callback.process(e.getState(), e.getType(), e.getPath());
                    }
            );
        }
        curatorFramework.getUnhandledErrorListenable().addListener((msg, error) -> {
            String errmsg = "Unrecoverable Zookeeper error, halting process: " + msg;
            log.error(errmsg, error);
        });
        curatorFramework.start();
        return curatorFramework;
    }

    @Override
    public String createNode(CuratorFramework client, String path, byte[] data) throws Exception {
        return createNode(client, path, data, org.apache.zookeeper.CreateMode.PERSISTENT);
    }

    @Override
    public String createNode(CuratorFramework client, String path, byte[] data, CreateMode mode) throws Exception {
        return client.create().withMode(mode).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, data);
    }

    @Override
    public boolean existsNode(CuratorFramework client, String path) throws Exception {
        return existsNode(client, path, false);
    }

    @Override
    public boolean existsNode(CuratorFramework client, String path, boolean needWatch) throws Exception {
        if (needWatch) {
            return client.checkExists().watched().forPath(path) != null;
        } else {
            return client.checkExists().forPath(path) != null;
        }
    }

    @Override
    public boolean exists(CuratorFramework client, String path) throws Exception {
        return existsNode(client, path);
    }

    @Override
    public boolean exists(CuratorFramework client, String path, boolean needWatch) throws Exception {
        return existsNode(client, path, needWatch);
    }

    @Override
    public Stat setData(CuratorFramework client, String path, byte[] data) throws Exception {
        return client.setData().forPath(path, data);
    }

    @Override
    public byte[] getData(CuratorFramework client, String path) throws Exception {
        return getData(client, path, false);
    }

    @Override
    public byte[] getData(CuratorFramework client, String path, boolean needWatch) throws Exception {
        if (existsNode(client, path, needWatch)) {
            if (needWatch) {
                return client.getData().watched().forPath(path);
            } else {
                return client.getData().forPath(path);
            }
        } else {
            log.error("path:{} in client :{} not exist", path, client.getNamespace());
            throw new Exception();
        }
    }

    @Override
    public List<String> getChildren(CuratorFramework client, String path) throws Exception {
        return getChildren(client, path, false);
    }

    @Override
    public List<String> getChildren(CuratorFramework client, String path, boolean needWatch) throws Exception {
        if (needWatch) {
            return client.getChildren().watched().forPath(path);
        } else {
            return client.getChildren().forPath(path);
        }
    }


    @Override
    public void deleteNode(CuratorFramework client, String path) throws Exception {
        deleteNode(client, path, false);
    }

    @Override
    public void deleteNode(CuratorFramework client, String path, boolean force) throws Exception {
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            log.error(" delete znode  already not exists. {} " + path);
            if (!force)
                throw e;
        }
    }

    @Override
    public void deleteRecursive(CuratorFramework client, String path) throws Exception {
        if (existsNode(client, path)) {
            List<String> children = getChildren(client, path);
            for (String child : children) {
                deleteRecursive(client, path + File.separator + child);
            }
            deleteNode(client, path, true);
        }
    }

}
