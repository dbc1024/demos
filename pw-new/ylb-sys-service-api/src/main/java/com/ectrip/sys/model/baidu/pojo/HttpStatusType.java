package com.ectrip.sys.model.baidu.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxinhao on 2017/3/1.
 */
public enum HttpStatusType {
    SUCCESS(200,"�ɹ�"),
    ERROR_1(400,"�ӿ����ý�������"),
    ERROR_2(401,"Ȩ����֤ʧ��"),
    ERROR_3(403,"��Ȩʧ��"),
    ERROR_4(404,"δ�ҵ�ƥ��ӿ�"),
    ERROR_5(500,"�����쳣");


    private int code;

    private String name;

    HttpStatusType(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    public static HttpStatusType typeOf(int code) {
        //ö�ٽ��
        HttpStatusType[] httpStatuses = HttpStatusType.values();
        for (HttpStatusType httpStatus : httpStatuses) {
            if (httpStatus.getCode() == code) {
                return httpStatus;
            }
        }
        throw new IllegalArgumentException("Invalid HttpStatus type: " + code);
    }

    /**
     * ö��ת�� Map
     * @return Map
     */
    public static Map<Integer, String> convertToMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(HttpStatusType httpStatus : HttpStatusType.values()) {
            map.put(httpStatus.getCode(), httpStatus.getName());
        }
        return map;
    }
}
