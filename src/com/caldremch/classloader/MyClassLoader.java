package com.caldremch.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    public byte[] loadClassData(String name){
        FileInputStream fis = null;
        byte[] data = null;

        try {
            String classPath = clzPath+name;
            System.out.println("原类路径: "+classPath);
            classPath = classPath.replaceAll("\\.", "/")+suffix;
            System.out.println("类路径: "+classPath);
//            classPath = classPath.replaceAll("\\\\", ".")
//                    .replaceAll("/", ".")
//                    .replaceAll(":","");
            File file = new File(classPath);
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos =
                    new ByteArrayOutputStream();

            int ch = 0;
            while ((ch = fis.read()) != -1){
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static final String clzPath = "C:/tempclassload/";
    public static final String suffix = ".class";

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(MyClassLoader.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }

}
