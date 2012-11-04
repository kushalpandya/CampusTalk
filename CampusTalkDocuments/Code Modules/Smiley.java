import javax.swing.*;



public class Smiley {
	
	public static void main(String args[])
	{
		String comment = JOptionPane.showInputDialog("Enter Your Comment");
		String returnComment = replaceComment(comment);
		JOptionPane.showMessageDialog(null, returnComment);
	}
	
	/**
	 * Function to Display Smiley Symbol in  Comments
	 * @param com
	 * @return
	 */
	
	static String replaceComment(String com)
	{
		StringBuffer strBfr = new StringBuffer(com);
		
		
		for(int i = 0; i < strBfr.length() ; i++ )
		{
			if(strBfr.charAt(i)==':' || strBfr.charAt(i)==';' || strBfr.charAt(i)=='8')
			{
				if(strBfr.charAt(i+1)=='-')
				{
					strBfr.deleteCharAt(i+1);
				}
							
			}
			
			switch(strBfr.charAt(i))
			{
			case ':':
				switch(strBfr.charAt(i+1))
				{
					case ')':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "HappySmile");
					break;
				
					case '(':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "SadSmiley");
					break;
				
					case 'D':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "LaughterSmiling");
					break;
			
					case '\'':
					if(strBfr.charAt(i+2)=='(')
					{
						strBfr = strBfr.delete(i, i+3);
						strBfr = strBfr.insert(i, "CryingSmiley");
					}
					break;	
 					
					case 'p':
					case 'P':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "PSmiley");
					
 					break;
 					
					case '@':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "AngrySmiley");
					break;
 				
					case 's':
					case 'S':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "ConfusedSmiley");
					break;
 					
					case '$':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "EmbaassedSmiley");
					break;
 				
					case '|':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "DisappointemntSmiley");
					break;
 					
					case '\\':
					strBfr = strBfr.delete(i, i+2);
					strBfr = strBfr.insert(i, "ThinkSmiley");
					break;
 				
					case '*':
						strBfr = strBfr.delete(i, i+2);
						strBfr = strBfr.insert(i, "KissSmiley");
						break;
				}
					
		
				
				case ';':
				    switch(strBfr.charAt(i+1))
				    {
				    case ')':
						strBfr = strBfr.delete(i, i+2);
						strBfr = strBfr.insert(i, "WinkSmile");
						break;
				    }
					break;
					
				case '8':
					 switch(strBfr.charAt(i+1))
					    {
					    	case ')':
							strBfr = strBfr.delete(i, i+2);
							strBfr = strBfr.insert(i, "EyeRollSmiley");
							break;
					    }
						break;
				
		}
			
		
		}
		return strBfr.toString();
		
	}

}
