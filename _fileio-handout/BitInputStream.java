import java.io.*;

/**
 * This class provides methods to read a stream of bits from a file.
 *
 * @author Samriddhi Matharu
 */
public class BitInputStream {
    private FileInputStream inputStream;
    private int currentByte;
    private int numBitsRemaining;

    /**
     * Constructs a new BitInputStream to read bits from the specified file.
     *
     * @param file the file to read bits from
     * @throws IOException if an I/O error occurs
     */
    public BitInputStream(File file) throws IOException {
        inputStream = new FileInputStream(file);
        currentByte = 0;
        numBitsRemaining = 0;
    }

    /**
     * Returns the number of bits available in the file.
     *
     * @return the number of bits available
     */
    public int available() {
        try {
            return inputStream.available() * 8 + numBitsRemaining;
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Reads the next bit from the input stream.
     *
     * @return the next bit ('0' or '1') read from the stream
     * @throws IOException if an I/O error occurs
     */
    public char readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = inputStream.read();
            if (currentByte == -1) {
                throw new EOFException();
            }
            numBitsRemaining = 8;
        }
        char bit = ((currentByte >> (numBitsRemaining - 1)) & 1) == 1 ? '1' : '0';
        numBitsRemaining--;
        return bit;
    }

    /**
     * Reads the remainder of the file and returns the bit string.
     *
     * @return the bit string read from the file
     * @throws IOException if an I/O error occurs
     */
    public String readBits() throws IOException {
        StringBuilder bits = new StringBuilder();
        while (true) {
            try {
                char bit = readBit();
                bits.append(bit);
            } catch (EOFException e) {
                break;
            }
        }
        return bits.toString();
    }

    /**
     * Closes the input stream.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        inputStream.close();
    }
}