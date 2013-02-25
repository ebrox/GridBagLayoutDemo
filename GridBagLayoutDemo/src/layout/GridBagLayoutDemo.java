/*
 * 
 */
package layout;

import java.awt.*;
import javax.swing.*;

public class GridBagLayoutDemo {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    static boolean isWinner = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if (shouldFill) {
	//natural height, maximum width
	c.fill = GridBagConstraints.HORIZONTAL;
	}
        
        //********PERIODIC TABLE BUTTON AND HELP BUTTON***************
        //new panel jp1 for start box, race, box, start gate
        JPanel jp1 = new JPanel();
        JButton button1 = new JButton("Periodic Table");
        JButton button2 = new JButton("Help");
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 0;
        jp1.add(button1);
        jp1.add(button2);
	pane.add(jp1, c);
        
                
        //*********DRAWING START BOX, RACE BOX, GATE AND FINISH*********
        JPanel jp7 = new JPanel(new GridBagLayout());
        jp7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Simulation Space"));
        //
        GasChamber bt = new GasChamber();
        
        bt.init();
        jp7.add(bt);
        
        bt.particleFill(1, 6);                 // AEB for the Combo Box 
                                              // listeners and uses the
                                             // variables set by the Combo Boxes

        Element.setTopSpeed(Element.getTopSpeed() * (1.21f)); // AEB for Slider listener temp range is 0.00f (at -100) to 1.00f (at 0) to 2.00f (at 100)
        
        bt.setGateOpen(false);  // AEB for Go Button listener
        
        bt.keyPressed();  // AEB for Pause and Play Button Listeners (since it is only one method the Pause and Play buttons can probably be combined
                          // because the method is being called the app starts paused, press any key and it will go again
        
        //jp7.add(knownComboBoxList,BorderLayout.NORTH);
        c.insets = new Insets(10,10,10,10);
        c.ipadx = 500;                                              // AEB added
        c.ipady = 200;                                              // AEB added
	c.weightx = 0.5;
        c.gridwidth = 5;                                            // AEB was 1
        c.gridheight = 2;                         // AEB was 5 and commented out
        c.fill = GridBagConstraints.BOTH;
	c.gridx = 0;
	c.gridy = 1;
	pane.add(jp7, c);
        
        //*******KNOWN COMBO BOX***********
        //new jpanel for the known molecules combobox
        JPanel jp2 = new JPanel(new BorderLayout());
        
        //variables
        JComboBox knownComboBox;
        //create known combo box list label
        JLabel knownComboBoxList = new JLabel("Select Known Molecules: ");
        //create array of list data for JList
        String [] knownComboBoxListData = {"Argon (Ar)", "Helium (He)", "Neon (Ne)"};
        knownComboBox = new JComboBox(knownComboBoxListData);
        //create a courses list
        JList knownList  = new JList(knownComboBoxListData);
        //set visible amount of rows in JList
        knownList.setVisibleRowCount(1);
        jp2.add(knownComboBoxList,BorderLayout.NORTH);
        jp2.add(knownComboBox,BorderLayout.SOUTH);
        c.ipadx = 25;                                               // AEB added
        c.ipady = 25;                                               // AEB added
        c.gridwidth = 1;                                            // AEB added
        c.gridheight = 1;                                           // AEB added
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 3;
	pane.add(jp2, c);
        
        //***********UNKNOWN/KNOWN COMBO BOX***************
        //new jpanel for the unknown molecules combobox
        JPanel jp3 = new JPanel(new BorderLayout());
        //variables
        JComboBox unknownComboBox;
        //create known combo box list label
        JLabel unknownComboBoxList = new JLabel("Select Unknown Molecules: ");
        //create array of list data for JList
        String [] unknownComboBoxListData = {"Argon (Ar)", "Helium (He)", 
            "Neon (Ne)","Unknown 1","Unknown 2","Unknown 3"};
        unknownComboBox = new JComboBox(unknownComboBoxListData);
        //create a courses list
        JList unknownList  = new JList(unknownComboBoxListData);
        //set visible amount of rows in JList
        unknownList.setVisibleRowCount(1);
        jp3.add(unknownComboBoxList,BorderLayout.NORTH);
        jp3.add(unknownComboBox,BorderLayout.SOUTH);
        c.gridwidth = 1;                                            // AEB added
        c.gridheight = 1;                                           // AEB added
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	pane.add(jp3, c);
        
