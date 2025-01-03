package bg.softuni.mobilelele.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "forex.api")
public class ForexApiConfig {
    String key;
    private String url;
    private String base;

    public String getBase() {
        return base;
    }

    public ForexApiConfig setBase(String base) {
        this.base = base;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ForexApiConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ForexApiConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    @PostConstruct
    public void  checkConfiguration() {
        verifyNotNullOrEmpty("key", this.key);
        verifyNotNullOrEmpty("url", this.url);
        verifyNotNullOrEmpty("base", this.base);

        if (!"USD".equals(this.base)) {
            throw new IllegalStateException("Forex API base must be USD for the free API version!");
        }
    }

    private static void verifyNotNullOrEmpty(String name, String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Property " + name + " cannot be null or empty!");
        }
    }
}
