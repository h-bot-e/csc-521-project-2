README.txt

Haris Bhatti
CSC 521


Link to my servlet: http://hbhat198.kutztown.edu:8080/LibraryofCongressServlet/index.html

Link to my javadoc: https://acad.kutztown.edu/~hbhat198/LibraryofCongressReader/classes/overview-tree.html 


My program expands upon Project 1, which pulled data from the Libary of Congress website and allowed users
to use buttons to pull data from different years on different films, allowing for the contributors to be pulled.

This servlet project adapts the same reasoning to an html website using Oracle databases and Tomcat.  The servlet
submits information to a database online and retrieves the data back to create a website when one chooses parameters and hits submit on my index.html page.
I then used a destroy method to drop the table afterwards, ensuring that no more than five tables would be online at a time.

The servlet pulls data from the Library of Congress official website which has many different filters that can be applied in the querystring.
Filters include year range, country of origin and the type of collection.  I incorporated the filters into the index.html file as a user friendly
dropdown menu that allows the user to choose the query for year, country and collection.  These selections would then be applied to LibraryofCongressServlet.java
when the user hits the submit button.  To reset to the default parameters hit the reset button.

A sample query would be a selection of the year 1977, country of the United States and part of the Jazz on the Screen Filmography.

On the official Library of Congress website it would look like this: https://www.loc.gov/film-and-videos/?fa=access-restricted%3Afalse%7Clocation%3Aunited+states%7Cpartof%3Ajazz+on+the+screen+filmography&all=true&sb=title_s&dates=+1977+&st=list&c=100
On my servlet it would look like this: http://hbhat198.kutztown.edu:8080/LibraryofCongressServlet/LibraryofCongressServlet?years=1977&country=united%2Bstates&partof=jazz%2Bon%2Bthe%2Bscreen%2Bfilmography

In the file LibraryofCongressServlet.java, most of the important work is being done.  I utilized a Hashmap to store different types of values in the
first phase and I opted to leave the basic structure intact while making adjustments to suit this project.  The Hashmap sets up the URL grab and I substitute
parts of the URL querystring where data such as the year would normally be and I had the int value put in instead so that users can modify the query from the front page.
After grabbing data from the Library of Congress website, I included a function called giveMeTextBetween to isolate the specific data I wanted, in this case, movie titles.
I then used replaceAll functions with a string to further clean up the html file and make an orderly list of the various film titles.  I used HTML formatting to make the 
website look the way I wanted while displaying the information that the user intended to seek out.


