import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

 /**
 LibraryOfCongressReader
 Extends frame
 Uses a HashMap to export to GUI
 GUI inspired by Temperature Converter by Dr. Spiegel
 **/

public class LibraryOfCongressReader extends Frame
implements WindowListener, ActionListener
{

	HashMap<String, LibraryOfCongressItem>information;
	Label details;
	Label date;
	Label instructions;
	JComboBox years;
	
	ArrayList<JButton> buttonlist;
	
	 /**
	 setSize changes the size of the GUI window
	 instructions sets the instructions text
	 details sets the names of contributors
	 years sets year listed
	 Each of these panels can be adjusted based on size
	 *@param windowname
	 *@param information
	 *
	 **/
	
	LibraryOfCongressReader(String windowname, HashMap<String, LibraryOfCongressItem>information)
  { super(windowname);
    setSize(1080,620);
	setLayout(null);
	
	this.information = information;
	instructions = new Label();
	details = new Label();
	date = new Label ();
	buttonlist = new ArrayList<JButton>();
	Integer[] yearArray = new Integer[10];
	for(int i = 1977;i<=1986;i++){
		yearArray[i-1977] = i;
	}
	years = new JComboBox (yearArray);
    // setLayout(new FlowLayout());   No LayoutManager. Place items manually
    addWindowListener(this);
	
	
	int i = 0;
    
	setUpTitleButtons();
	
	
	instructions.setBounds(10,115,1080,30);
    instructions.setFont(new Font("TimesRoman 12 point bold.", 20, 20));
	instructions.setText("Choose a year from the dropdown to get first five movies of that year.  Click movie title button to get Contributors.");
    add(instructions);
	
	
	details.setBounds(10,215,1080,30);
    details.setFont(new Font("TimesRoman 12 point bold.", 20, 20));
	details.setText("Contributors: ");
    add(details);
	
	date.setBounds(10,315,1080,30);
    date.setFont(new Font("TimesRoman 12 point bold.", 20, 20));
	date.setText("Year Published: ");
    add(date);
	
	years.setBounds(10,360,250,30);
    add(years);
	years.addActionListener(this);
	setVisible(true);
	
	
	
    
  }
  public void windowClosed(WindowEvent event) {}
  public void windowDeiconified(WindowEvent event) {}
  public void windowIconified(WindowEvent event) {}
  public void windowActivated(WindowEvent event) {}
  public void windowDeactivated(WindowEvent event) {}
  public void windowOpened(WindowEvent event) {}
  public void windowClosing(WindowEvent event) { System.exit(0); }
  
  
   /**
   *actionperformed puts information into try loop
   *This allows the program to refetch data from the website with new buttons every time
   *@param event	action event that class is reacting to
   */
  
  public void actionPerformed(ActionEvent event)
  { 
  if(event.getSource()==years){
  try{
	  information = Info.GiveMeWeb(5, (int)years.getSelectedItem());
	  int i = 0;
	  for(String title:information.keySet()){
	  buttonlist.get(i).setText(title);
	 i++;
	  }
  }catch(java.io.IOException exception){
	  System.out.println("error encountered while loading from internet");
  }
	  
  }
  else
  {
  details.setText("Contributors: " + information.get(event.getActionCommand()).contributor);
  date.setText("Year Published: " + information.get(event.getActionCommand()).date);
  }
  }
	void setUpTitleButtons(){
		buttonlist.clear();
		int i = 0;
		for(String title:information.keySet()){
		JButton titlebutton = new JButton (title);
		titlebutton.setBounds(i*200+35,50,200,60);
		titlebutton.addActionListener(this);
		add(titlebutton);
		buttonlist.add(titlebutton);
		//System.println(i*200)
		++i;
	}
	}
}