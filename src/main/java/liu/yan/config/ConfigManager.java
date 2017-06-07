package liu.yan.config;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyan9 on 2017/5/24.
 */
@Slf4j
public class ConfigManager {
    public static Object getObject(final Map config, final String key) {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        return config.get(key);
    }


    public static String getString(final Map config, final String key) {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        return String.valueOf(config.get(key));
    }

    public static List<String> getStringList(final Map config, final String key) {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        String[] valueArray = StringUtils.split(String.valueOf(config.get(key)), ",");
        List<String> values = new ArrayList<String>();
        for (String tmp : valueArray) {
            values.add(tmp);
        }
        return values;
    }

    public static int getInt(final Map config, final String key) throws Exception {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        try {
            return Integer.parseInt(String.valueOf(config.get(key)));
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }

    }


    public static long getLong(final Map config, final String key) throws Exception {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        try {
            return Long.parseLong(String.valueOf(config.get(key)));
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }
    }

    public static double getDouble(final Map config, final String key) throws Exception {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        try {
            return Double.parseDouble(String.valueOf(config.get(key)));
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }
    }


    @SuppressWarnings("rawtypes")
    public static boolean getBoolean(final Map config, final String key) throws Exception {
        Preconditions.checkNotNull(config);
        Preconditions.checkState(config.containsKey(key));
        try {
            return Boolean.parseBoolean(String.valueOf(config.get(key)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }
    }
}
