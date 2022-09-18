// Haris Bhatti
// CSC 521: Advanced Web Based Software Development
// Project 1
// 10/1/2021

// File: ReadWeb_MyPage.java
// Test reading webpage directly
// Reads webpage directly, utilizes LibraryOfCongressReader.java and LibraryOfCongressItem.java
// Modified code based on ReadWeb_MyPage.java by Dr. Spiegel
//

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;




public class Info {

 /**
  * Main function, meant to call LibraryOfCongressReader
  * GiveMeWeb gives default first five choices from website by replacing count in query with a number.
  * 1977 is set as the default query year, however it can be modified in the accompanying GUI via a dropdown menu.
  *
  *@param main	a main method that throws an IOExcpetion.  Library of CongressReader.
  *
  */

 public static void main(String args[]) throws java.io.IOException
 {   
	 
	 
new LibraryOfCongressReader("Library of Congress Reader", GiveMeWeb(10, 1977));

 }
 
 /**
  * @param	String s is meant to replace all
  * The html reading taken from scraping the website won't work for application
  * @param	s.replaceAll("\n", ""); removes new lines
  * @param	s = s.replaceAll("\\<.*?\\>", ""); removes excess html code in results
  * In the html code, apostrophes show up as "&#39;", s = s.replaceAll("&#39;", "'"); replaces this with an apostrophe.
  * @param	s.replaceAll("  ", ""); removes all instances of two or more spaces to get rid of excess spaces
  * @return s	returns the string without the html code included
  */
 
 static String format(String s){
	 
	 s = s.replaceAll("\n", "");
	 s = s.replaceAll("\\<.*?\\>", "");
	 s = s.replaceAll("&#39;", "'");
	 s = s.replaceAll("  ", "");
	 return s;
 }
 
  /**
  * @param	HashMap	creates a hashmap
  */
 
 public static HashMap<String, LibraryOfCongressItem> GiveMeWeb(int count, int year) throws java.io.IOException{
	 
	 URL TheFile=null;

 
     try {	// Set up a URL to the file
       TheFile=new URL(
// This is the query that provides KutztownTable.txt
    //    "https://aa.usno.navy.mil/cgi-bin/aa_rstablew.pl?ID=AA&year=2008&task=0&state=PA&place=Kutztown");
// This provides what we need for now...
	 "https://www.loc.gov/film-and-videos/?fa=location%3Aunited+states%7Cpartof%3Ajazz+on+the+screen+filmography%7Caccess-restricted%3Afalse&all=true&sb=title_s&dates="+year+"&st=list&c="+count);
     }
     catch (Exception e) {
       System.err.println("URL Setup failed...");
       e.printStackTrace();
     }
     InputStream s=null;
     try { // Hook up to the file on the server
       s=TheFile.openStream();
     }
     catch (Exception e)  {
       e.printStackTrace();
       System.err.println("!! Stream open failed !!");
     }
     BufferedReader Inf=null;
     try {
       Inf=new BufferedReader(new InputStreamReader(s));
     }
     catch (Exception e){
       e.printStackTrace();
     }
     int next;
     next=Inf.read();
	 StringBuilder html=new StringBuilder();
	 
     while (next>=0) {
       //System.out.print((char)next);
	   html.append((char)next);
       next=Inf.read();
     } 
	 
	 HashMap<String, LibraryOfCongressItem> result = new HashMap<String, LibraryOfCongressItem>();
	 
	 
	//System.out.println("Title\tContributor");
	 
	 ArrayList<String> titles=giveMeTextBetween(count, html.toString(), "<span class='item-description-title'>", "</span>");
	 
	 
	 ArrayList<String> contributors=giveMeTextBetween(count, html.toString(), "<strong class='search-results-label'>Contributor:</strong>", "</span>");
	 
	 
	 ArrayList<String> dates=giveMeTextBetween(count, html.toString(), "<strong class='search-results-label'>Date:</strong>", "</span>");
	 for(int i = 0; i < dates.size(); ++i){
		 LibraryOfCongressItem information = new LibraryOfCongressItem();
		 dates.set(i, format(dates.get(i)));
		 contributors.set(i, format(contributors.get(i)));
		 titles.set(i, format(titles.get(i)));
		 information.date = dates.get(i);
		 information.contributor = contributors.get(i);
		 information.title = titles.get(i);
		 
		 result.put(information.title, information);
	 System.out.println(titles.get(i));
	 System.out.println(contributors.get(i));
	 System.out.println(dates.get(i));
	 }
	 return result;
	 
	 
 }
 
 
 
  /**
   * @param giveMeTextBetween	returns text in between html
   * @return result	returns the result
   */
 
 
static ArrayList<String> giveMeTextBetween(int count, String s, String before, String after) {
	ArrayList<String> result = new ArrayList<String>();
	
	int start = s.indexOf(before);

	start += before.length();
	int end = s.indexOf(after, start);
	
	for(int i = 0; i < count; ++i) {
		
		result.add(s.substring(start, end));
		start = s.indexOf(before, start);

		start += before.length();
		end = s.indexOf(after, start);
		//System.out.println(start);
		
	}
	
	return result;
}




}

















