//package testDynamicProxy;
//
//import com.squareup.javapoet.FieldSpec;
//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//import com.squareup.javapoet.TypeSpec;
//
//import javax.lang.model.element.Modifier;
//import javax.tools.StandardJavaFileManager;
//import javax.tools.ToolProvider;
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.nio.channels.FileLockInterruptionException;
//
//public class Proxy {
//
//    public static Object newProxyInstance(Class inf, InvocationHandler handler) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
//        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
//                .addModifiers(Modifier.PUBLIC)
//                .addSuperinterface(inf);
//
////        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
////        typeSpecBuilder.addField(fieldSpec);
//        FieldSpec fieldSpec = FieldSpec.builder(InvocationHandler.class, "handler", Modifier.PRIVATE).build();
//        typeSpecBuilder.addField(fieldSpec);
//
//
//        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
//                .addModifiers(Modifier.PUBLIC)
//                .addParameter(InvocationHandler.class, "handler")
//                .addStatement("this.handler = handler")
//                .build();
////                .addParameter(Flyable.class, "flyable")
////                .addStatement("this.flyable = flyable")
////                .build();
//        typeSpecBuilder.addMethod(constructorMethodSpec);
//
////        Method[] methods = Flyable.class.getDeclaredMethods();
////        for (Method method : methods) {
////            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
////                    .addModifiers(Modifier.PUBLIC)
////                    .addAnnotation(Override.class)
////                    .returns(method.getReturnType())
////                    .addStatement("long start = $T.currentTimeMillis()", System.class)
////                    .addCode("\n")
////                    .addStatement("this.flyable." + method.getName() + "()")
////                    .addCode("\n")
////                    .addStatement("long end = $T.currentTimeMillis()", System.class)
////                    .addStatement("$T.out.println(\"Fly Time =\" + (end - start))", System.class)
////                    .build();
////            typeSpecBuilder.addMethod(methodSpec);
////        }
//        Method[] methods = inf.getDeclaredMethods();
//        for (Method method : methods) {
//            Parameter[] parameters = method.getParameters();
//            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
//                    .addModifiers(Modifier.PUBLIC)
//                    //.addParameter(Object[].class, "args")
//                    .addAnnotation(Override.class)
//                    .returns(method.getReturnType())
//                    .addCode("try {\n")
//                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class)
//                    // 为了简单起见，这里参数直接写死为空
//                    .addStatement("\tthis.handler.invoke(this, method, null)")
//                    .addCode("} catch(Exception e) {\n")
//                    .addCode("\te.printStackTrace();\n")
//                    .addCode("}\n")
//                    .build();
//
//            typeSpecBuilder.addMethod(methodSpec);
//        }
//
//        JavaFile javaFile = JavaFile.builder("testDynamicProxy.proxy", typeSpecBuilder.build()).build();
//        // 为了看的更清楚，我将源码文件生成到桌面
//        javaFile.writeTo(new File("E:\\test1\\src\\main\\java\\"));
//// 编译
//        javax.tools.JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//        javaCompiler.run(null, null, null, "E:\\test1\\src\\main\\java\\testDynamicProxy\\proxy\\TimeProxy.java");
//
//
//        URL[] urls = new URL[]{new URL("file:/" + "E:\\test1\\src\\main\\java\\")};
//        URLClassLoader classLoader = new URLClassLoader(urls);
//        Class clazz = classLoader.loadClass("testDynamicProxy.proxy.TimeProxy");
//        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
//        Object obj = constructor.newInstance(handler);
//
//            return obj;
//    }
//
//}