        //*********TEMPERATURE/REACTION RATE +- SLIDER**************
        //JSlider js = new JSlider();
        JPanel jp4 = new JPanel(new BorderLayout());
        JSlider slider2 = new JSlider();
        slider2.setBorder(BorderFactory.createTitledBorder("<--Decrease "
                + "Temperature | Increase Temperature-->"));
        slider2.setSize(100,20);
        slider2.setMajorTickSpacing(50);
        slider2.setMinorTickSpacing(10);
        slider2.setPaintTicks(true);
        jp4.add(slider2, BorderLayout.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
	c.weightx = 0.5;
        //c.gridwidth = 3;
	c.gridx = 2;
	c.gridy = 3;
	pane.add(jp4, c);
        
        //******************GO BUTTON********************
	JButton button3 = new JButton("GO");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;                                            // AEB was 1
	c.gridx = 3;
	c.gridy = 3;
	pane.add(button3, c);
        
        //********************PLAY AND PAUSE BUTTONS******************
        //new panel jp5 for play and pause buttons
        JPanel jp5 = new JPanel();
        JButton button4 = new JButton("Play");
        JButton button5 = new JButton("Pause");
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,5,5);
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 3;
        jp5.add(button4);
        jp5.add(button5);
	pane.add(jp5, c);
        
        //******************FORM**********************
        JPanel jp6 = new JPanel(new BorderLayout());
        String [] colHeading = {"Known", "Unknown", "Rate (m/s)", "Molecular Weight (MW)"};
        String [][] data = {{"Argon", "Unknown 2", "50", "16"},
            {"Neon", "Unknown 1", "30", "40"}};
        JTable table = new JTable(data,colHeading);
        JLabel enterLabel = new JLabel("Use the data in the table to "
                + "compute the Rate and Molecular Weight...");
	c.fill = GridBagConstraints.BOTH;
        //c.insets = new Insets(10,10,10,10);
	//c.weightx = 0.5;
        c.ipady = 20;
	c.gridx = 0;
	c.gridy = 5;
        c.gridwidth = 5;
        c.gridheight = 1;                         // AEB was 3 and commented out
        jp6.add(enterLabel, BorderLayout.NORTH);
        jp6.add(table, BorderLayout.SOUTH);
	pane.add(jp6, c);
        
        //*******************CHECK ANSWER AND RESET BUTTONS****************
        JPanel jp8 = new JPanel(new BorderLayout());
        JButton button6 = new JButton("Reset");
        JButton button7 = new JButton("CheckBox");
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 6;
        jp8.add(button6, BorderLayout.WEST);
        jp8.add(button7, BorderLayout.EAST);
	pane.add(jp8, c);
        
        
////	button = new JButton("Long-Named Button 4");
////	c.fill = GridBagConstraints.HORIZONTAL;
////	c.ipady = 40;      //make this component tall
////	c.weightx = 0.0;
////	c.gridwidth = 3;
////	c.gridx = 0;
////	c.gridy = 1;
////	pane.add(button, c);
//
//	button = new JButton("5");
//	c.fill = GridBagConstraints.HORIZONTAL;
//	c.ipady = 0;       //reset to default
//	c.weighty = 1.0;   //request any extra vertical space
//	c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//	c.insets = new Insets(10,0,0,0);  //top padding
//	c.gridx = 1;       //aligned with button 2
//	c.gridwidth = 2;   //2 columns wide
//	c.gridy = 2;       //third row
//	pane.add(button, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
//                BouncyBubbles bt = new BouncyBubbles();
//                bt.setup();
//                bt.init();
            }
        });
    }
    
//    public void showWinner(){
//        isWinner = true;
//            
//    }
}