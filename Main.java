import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URLConnection;
import java.io.*;

public class Main {

    private static boolean isPng(String filePath) throws IOException {
    	        Path path = Paths.get(filePath);
		InputStream is = new FileInputStream(new File(path.toString()));
		int nBytes = 16;
		byte[] data = new byte[nBytes];
		int nRead = is.read(data, 0, nBytes);
		if (nRead != nBytes) {
			throw new IOException("Could not read the file header");
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : data) {
		        sb.append(String.format("%02X", b));
	   	}
		String header = sb.toString();
		//System.out.println(header);
		// This is a standard normal PNG header
		// http://www.libpng.org/pub/png/spec/1.2/PNG-Structure.html
		String shouldBe= "89504E470D0A1A0A0000000D49484452";
		return shouldBe.equals(header);

    }

    public static void main(String[] args) {
        try {
        	System.out.println(isPng(args[0]));
        } catch (IOException e) {
        	System.out.println(e);
        }
    }
}

