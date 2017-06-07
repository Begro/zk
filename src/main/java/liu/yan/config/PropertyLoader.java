package liu.yan.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liuyan9 on 2017/5/24.
 */
@Slf4j
public class PropertyLoader {

    public static Properties loadProperties(final String path) {
        InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("config path: {} not exist!", path);
            System.exit(-1);
        }
        return properties;
    }

    public static Map<String, String> loadMap(final String path) {
        Properties properties = loadProperties(path);
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return result;
    }
}
