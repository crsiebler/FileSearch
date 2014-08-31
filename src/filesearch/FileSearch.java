//  Assignment 1, File Search
//  Name: Cory Siebler
//  StudentID: 1000832292
//  Lecture Topic: CSE 494 @ 7:30 MW
//  Description: (Description of each file/class)
package filesearch;

import javax.swing.JFrame;

/**
 *
 * @author Cory Siebler
 */
public class FileSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize JFrame & Dimensions
        JFrame frame = new JFrame("CSE 494 - File Scanner");

        // Set Default Dimension, Close Operation, Bounds, and Resizable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the Panel to perform file searching
        FileSearchPanel fileSearchPanel = new FileSearchPanel();
        
        // Add the panel and set the frame size & visibility
        frame.add(fileSearchPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
}
