package direct_byte_buffer;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class DirectByteBufferTest {
    public static void main(String[] args) {

        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024);
        ((DirectBuffer) bb).cleaner().clean();


    }
}
