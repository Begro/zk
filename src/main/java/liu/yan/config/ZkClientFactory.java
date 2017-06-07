package liu.yan.config;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.Map;

/**
 * Created by liuyan9 on 2017/5/24.
 */
@Slf4j
public class ZkClientFactory {

    private static final String ZOOKEEPER_CONNECTION_URL = "zookeeper.connection.url";
    private static final String ZOOKEEPER_CONNECTION_TIMEOUT = "zookeeper.connection.timeout";
    private static final String ZOOKEEPER_SESSION_TIMEOUT = "zookeeper.session.timeout";
    public static final String ZOOKEEPER_RETRY_TIMES = "zookeeper.retry.times";
    public static final String ZOOKEEPER_RETRY_INTERVAL = "zookeeper.retry.interval";


    public static CuratorFramework newCurator(final Map<String, String> conf, final String namespace) throws Exception {
        Preconditions.checkNotNull(conf);
        String zkStr = ConfigManager.getString(conf, ZOOKEEPER_CONNECTION_URL);
        log.info("zk url is {}", zkStr);
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkStr)
                .connectionTimeoutMs(ConfigManager.getInt(conf, ZOOKEEPER_CONNECTION_TIMEOUT))
                .sessionTimeoutMs(ConfigManager.getInt(conf, ZOOKEEPER_SESSION_TIMEOUT))
                .retryPolicy(new RetryNTimes(ConfigManager.getInt(conf, ZOOKEEPER_RETRY_TIMES), ConfigManager.getInt(conf, ZOOKEEPER_RETRY_INTERVAL)));
        if (namespace != null) {
            builder.namespace(namespace);
        }
        return builder.build();
    }
}
