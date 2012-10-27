/**
 * CommonUtils Security Feature-set.
 */
package org.campustalk.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CommonUtils v1.0
 * 
 * @author Kushal Pandya <https://github.com/kushalpandya>
 * License: GPLv3
 * 
 * String and File MD5 Hash-code Generator Class. 
 */
public class MD5Hash
{
	private MessageDigest md;
	private byte[] digest;
	private BigInteger bigInt;
	private String hash;
	
	/**
	 * Initializes this MD5Hash object with MD5 algorithm and resets the digest.
	 * @throws NoSuchAlgorithmException 
	 */
	public MD5Hash() throws NoSuchAlgorithmException
	{
		md = MessageDigest.getInstance("MD5");
		md.reset();
	}
	
	/**
	 * Gets 32 character MD5 Hash String for the given key.
	 * @param key String for which Hash code is to be computed.
	 * @return String representing MD5 Hash code.
	 */
	public String getStringHash(String key)
	{
		String source = key;
		md.update(source.getBytes());
		digest = md.digest();
		bigInt = new BigInteger(1,digest);
		hash = bigInt.toString(16);
		while(hash.length()<32)
			hash = "0" + hash;
		
		return hash;
	}
	
	/**
	 * Gets 32 character MD5 Hash String for the given File.
	 * @param source File for which Hash code is to be computed.
	 * @return String representing MD5 Hash code.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getFileHash(File source) throws FileNotFoundException, IOException
	{
		@SuppressWarnings("unused")
		int i = 0;
		InputStream is = new FileInputStream(source);
		is = new DigestInputStream(is, md);
		while((i=is.read())!=-1)
		{
			is.read();
		}
		is.close();
		digest = md.digest();
		bigInt = new BigInteger(1,digest);
		hash = bigInt.toString(16);
		while(hash.length()<32)
			hash = "0" + hash;
		
		return hash;
	}
}
