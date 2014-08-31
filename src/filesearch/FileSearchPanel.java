//  Assignment 1, File Search
//  Name: Cory Siebler
//  StudentID: 1000832292
//  Lecture Topic: CSE 494 @ 7:30 MW
//  Description: (Description of each file/class)
package filesearch;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author csiebler
 */
public class FileSearchPanel extends JPanel {
    
    // Define the Result strings that are reused often
    private static final String LINE = "Line ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";
    private static final String NOT_FOUND = "Keyword specified was not found!";
    
    // Declare the GUI components
    private JLabel keywordLabel;
    private JLabel resultsLabel;
    private JTextField keywordTextField;
    private JFileChooser fileChooser;
    private JButton submitButton;
    private JTextArea resultsTextArea;
    private JScrollPane scrollPane;
    
    // Declare the line counter
    private int lineCount;
    
    /**
     * Constructor
     */
    public FileSearchPanel() {
        initComponents();
    }

    /**
     * Initialize the Components for the GUI.
     */
    private void initComponents() {
        // Initialize the Components
        keywordLabel = new JLabel("Keyword:");
        resultsLabel = new JLabel("Results:");
        keywordTextField = new JTextField();
        fileChooser = new JFileChooser();
        submitButton = new JButton("Select File");
        resultsTextArea = new JTextArea(20, 50);
        scrollPane = new JScrollPane(resultsTextArea);
        
        // Initialize the Panels & Separator for GUI styling
        JPanel keywordPanel = new JPanel();
        JPanel resultsPanel = new JPanel();
        
        // Do not allow the user to edit the text area
        resultsTextArea.setEditable(false);
        
        // Show a scroll bar for the text area when needed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Add a listener for the button
        submitButton.addActionListener(new FileListener());
        
        // Set the layout for the panels
        keywordPanel.setLayout(new BoxLayout(keywordPanel, BoxLayout.X_AXIS));
        resultsPanel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        
        // Add the components to the subpanels
        keywordPanel.add(keywordLabel);
        keywordPanel.add(keywordTextField);
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add the components to the main panel
        add(keywordPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }
    
    /**
     * 
     */
    private boolean searchLine(String line) {
        // Declare a way to track that the keyword is found
        boolean found = false;
        
        // Initialize the scanner from the line segment
        Scanner scanner = new Scanner(line);
        
        // Increment the line counter
        lineCount++;
        
        // Loop through the words within the line segment
        while (scanner.hasNext()) {
            // Grab the current word being processed
            String word = scanner.next().toLowerCase();
            
            // Check if the current word equals the keyword
            if (word.equals(keywordTextField.getText().toLowerCase())) {
                found = true;
                
                StringBuilder sb = new StringBuilder();
                
                sb.append(LINE);
                sb.append(String.valueOf(lineCount));
                sb.append(COLON);
                sb.append(line);
                sb.append(NEW_LINE);
                
                resultsTextArea.append(sb.toString());
            }
        }
        
        return found;
    }
    
    /**
     * Analyze the file for the matching 
     * 
     * @param file 
     */
    private void searchFile(File file) {
        try {
            // Declare a way to track that the keyword is found
            boolean found = false;

            // Initialize the scanner from the file
            Scanner scanner = new Scanner(file);
            
            // Reset the line counter
            lineCount = 0;
            
            // Loop through the entire text file line by line
            while (scanner.hasNextLine()) {
                // Search for the keyword in the current line
                boolean result = searchLine(scanner.nextLine());
                
                // Check if the keyword was found in the line
                if (result) {
                    found = true;
                }
            }
        
            // Check if the keyword was found at all
            if (!found) {
                // Keyword was not found so display a message to the user
                resultsTextArea.append(NOT_FOUND);
            }
        } catch (FileNotFoundException ex) {
            // Error occurred while searching so display the error to user
            resultsTextArea.setText(ex.getMessage());
        }
    }
    
    /**
     * Provides the functionality to load a text file and perform the keyword
     * search on that file. 
     */
    private class FileListener implements ActionListener {

        /**
         * 
         * @param e Event fired when the button is pressed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear the text area before displaying results
            resultsTextArea.setText("");
            
            // Make sure the user inputted a keyword
            if (!keywordTextField.getText().isEmpty()) {
                // Allow the user to only select text files
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(
                        new FileNameExtensionFilter("Text Files", "txt")
                );

                // Display File Open Menu
                int returnVal = fileChooser.showOpenDialog(FileSearchPanel.this);

                // Make sure a file was selected
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    // Grab the path for the user selected file
                    File file = fileChooser.getSelectedFile();

                    // Parse through the file and look for the keyword
                    searchFile(file);
                }
            } else {
                resultsTextArea.append("Please enter a keyword to search");
            }
        }
        
    } // End of FileListener class
    
} // End of FileSearchPanel class
