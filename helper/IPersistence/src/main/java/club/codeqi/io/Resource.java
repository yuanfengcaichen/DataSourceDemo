package club.codeqi.io;

import java.io.InputStream;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:00
 */
public class Resource {
    public static InputStream ReadAsInputStream(String path) {
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }
}
