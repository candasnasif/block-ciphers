import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLogger {
	
	public void info(MyCipher cipher, Long time) throws IOException {
		FileWriter x=new FileWriter("run.log",true);
		PrintWriter y = new PrintWriter(x);
		y.println(cipher.Infile+" "+cipher.Outfile+" "+cipher.Mode+" "+cipher.Algorithm+" "+cipher.WhichMode+" "+time+" ms");
		x.close();
	}

}
