/**
 * CommonUtils Security Feature-set.
 */
package org.campustalk.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CommonUtils v1.0
 * 
 * @author Kushal Pandya <https://github.com/kushalpandya>
 * License: GPLv3
 * 
 * String and File SHA1 Hash-code Generator Class. 
 */
public class SHA1Hash
{
	private MessageDigest md;
	private byte[] digest;
	private String hash;
	
	/**
	 * Initializes this SHA1Hash object with SHA-1 algorithm and resets the digest.
	 * @throws NoSuchAlgorithmException 
	 */
	public SHA1Hash() throws NoSuchAlgorithmException
	{
		md = MessageDigest.getInstance("SHA-1");
		md.reset();
	}
	
	/**
	 * Gets 41 character SHA-1 Hash String for the given key.
	 * @param key String for which Hash code is to be computed.
	 * @return String representing SHA-1 Hash code.
	 */
	public String getStringHash(String key)
	{
		md.update(key.getBytes());
		digest = md.digest();
		for(int i=0; i<digest.length; i++)
			hash += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		
		return hash;
	}

	/**
	 * Gets 41 character SHA-1 Hash String for the given File.
	 * @param source File for which Hash code is to be computed.
	 * @return String representing MD5 Hash code.
	 * @throws IOException
	 */
	public String getFileHash(File source) throws IOException
	{
		@SuppressWarnings("unused")
		int c = 0;
		InputStream is = new FileInputStream(source);
		
		is = new DigestInputStream(is, md);
		while((c=is.read())!=-1)
		{
			is.read();
		}
		is.close();
		
		digest = md.digest();
		
		for(int i=0; i<digest.length; i++)
			hash += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		
		return hash;
	}
}
