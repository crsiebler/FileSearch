//  Assignment 1, File Search
//  Name: Cory Siebler
//  StudentID: 1000832292
//  Lecture Topic: CSE 494 @ 7:30 MW
//  Description: Loader class for the File Search program. This creates the
//              Frame and the Panel to interface with the user. Makes sure that
//              the initialize setup of the GUI is thread safe.
package filesearch;

import javax.swing.JFrame;

/**
 * Loader class for the File Search program. This creates the Frame and the
 * Panel to interface with the user. Makes sure that the initialize setup of the
 * GUI is thread safe.
 *
 * @author Cory Siebler
 */
public class FileSearch {

    /**
     * Initializes the frame and the panel to search files.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CSE 494 - File Scanner");
            
            // Set Default Dimension, Close Operation, Bounds, and Resizable
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Initialize the Panel to perform file searching
            FileSearchPanel fileSearchPanel = new FileSearchPanel();
            
            // Add the panel and set the frame size & visibility
            frame.add(fileSearchPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
    
}
