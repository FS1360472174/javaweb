package nio;

import java.nio.ByteBuffer;

public class FlipTest {
	public static void main(String[] args) {
		byte[] bytes1=new byte[]{1, 6, 3};
		ByteBuffer buffer =fromByteArray(bytes1);
		System.out.println(buffer);
		byte[] bytes2 =new byte[]{1,2,3};
		ByteBuffer buffer2=fromByteArray(bytes2);
		System.out.println(buffer2);
	}
	
	/**
	 * If you are building up a ByteBuffer by repeatedly writing into it, and then want to give it away, you must remember to flip() it. 
	 * If we did not flip() it, the returned ByteBuffer would be empty because the position would be equal to the limit.
	 * @param bytes
	 * @return
	 */
	public static ByteBuffer fromByteArray(byte[] bytes) {
	    final ByteBuffer ret = ByteBuffer.wrap(new byte[bytes.length]);
	 
	    ret.put(bytes);
	    ret.flip();
	 
	    return ret;
	} 
}
