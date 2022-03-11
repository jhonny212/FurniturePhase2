package com.example.furniture.util;

import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class ValidationService {
    
    public boolean validate(Object classHref){
        Method methods[] =classHref.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().contains("get") && !method.getName().toUpperCase().contains("ID")){
                Class type = method.getReturnType();
                System.out.println("Evaluando"+ method.getName());
                try {
                    if(type == null){
                        System.out.println("aca");
                        return false;
                    }
                    Object obj = method.invoke(classHref, null);
                    if (obj == null) {
                        System.out.println("ERROR en"+method.getName());
                        return false;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
