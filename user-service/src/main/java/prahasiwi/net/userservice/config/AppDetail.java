package prahasiwi.net.userservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class AppDetail {
    private String name;
    private String version;
    private String description;
    private DefaultUser defaultUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DefaultUser getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(DefaultUser defaultUser) {
        this.defaultUser = defaultUser;
    }
}
