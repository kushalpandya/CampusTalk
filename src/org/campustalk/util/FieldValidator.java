/**
 * CommonUtils General Utility Feature-set.
 */
package org.campustalk.util;

/**
 * CommonUtils v1.0
 * 
 * @author Kushal Pandya <https://github.com/kushalpandya>
 * License: GPLv3
 * 
 * Regex-based Field Values Validator Class.
 */
public class FieldValidator
{
	private String regExNumber = "\\d*";
	private String regExFloat = "[-+]?[0-9]*\\.?[0-9]+";
	private String regExAlphabet = "\\D\\w*";
	private String regExDate = "^\\d{1,2}[-/.]\\d{1,2}[-/.]\\d{1,4}$";
	private String regExEmail = "^(.+)@(.+)$";
	private String regExPhone = "^([0-9\\(\\)\\/\\+ \\-]*)$";
	private String regExSentence = "[^A-Za-z0-9-\\s.,]";
	private String regExSimplePassword = ".{8,}"; 
	private String regExPassword = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[\\W]).*$"; // or alternate "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
	
	private String regExPattern;
	
	/**
	 * Initializes this FieldValidator object without any custom pattern.
	 */
	public FieldValidator()
	{
		regExPattern = null;
	}
	
	/**
	 * Initializes this FieldValidator object with custom pattern.
	 * @param pattern
	 */
	public FieldValidator(String pattern)
	{
		regExPattern = pattern;
	}
	
	/**
	 * Checks whether given String key matches with defined custom pattern.
	 * @param key String to be checked.
	 * @return boolean representing successful match with pattern.
	 * @throws UndefinedPatternException
	 */
	public boolean isPattern(String key) throws UndefinedPatternException
	{
		if(regExPattern!=null)
			return key.matches(regExPattern);
		else
			throw new UndefinedPatternException("Custom Pattern Not Defined");
	}
	
	/**
	 * Checks whether given String key is a valid number.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isNumber(String key)
	{
		return key.matches(regExNumber);
	}
	
	/**
	 * Checks whether given String key is a valid floating point number.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isFloat(String key)
	{
		return key.matches(regExFloat);
	}

	/**
	 * Checks whether given String key is a valid Alphabet.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isAlphabet(String key)
	{
		return key.matches(regExAlphabet);
	}

	/**
	 * Checks whether given String key is a valid Date.
	 * Valid format for Date is standard Indian date format, i.e. DD-MM-YYYY (where, allowed separators are - or . or /)
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isDate(String key)
	{
		return key.matches(regExDate);
	}
	
	/**
	 * Checks whether given String key is a valid Date as per given format pattern regex.
	 * @param key String to be checked.
	 * @param formatpattern String Regular Expression for Custom Date format.
	 * @return boolean representing successful match.
	 */
	public boolean isDate(String key, String formatpattern)
	{
		return key.matches(formatpattern);
	}

	/**
	 * Checks whether given String key is a valid Email address.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isEmail(String key)
	{
		return key.matches(regExEmail);
	}

	/**
	 * Checks whether given String key is a valid Phone number.
	 * Valid phone number contains only numbers, a dash (-) and a plus sign (+), with no limits in length of number.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isPhone(String key)
	{
		return key.matches(regExPhone);
	}

	/**
	 * Checks whether given String key is a valid simple Password.
	 * Valid Simple password is at least 8 characters long with no restrictions on type of characters.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isSimplePassword(String key)
	{
		return key.matches(regExSimplePassword);
	}
	
	/**
	 * Checks whether given String key is a valid Password.
	 * Valid Password must contain a minimum of one lower case character, one upper case character, one digit, one special character, and at least 8 characters long.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isPassword(String key)
	{
		return key.matches(regExPassword);
	}

	/**
	 * Checks whether given String key is a valid Sentence.
	 * Valid sentence contains alphabets, numbers, a space, a comma and full-stop only.
	 * @param key String to be checked.
	 * @return boolean representing successful match.
	 */
	public boolean isSentence(String key)
	{
		return key.matches(regExSentence);
	}
}

/**
 * CommonUtils v1.0
 * 
 * @author Kushal Pandya <https://github.com/kushalpandya>
 * License: GPLv3
 * 
 * Custom Exception, thrown when pattern is not defined and isPattern() (from FieldValidator class) is used.
 */
class UndefinedPatternException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public UndefinedPatternException(String message)
	{
		super(message);
	}
}
