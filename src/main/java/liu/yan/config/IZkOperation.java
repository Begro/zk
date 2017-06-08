package liu.yan.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by liuyan9 on 2017/5/24.
 */
public interface IZkOperation {

    public String createNode(CuratorFramework client, String path, byte[] data) throws Exception;

    public String createNode(CuratorFramework client, String path, byte[] data, CreateMode mode) throws Exception;

    public boolean existsNode(CuratorFramework client, String path) throws Exception;

    public boolean existsNode(CuratorFramework client, String path, boolean needWatch) throws Exception;

    public boolean exists(CuratorFramework client, String path) throws Exception;

    public boolean exists(CuratorFramework client, String path, boolean needWatch) throws Exception;

    public org.apache.zookeeper.data.Stat setData(CuratorFramework client, String path, byte[] data) throws Exception;

    public byte[] getData(CuratorFramework client, String path) throws Exception;

    public byte[] getData(CuratorFramework client, String path, boolean needWatch) throws Exception;

    public List<String> getChildren(CuratorFramework client, String path) throws Exception;

    public List<String> getChildren(CuratorFramework client, String path, boolean needWatch) throws Exception;

    public void deleteNode(CuratorFramework client, String path) throws Exception;

    public void deleteNode(CuratorFramework client, String path, boolean force) throws Exception;

    public void deleteRecursive(CuratorFramework client, String path) throws Exception;

    public void close(CuratorFramework client) throws Exception;
}
