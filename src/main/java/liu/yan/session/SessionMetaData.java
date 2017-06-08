package liu.yan.session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by liuyan9 on 2017/6/7.
 */
@Getter
@Setter
public class SessionMetaData implements Serializable {

    private String id;
    private Long createTime;
    private Long maxIdle;
    private Long lastAccessTime;
    private Boolean validate = false;
    private Integer version = 0;

    public SessionMetaData() {
        this.createTime = System.currentTimeMillis();
        this.lastAccessTime = createTime;
        this.validate = true;
    }
}
