package com.ectrip.sys.model.authcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxinhao on 2017/2/25.
 */
public enum CodeTypePrefix {

    SYSTEM("01","A"),
    PROVIDER("02","B"),
    PRODUCT("03","C"),
    PRICE("04","D"),
    GARDEN("05","E"),
    CHECKMACHINE("06","F"),
    STATION("07","G"),
    SALEMACHINE("08","H");

    private String code;

    private String name;

    CodeTypePrefix(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡö�ٶ���
     * @param code
     * @return
     */
    public static CodeTypePrefix typeOf(String code) {
        //ö�ٽ��
        CodeTypePrefix[] codeTypes = CodeTypePrefix.values();
        for (CodeTypePrefix codeType : codeTypes) {
            if (codeType.getCode().equalsIgnoreCase(code)) {
                return codeType;
            }
        }
        throw new IllegalArgumentException("Invalid CodeTypePrefix type: " + code);
    }

    /**
     * ö��ת�� Map
     * @return Map
     */
    public static Map<String, String> convertToMap() {
        Map<String, String> map = new HashMap<String, String>();
        for(CodeTypePrefix codeType : CodeTypePrefix.values()) {
            map.put(codeType.getCode(), codeType.getName());
        }
        return map;
    }
}
