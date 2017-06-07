package liu.yan.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by liuyan9 on 2017/5/24.
 */
@Slf4j
public class ZkConfigManager {


    private static Map<String, String> config;

    static {
        try {
            config = PropertyLoader.loadMap("./zk.properties");
            if (config == null) {
                log.error("zk.properties can not found");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

    public static Map<String, String> getConfig() {
        return config;
    }
}
