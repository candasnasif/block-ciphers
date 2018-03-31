public class MyCipher {
	String Mode;
	String Infile;
	String Outfile;
	String Algorithm;
	String WhichMode;
	String Keyfile;
	int NumberOfThreads=0;
	
	public MyCipher(
			) {
		
	}
	
	public MyCipher(String mode, int numberOfThreads, String infile, String outfile, String algorithm, String whichMode, String keyfile
			) {
		super();
		String[] PMode = mode.split("-");
		Mode = PMode[1];
		NumberOfThreads = numberOfThreads;
		Infile = infile;
		Outfile = outfile;
		Algorithm = algorithm;
		WhichMode = whichMode;
		Keyfile = keyfile;
	}
}
