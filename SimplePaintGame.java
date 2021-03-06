
/*
 * Simple Paint Game
 * Kristina Yin
 * 
 * Objectives: 
 * Each time the user clicks on the main panel, a new circle is drawn. The color and size of the circle do not matter.
 * In the frame, but outside of the main panel, there should be an "undo" button, which removes the most recently drawn circle.
 */

// importing necessary packages
import java.util.*; // for the arraylists
import java.awt.event.*; // for the actionlistener
import java.awt.*; // for all the gui components

public class Paint
{
    // new object of the paintedpanel (inner class)
    PaintedPanel panel = new PaintedPanel(); 
    
    // variables that will hold the x and y location of the mouse clicks
    int x; 
    int y; 
    
    // arraylists that will save all the points (where the mouse is clicked) and all the colors (see variable randomColor in paintedpanel class)
    ArrayList<Point> pointlist = new ArrayList<Point>(); 
    ArrayList<Color> colorlist = new ArrayList<Color>(); 
    
    // variable that will determine the height and width of the oval/circle
    int circlelength = 20;
    
    // declaring and initializing a frame and a button that will used to erase the last drawn circle
    JFrame frame = new JFrame(); 
    JButton erase = new JButton("UNDO");
    
    // constructor that will set up everything
    public Paint() 
    {
        frame.add(BorderLayout.CENTER, panel); // add the panel to the center of the frame
        frame.add(BorderLayout.SOUTH, erase); // add the erase button to the bottom of the frame
        panel.addMouseListener(new mouseListener()); // make the panel responsive toward mouse clicks
        erase.addActionListener(new timerListener()); // make the button responsive to clicks
        frame.setSize(300,300); // setting up the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so the frame closes when the x button is pressed
        frame.setVisible(true);
        //timer.start(); 
    }
    
    // inner class that uses the MouseListener interface
    public class mouseListener implements MouseListener {
        // when mouse is clicked, get the position of the mouse, save that point to pointlist arraylist, and repaint the frame 
        public void mouseClicked(MouseEvent e) {
            pointlist.add(panel.getMousePosition()); 
            panel.repaint();
        }
        public void mouseExited(MouseEvent e) {
            // nothing to do 
        }
        public void mouseEntered(MouseEvent e) {
            // nothing to do 
        }
        public void mouseReleased(MouseEvent e) {
            // nothing to do 
        }
        public void mousePressed(MouseEvent e) {
            // nothing to do 
        }
    }
    
    // inner class that uses the actionlistener interface
    public class timerListener implements ActionListener {
        // when button is clicked, remove the last element of the pointlist and colorlist arraylists
        public void actionPerformed(ActionEvent e) {
            if (pointlist.size() != 0) {
                pointlist.remove(pointlist.size()-1);
                colorlist.remove(colorlist.size()-1);
                panel.repaint(); 
            }
        }
    }
    
    // inner class that is the subclass of JPanel
    public class PaintedPanel extends JPanel {
        // overrides the paintComponenent method
        public void paintComponent(Graphics g) {
            
            // use random values of red, blue, and green to create a random color
            int red = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            Color randomColor = new Color(red, green, blue);
            
            // add randomcolor to the colorlist arraylist
            colorlist.add(randomColor);
            
            // for each element in pointlist, draw a circle according the element's saved location
            for (int i = 0; i < pointlist.size(); i++) {
                g.setColor(colorlist.get(i));
                if (pointlist.get(i) != null) {
                    x = pointlist.get(i).x; 
                    y = pointlist.get(i).y; 
                }   
                g.fillOval(x-10, y-10, circlelength, circlelength); 
            }
        }
    }
   
    // main method to run everything
    public static void main(String[] args) {
        Paint gui = new Paint(); 
    }
}
