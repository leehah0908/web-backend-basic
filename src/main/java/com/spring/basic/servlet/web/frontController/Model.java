package com.spring.basic.servlet.web.frontController;

import java.util.HashMap;
import java.util.Map;

// jsp에게 보낼 데이터를 수송하는 역할
public class Model {
    private Map<String, Object> model = new HashMap<>();

    // 데이터를 model에 추가하는 메서드
    public void addAttribute(String key, Object value) {
        model.put(key, value);
    }

    // 데이터 리턶
    public Map<String, Object> getModelMap() {
        return model;
    }

}
