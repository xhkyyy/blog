package hession_issue;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * dubbo 2.5.7 之前，Hessian 序列化存在以下问题：当子类和父类都存在相同字段时，Hessian 反序列化后，该字段无法被正确序列化。
 * 原因是：Hessian 序列化子类时，相同到字段分别在父类和子类都提取了一遍。
 * 在反序列化时第一次能获取到字段到值，第二次则是字段对应到零值，也即默认值，对于 int 默认值是0，对于 string ，默认值是 null
 */
public class Test {

    @org.junit.Test
    public void test() throws IOException {
        User user = new Student("hello world1");
        user.setUserId(123);
        user.setUserName("Luck");

        byte[] bytes = serialize(user);

        Object obj = deserialize(bytes);

        Assert.assertTrue(obj.toString().contains("userId=0"));
        System.out.println(obj);


        Student student = new Student("hello world2");
        student.setUserId(123);
        student.setUserName("Luck");

        bytes = serialize(student);

        Object obj2 = deserialize(bytes);

        Assert.assertTrue(obj2.toString().contains("userId=0"));
        System.out.println(obj2);
    }

    static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Hessian2Output h2o = new Hessian2Output(bao);
        byte[] bytes = null;
        try {
            if (obj == null) throw new NullPointerException();
            h2o.writeObject(obj);
            h2o.flushBuffer();
            bytes = bao.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            h2o.close();
        }
        return bytes;

    }

    static Object deserialize(byte[] bytes) throws IOException {
        try {
            if (bytes == null) {
                throw new NullPointerException();
            }
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            Hessian2Input hi = new Hessian2Input(is);
            return hi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
