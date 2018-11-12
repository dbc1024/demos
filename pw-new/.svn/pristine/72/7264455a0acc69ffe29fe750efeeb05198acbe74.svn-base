package com.ectrip.sys.model.authcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxinhao on 2017/2/24.
 */
public enum CodeType {

    SYSTEM("01","ϵͳ"),
    PROVIDER("02","������"),
    PRODUCT("03","��Ʒ"),
    PRICE("04","�۸�"),
    GARDEN("05","԰��"),
    CHECKMACHINE("06","��Ʊ�豸"),
    STATION("07","��Ʊվ��"),
    SALEMACHINE("08","��Ʊ����");

    private String code;

    private String name;

    CodeType(String code, String name) {
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
    public static CodeType typeOf(String code) {
        //ö�ٽ��
        CodeType[] codeTypes = CodeType.values();
        for (CodeType codeType : codeTypes) {
            if (codeType.getCode().equalsIgnoreCase(code)) {
                return codeType;
            }
        }
        throw new IllegalArgumentException("Invalid CodeType type: " + code);
    }

    /**
     * ö��ת�� Map
     * @return Map
     */
    public static Map<String, String> convertToMap() {
        Map<String, String> map = new HashMap<String, String>();
        for(CodeType codeType : CodeType.values()) {
            map.put(codeType.getCode(), codeType.getName());
        }
        return map;
    }
}
