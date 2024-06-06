import java.io.File;

public class Driver {
  public static void main(String[] args) throws Exception {
     // NOTE: The actual driver program will run tests on the output and input streams.
     // This shell just shows the methods that will be called on them.
     BitOutputStream bos = new BitOutputStream(new File("/tmp/foo"));
     bos.writeBit('0');
     bos.writeBits("010101");
     bos.close();

     BitInputStream bis = new BitInputStream(new File("/tmp/foo"));
     bis.readBit();
     bis.readBits();
     bis.close();
  }
}
