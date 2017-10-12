package org.jonas.test.spring.conf.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("some")
public class MapConfigurationProperties {
    private Map<String, DataSourceProperties> map = new HashMap<>();

    public Map<String, DataSourceProperties> getMap() {
        return map;
    }

    public void setMap(Map<String, DataSourceProperties> map) {
        this.map = map;
    }
}
