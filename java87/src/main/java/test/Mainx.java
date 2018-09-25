package test;

import java.util.HashMap;
import java.util.Map;

public class Mainx {
    public static void main(String[] args) {

        Map<String, String> m = new HashMap<String, String>();
        m.put("abc", "123");
        m.put("abc`", "123");
        m.put("sdf`", "123");

        System.out.println(m.size());

    }
}
