
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class main {

	public static void main(String[] args) throws Exception {
		MyLogger logger = new MyLogger();
		
	        
		DES d = null;
		AES a = null;
		MyCipher cipher=new MyCipher();
		switch (args.length) {
		case 10:
			cipher=new MyCipher(args[0], Integer.parseInt(args[2]), args[4], args[6], args[7], args[8], args[9]);
			break;
		case 8:
			cipher=new MyCipher(args[0], 0, args[2], args[4], args[5], args[6], args[7]);
			break;
			
		}

		String[] key=Key(cipher.Keyfile);
		long startTime = System.nanoTime();
		int control=0;
		if(cipher.Algorithm.equals("DES")){
			 d=new DES();
			 if(key[1].length() == 8 && key[0].length() == 8){
				 control=1;
				 d.DESAlgorithm(cipher, key[1].getBytes(), key[0]);
			 }
			 else{
				 System.out.println("The key length and the algorithm does not match.");
			 }
		}
		else{
			 a=new AES(); 
			 	if(key[1].length() == 16 && key[0].length() == 16){
			 		control=1;
			 		a.AESAlgorithm(cipher, key[1].getBytes(), key[0]);
			 }
			 else {
			 		System.out.println("The key length and the algorithm does not match.");
			 }
		}
		 long endTime = System.nanoTime();

		 long duration = (endTime - startTime)/1000000;
		 if(control == 1)
			 logger.info(cipher,duration); 
	
	}
	

  public static  String[] Key(String fileName) throws FileNotFoundException, IOException{
		  
		  String x=new String("");
		  File f=new File(fileName);
		  BufferedReader br = new BufferedReader(new FileReader(f));
			    String line;
			    while ((line = br.readLine()) != null) {
			        x=line;
			    }
			    String[] array=x.split(" - ");
			    br.close();
		  return array;
	 }

}

