package cn.com.prescription.framework.common.unique_id;

import java.io.IOException;

public interface SystemIdProvider {

    public abstract String getSystemId() throws IOException;
}
