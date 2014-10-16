package com.yx.online.tools;

import android.widget.EditText;


public class StringUtil
{
	public static String a(EditText paramEditText)
	  {
		 String str="";
	    if (paramEditText == null)
	    {
	    	return null;
	    }
	  
	     str = paramEditText.getText().toString().trim();
	   
	    return str;
	  }
	
	public static String a(String paramString)
	  {
	    if (paramString == null)
	      return "";
	    return paramString.replace("&ldquo;", "“").replace("&rdquo;", "”").replace("&quot;", "\"").replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<o:p>", "").replaceAll("</o:p>", "");
	  }
	/**
     * @author : tiankang
     * @param str
     * @return
     */
    public static String filterEnter(String str)
    {
    	int ind;
    	StringBuffer sb = new StringBuffer();
    	while((ind = str.lastIndexOf("\n")) != -1)
    	{
        	sb.append(str.substring(0,ind));
        	sb.append("\\n");
        	sb.append(str.substring(ind+1));
        	str = sb.toString();
        	sb.delete(0, str.length());
    	}
    	return str;
    }
    
    public static boolean checkStringIsNull( String str )
    {
        if ( null==str ||  "".equals( str ) || "null".equals( str ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

