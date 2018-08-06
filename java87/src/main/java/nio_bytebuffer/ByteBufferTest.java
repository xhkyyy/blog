package nio_bytebuffer;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ByteBufferTest {

    static final String utf8 = "utf8";


    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        System.out.println("byteBuffer is direct buffer : " + byteBuffer.isDirect());
        System.out.println("byteBuffer capacity is : " + byteBuffer.capacity());
        System.out.println("byteBuffer limit is : " + byteBuffer.limit());
        System.out.println("byteBuffer initial value : " + byteBuffer);

        byte[] bytes = "Netty".getBytes(utf8);

        System.out.println("1>" + byteBuffer.position());
        for (byte b : bytes) {
            byteBuffer.put(b);
            System.out.println("#byteBuffer value : " + byteBuffer);
        }
        System.out.println("2>" + byteBuffer.position());

        System.out.println(">>>>" + bufToString(byteBuffer));

        int position = byteBuffer.position();

        System.out.println("byteBuffer limit is : " + byteBuffer.limit());
        //java.nio.BufferUnderflowException
        //byteBuffer.get();

        // position to zero
        byteBuffer.rewind();

        System.out.println((char) byteBuffer.get());
        System.out.println("after get, byteBuffer value 0 : " + byteBuffer);

        System.out.println((char) byteBuffer.get());
        System.out.println("after get, byteBuffer value 1 : " + byteBuffer);

        // limit -> position & position -> 0
        byteBuffer.flip();

        System.out.println(byteBuffer);

        System.out.println((char) byteBuffer.get(0));
        System.out.println((char) byteBuffer.get(1));

        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.position(position);
        byteBuffer.put(" ! ".getBytes(utf8));

        System.out.println(">>>>" + bufToString(byteBuffer));

    }

    public static String bufToString(ByteBuffer buf) throws UnsupportedEncodingException {
        int position = buf.position();
        buf.flip();
        byte[] b = new byte[position];
        buf.get(b);
        return new String(b, "utf8");
    }
}
