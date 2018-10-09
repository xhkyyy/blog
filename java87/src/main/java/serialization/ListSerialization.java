package serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSerialization {
    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();

        list.add("hello");

        // 序列化
        FileOutputStream fos = new FileOutputStream(ListSerializationConstant.FILE_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();

        System.out.println(list);

    }
}
