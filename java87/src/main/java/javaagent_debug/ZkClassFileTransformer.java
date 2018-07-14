package javaagent_debug;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ZkClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        try {

            String classFilePath = "/Users/Len/Downloads/gc/";

            if ("com/alibaba/dubbo/registry/zookeeper/ZookeeperRegistry".equals(className)
                    ) {
                classfileBuffer = readFileToByteArray(new File(classFilePath + "ZookeeperRegistry.class"));
            } else if ("com/alibaba/dubbo/registry/zookeeper/ZookeeperRegistry$1".equals(className)
                    ) {
                classfileBuffer = readFileToByteArray(new File(classFilePath + "ZookeeperRegistry$1.class"));
            } else if ("com/alibaba/dubbo/registry/zookeeper/ZookeeperRegistry$2".equals(className)
                    ) {
                classfileBuffer = readFileToByteArray(new File(classFilePath + "ZookeeperRegistry$2.class"));
            } else if ("com/alibaba/dubbo/registry/zookeeper/ZookeeperRegistry$3".equals(className)
                    ) {
                classfileBuffer = readFileToByteArray(new File(classFilePath + "ZookeeperRegistry$3.class"));
            }
        } catch (Throwable ignored) {
            ignored.printStackTrace();
        }

        return classfileBuffer;
    }


    public static byte[] readFileToByteArray(File file) throws IOException {
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();
        return bytesArray;
    }
}