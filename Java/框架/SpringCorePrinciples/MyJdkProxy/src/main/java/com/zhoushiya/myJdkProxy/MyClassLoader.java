package com.zhoushiya.myJdkProxy;

import java.io.*;

/**
 * @author zhoushiya
 * @date 2020/9/24 19:03
 */
public class MyClassLoader extends ClassLoader {
    private File classPathFile;

    public MyClassLoader() throws UnsupportedEncodingException {
        String classPath = java.net.URLDecoder.decode(MyClassLoader.class.getResource("").getPath(), "utf-8");
        this.classPathFile = new File(classPath);
    }

    /**
     * 根据给定名称查找
     *
     * @param name
     * @return
     */
    protected Class<?> findClass(String name) {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile != null) {
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()) {
                try (FileInputStream in = new FileInputStream(classFile);
                     ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
