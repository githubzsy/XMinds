package com.zhoushiya.myJdkProxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoushiya
 * @date 2020/9/24 19:02
 */
public class MyProxy {
    public static final String ln = "\r\n";

    /**
     * 创建代理类实例
     *
     * @param classLoader 加载器
     * @param interfaces  要代理的接口
     * @param h           handler
     * @return 代理类实例对象
     */
    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, IMyInvocationHandler h) {
        try {
            String src = generateProxySrc(interfaces);
            String filePath = java.net.URLDecoder.decode(MyProxy.class.getResource("").getPath(), "utf-8");
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            Constructor<?> constructor = proxyClass.getConstructor(IMyInvocationHandler.class);
            // f.delete();

            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成接口的代理类java源代码
     *
     * @param interfaces 接口
     * @return 代理类的源代码
     */
    public static String generateProxySrc(Class<?>[] interfaces) {
        /** 假设静态代理代码
         public interface IAnimal{
         void eat(String food);
         }

         public class Animal implements IAnimal{
         public void eat(String food){

         }
         }

         public class AnimalProxy implements IAnimal{
         private Animal animal;
         public AnimalProxy(Animal animal){
         this.animal=animal;
         }


         public void eat(String food){

         }
         }
         */

        /** 那么以下代码生成的java源码为：
         * package com.zhoushiya.myJdkProxy;
         * package java.lang.reflect.*;
         *
         * public class $Proxy0 implements IAnimal {
         *  IMyInvocationHandler h;
         *  public $Proxy0(IMyInvocationHandler h){
         *      this.h=h;
         *  }
         *
         * public void eat(String food){
         *  try{
         *      Method m=IAnimal.class.getMethod("eat",new Class[]{String.class});
         *      this.h.invoke(this,m,new Object[]{food});
         *  }catch(Error _ex){}
         *  catch(Throwable e){
         *      throw new UndeclaredThrowableException(e);
         *  }
         *  }
         * }
         */

        StringBuffer sb = new StringBuffer();
        // package
        sb.append("package com.zhoushiya.myJdkProxy;");
        sb.append(ln);

        // package java.lang.reflect;
        sb.append("import java.lang.reflect.*;");
        sb.append(ln);

        // public class $Proxy0 implements IAnimal {
        sb.append("public class $Proxy0 implements ");
        sb.append(interfaces[0].getName());
        sb.append("{");
        sb.append(ln);

        // IMyInvocationHandler h;
        sb.append("IMyInvocationHandler h;");
        sb.append(ln);

        /**
         *  public $Proxy0(IMyInvocationHandler h){
         *      this.h=h;
         *  }
         */
        sb.append("public $Proxy0(IMyInvocationHandler h){");
        sb.append(ln);
        sb.append("this.h=h;");
        sb.append("}");
        sb.append(ln);

        for (Method method : interfaces[0].getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            /**
             * 记录参数类型和名称，类似
             * String food
             */
            StringBuffer paramNames = new StringBuffer();

            /**
             * 记录参数名称，类似
             * food
             */
            StringBuffer paramValues = new StringBuffer();

            /**
             * 记录参数类型，类似
             * String.class
             */
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> clazz = parameterTypes[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i > 0 && i < parameterTypes.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }

            /**
             * public void eat(String food){
             * try{
             *  Method m=IAnimal.class.getMethod("eat",new Class[]{String.class});
             *  this.h.invoke(this,m,new Object[]{food});
             * }catch(Error _ex){}
             * catch(Throwable e){
             *  throw new UndeclaredThrowableException(e);
             * }
             * }
             */

            // public void eat(String food){
            sb.append("public ");
            sb.append(method.getReturnType().getName()); // void
            sb.append(" ");
            sb.append(method.getName()); //eat
            sb.append("(");
            sb.append(paramNames.toString());
            sb.append("){");
            sb.append(ln);

            // try{
            sb.append("try{");
            sb.append(ln);

            // Method m=IAnimal.class.getMethod("eat",new Class[]{String.class});
            sb.append("Method m=");
            sb.append(interfaces[0].getName());
            sb.append(".class.getMethod(\"");
            sb.append(method.getName());
            sb.append("\",new Class[]{");
            sb.append(paramClasses.toString());
            sb.append("});");
            sb.append(ln);

            // this.h.invoke(this,m,new Object[]{food});
            sb.append((hasReturnValue(method.getReturnType()) ? "return " : ""));
            sb.append(getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})", method.getReturnType()) + ";" + ln);

            // }catch(Error _ex){}
            sb.append("}catch(Error _ex){}");
            sb.append(ln);

            // catch(Throwable e){
            sb.append("catch(Throwable e){");
            sb.append(ln);

            // throw new UndeclaredThrowableException(e);
            sb.append("throw new UndeclaredThrowableException(e);");
            sb.append(ln);

            // }
            sb.append("}");

            //
            sb.append(getReturnEmptyCode(method.getReturnType()));

            // }
            sb.append("}");
        }
        sb.append("}");
        sb.append(ln);
        return sb.toString();
    }


    private static Map<Class, Class> mappings = new HashMap<Class, Class>();

    static {
        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "return 0;";
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    /**
     * int
     * ((Integer)code).Value()
     *
     * @param code
     * @param returnClass
     * @return
     */
    private static String getCaseCode(String code, Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    /**
     * 将字符转换为小写开头
     *
     * @param src
     * @return
     */
    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
