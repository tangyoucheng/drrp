package cn.com.prescription.framework.common.unique_id;

import java.io.IOException;
import java.util.ServiceLoader;

public final class Identifier {

    private static SystemIdProvider provider;

    static {
        for (final SystemIdProvider provider : ServiceLoader.load(SystemIdProvider.class)) {
            Identifier.provider = provider;
            break;
        }
    }

    /**
     * 唯一のコンストラクタ。
     * <P>
     */
    public Identifier() {
        super();
    }

    /**
     */
    public String get() throws IOException {
        return make().concat(Identifier.provider.getSystemId());
    }

    /**
     * @return ユニークＩＤ
     */
    public static String make() {
        return UniqueIdGenerator.getUniqueId();
    }
}
