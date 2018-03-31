
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	
	private  byte[] encrypt(byte[] inpBytes,
			SecretKey key, String xform, String iv) throws Exception {
		
		Cipher cipher = Cipher.getInstance(xform);
	    IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
	    cipher.init(Cipher.ENCRYPT_MODE, key, ips);
	    return cipher.doFinal(inpBytes);
	 	
	}

	
	private  byte[] decrypt(byte[] inpBytes,
			SecretKey key, String xform, String iv) throws Exception {
		
		Cipher cipher = Cipher.getInstance(xform);
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, key, ips);
		return cipher.doFinal(inpBytes);
	  
	}
	
	
	public    byte[]  ReadFile(String fileName) throws IOException{
		
		File inputFile=new File(fileName);
		FileInputStream inputStream=new FileInputStream(inputFile);	
		byte[] result= new byte[(int)inputFile.length()];
		inputStream.read(result);
		inputStream.close();
		return result;	
	 
	}
	public  void DESAlgorithm(MyCipher cipher, byte[] keyBytes, String iv) throws Exception{
		
		String xform = "DES/"+cipher.WhichMode+"/PKCS5Padding";
		SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
		 	
		byte[] dataBytes ;
		 
		if(cipher.Mode.equals("e")){
			dataBytes=ReadFile(cipher.Infile);
			byte[] encBytes = encrypt(dataBytes, key, xform,iv);
			FileOutputStream fos = new FileOutputStream(cipher.Outfile);
			fos.write(encBytes);
			fos.close();
		}
		else if(cipher.Mode.equals("d")){
			try{
				FileInputStream fstream = new FileInputStream("run.log");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;
				/* read log line by line */
				int count = 0;
				while ((strLine = br.readLine()) != null)   {
					String[] parsedLine = strLine.split(" ");
					if (parsedLine[1].equals(cipher.Infile) && parsedLine[3].equals(cipher.Algorithm) && parsedLine[4].equals(cipher.WhichMode)) {
						dataBytes=ReadFile(cipher.Infile);
						byte[] decBytes = decrypt(dataBytes, key, xform, iv);
						FileOutputStream fos = new FileOutputStream(cipher.Outfile);
						fos.write(decBytes);
						fos.close();
						count++;
						break;
					}
				}
				if (count == 0) {
					System.out.println("You can't decrypt a file with a different algorithm or mode.");
					System.exit(1);
				}
					
				fstream.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	 }
	
}
