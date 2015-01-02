package bookrental;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

	private String a,b,c,d,e;
	
	///////////////////////////
	//		Attributes       //
	///////////////////////////

	///////////////////////////
	//	   Constructors      //
	///////////////////////////	

	
	public Test(String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	public Test(String a, String b, String c, String d) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Test(String a, String b, String c, String d, String e) {
		this(a, b, c, d);
		this.e = e;
	}
	
	
	///////////////////////////
	//		 Methods        //
	///////////////////////////

	
	
		
}
