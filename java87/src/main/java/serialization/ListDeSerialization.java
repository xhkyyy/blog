package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ListDeSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<String> list = new ArrayList<>();

        // 反序列化
        FileInputStream fis = new FileInputStream(ListSerializationConstant.FILE_PATH);
        ObjectInputStream ois = new ObjectInputStream(fis);

        list = (List<String>) ois.readObject();

        ois.close();
        fis.close();

        System.out.println(list);

    }
}
