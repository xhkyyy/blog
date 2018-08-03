package hession_issue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 序列化后精度丢失
 */
public class TestFloat {

    @org.junit.Test
    public void test() throws IOException {

        Teacher teacher = new Teacher();

        teacher.getContentMap().put("price", Float.valueOf(21.32f));

        teacher.setaFloat(Float.valueOf(21.88f));

        byte[] bytes = Test.serialize(teacher);

        Object obj = Test.deserialize(bytes);

        System.out.println(obj);

        System.out.println();

        HashMap<String, Number> cm = ((Teacher) (obj)).getContentMap();

        Map<String, Object> contentMap = (Map) cm;

        System.out.println(">>>>>" + contentMap);
    }
}
