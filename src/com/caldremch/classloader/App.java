package com.caldremch.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        try {
           Class clz =  classLoader.loadClass("com.caldremch.classloader.MyTestClassLoad");
           System.out.println(clz.getClassLoader());
           Object object = clz.newInstance();
            Method method = clz.getMethod("print");
            method.invoke(object, null);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
