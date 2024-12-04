package com.char2cs.Soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class WebSericeSOAP {

    @WebMethod
    public String sayHello(){
        return "Hola Mundo";
    }
    
    @WebMethod
    public int sumar(int a, int b){
        return a + b;
    }
}
