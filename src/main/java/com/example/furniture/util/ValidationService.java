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
                try {
                    Object obj = method.invoke(classHref, null);
                    if (obj == null) {
                        System.out.println("ERROR en"+method.getName());
                        return false;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public void updateVal(Object obj1Update, Object objValues){
        Method methods[] = obj1Update.getClass().getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            String name = method.getName();
            if(name.contains("set") && !name.toUpperCase().contains("ID")){
                name = name.replace("set", "get").toUpperCase();
                for(int j = 0; j <methods.length; j++){
                    Method m3 = methods[j];
                    if(m3.getName().toUpperCase().equals(name)){
                        try{
                            Object value = m3.invoke(objValues,null);
                            if(value !=null){
                                method.invoke(obj1Update, new Object[]{value});
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }
}
