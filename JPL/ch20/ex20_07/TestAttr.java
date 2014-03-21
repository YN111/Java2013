import java.io.*;

import junit.framework.TestCase;


public class TestAttr extends TestCase{
	
	public void testConvertStream() {
		String name = "name";
		Object value = "value";
		
		Attr origAttr = new Attr(name, value);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		origAttr.getStream(dos);
		byte[] out = baos.toByteArray();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(out);
		DataInputStream dis = new DataInputStream(bais);
		Attr copyAttr = new Attr(dis);
		
		assertEquals(copyAttr.getName(), name);
		assertEquals(copyAttr.getValue(), value);
	}
}
