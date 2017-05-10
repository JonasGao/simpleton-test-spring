package org.jonas.test.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonas on 2017/4/26.
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        String json = "{\"a\":\"this is a\", \"b\":\"this is b\", \"c\": {\"value\":\"this is c\"}}\n";
        ObjectMapper objectMapper = new ObjectMapper();
        Bean bean = objectMapper.readValue(json, Bean.class);
        System.out.println(bean.getA());
        System.out.println(objectMapper.writeValueAsString(bean));
    }
}

class Bean {
    private String a;
    private String b;
    private Constant c;

    @JsonCreator
    public Bean(@JsonProperty("a") String a, @JsonProperty("c") Constant c) {
        this.a = a;
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Constant getC() {
        return c;
    }
}

class Constant {
    private static Map<String, Constant> map = new HashMap<>();

    private final String value;

    private Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Constant of(@JsonProperty("value") String value) {
        return map.computeIfAbsent(value, Constant::new);
    }
}
