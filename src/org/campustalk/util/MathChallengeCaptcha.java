/**
 * CommonUtils General Utility Feature-set.
 */
package org.campustalk.util;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.campustalk.security.MD5Hash;

/**
 * CommonUtils v1.0
 * 
 * @author Kushal Pandya <https://github.com/kushalpandya>
 * License: GPLv3
 * 
 * Random Mathematical Challenge Generator Class.
 */
public class MathChallengeCaptcha
{
	private Random gen;
	private MD5Hash md5;
	private int oprnd1, oprnd2, oprtr, ans, max;
	private String hash;
	
	/**
	 * Initializes this MathChallengeCaptcha object with 10 as maximum possible value of numbers used in generated challenge.
	 * @throws NoSuchAlgorithmException 
	 */
	public MathChallengeCaptcha() throws NoSuchAlgorithmException
	{
		gen = new Random();
		md5 = new MD5Hash();
		this.max = 11;
	}
	
	/**
	 * Initializes this MathChallengeCaptcha object with provided maximum possible value of numbers used in generated challenge.
	 * @throws NoSuchAlgorithmException 
	 */
	public MathChallengeCaptcha(int max) throws NoSuchAlgorithmException
	{
		gen = new Random();
		md5 = new MD5Hash();
		this.max = max;
	}
	
	/**
	 * Gets Mathematical Challenge as a JSON String that contains an expression values; operand1, operand2, operator and MD5 Hash code of answer.
	 * Example:
	 * If challenge is 2 * 5 = 10, then it will represented as following Key-Value pairs in JSON String.
	 * 
	 * operand1 : 2
	 * operand2 : 5
	 * operator : '*'
	 * answerhash : "d3d9446802a44259755d38e6d163e820"
	 * 
	 * (here, value of key "answerhash" is MD5 hash code of answer "10").
	 * @return String representing JSON of MathCAPTCHA challenge.
	 */
	public String getChallenge()
	{
		while(true)
		{
			oprnd1 = gen.nextInt(max);
			oprnd2 = gen.nextInt(max);
			if(oprnd1 > oprnd2)
				break;
		}
		
		oprtr = gen.nextInt(3);
		char opchar;
		
		switch(oprtr)
		{
			case 0:
				opchar = '+';
				ans = oprnd1+oprnd2;
				break;
			case 1:
				opchar = 'x';
				ans = oprnd1*oprnd2;
				break;
			default:
				opchar = '-';
				ans = oprnd1-oprnd2;
		}
		
		hash = md5.getStringHash(""+ans);
		
		return "{\"operand1\":"+oprnd1+",\"operand2\":"+oprnd2+",\"operator\":\'"+opchar+"\',\"answerhash\":\""+hash+"\"}";
	}

}
