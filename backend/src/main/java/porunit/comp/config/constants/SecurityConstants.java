package porunit.comp.config.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000;
    public static final Key JWT_SECRET = new SecretKeySpec("zalupazalupzalupzalupazalupazalupazalupazalupazalupazalupazalupazalupazalupazalupazalupazalupazalupa".getBytes(StandardCharsets.UTF_8),
            "HmacSHA256");
}
