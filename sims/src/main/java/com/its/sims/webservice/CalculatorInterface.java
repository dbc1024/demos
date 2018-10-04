package com.its.sims.webservice;


import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by csz on 2017/9/21.
 */
@WebService
public interface CalculatorInterface {
    int add(@WebParam(name = "firstA")int a, @WebParam(name = "firstB")int b);
    int minus(@WebParam(name = "secondA")int a, @WebParam(name = "secondB")int b);

    String tranByXml(String str);

    String tranJsonStr(String str);



}
