package cn.com.prescription.framework.common.unique_id;

import java.io.IOException;

public class SystemIdImplProvider implements SystemIdProvider {

    @Override
    public String getSystemId() throws IOException {
        return "rp";
    }

}
