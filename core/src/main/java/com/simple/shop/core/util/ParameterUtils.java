package com.simple.shop.core.util;

import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {

    public static Map<String, Object> getCommonParameters(Integer id, String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("name", name);
        return parameters;
    }
}
