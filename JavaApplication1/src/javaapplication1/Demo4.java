
/*testeee
*mudança no git
aaa
dasdadasd
 * (C) 2004 - Geotechnical Software Services
 * 
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, 
 * MA  02111-1307, USA.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import no.geosoft.cc.graphics.*;



/**
 * G demo program. Demonstrates:
 *
 * <ul>
 * <li>Text position hints
 * <li>Annotation algorithm
 * </ul>
 * 
 * @author <a href="mailto:info@geosoft.no">GeoSoft</a>
 */   
public class Demo4 extends JFrame
{
  /**
   * Class for creating the demo canvas and hande Swing events.
   */   
  public Demo4()
  {
    super ("G Graphics Library - Demo 4");
    //setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    // Create the GUI
    JPanel topLevel = new JPanel();
    topLevel.setLayout (new BorderLayout());
    getContentPane().add (topLevel);        

    JPanel buttonPanel = new JPanel();
    buttonPanel.add (new JLabel ("Resize window to redo geometry"));
    topLevel.add (buttonPanel,   BorderLayout.NORTH);

    // Create the graphic canvas
    GWindow window = new GWindow (new Color (255, 255, 255));
    topLevel.add (window.getCanvas(), BorderLayout.CENTER);    

    // Create scene with default viewport and world extent settings
    GScene scene = new GScene (window, "Scene");

    // Create som graphic objects
    GObject testObject = new TestObject();
    scene.add (testObject);

    pack();
    setSize (new Dimension (500, 500));
    setVisible (true);
  }


  
  /**
   * Defines the geometry and presentation for a sample
   * graphic object.
   */   
  private class TestObject extends GObject
  {
    private GSegment  line_;

    
    
    TestObject()
    {
      line_ = new GSegment();
      GStyle lineStyle = new GStyle();
      lineStyle.setForegroundColor (new Color (0, 0, 255));
      line_.setStyle (lineStyle);
      addSegment (line_);

      GStyle textStyle = new GStyle();
      textStyle.setForegroundColor (new Color (200, 50, 50));
      textStyle.setFont (new Font ("Dialog", Font.BOLD, 18));

      GText text;
      text = new GText ("Top", GPosition.TOP | GPosition.NORTH);
      text.setStyle (textStyle);
      line_.addText (text);

      text = new GText ("Bottom", GPosition.BOTTOM | GPosition.SOUTH);
      text.setStyle (textStyle);
      line_.addText (text);
      
      text = new GText ("Left", GPosition.LEFT | GPosition.WEST);
      text.setStyle (textStyle);
      line_.addText (text);

      text = new GText ("Right", GPosition.RIGHT | GPosition.EAST);
      text.setStyle (textStyle);
      line_.addText (text);

      text = new GText ("First", GPosition.FIRST | GPosition.CENTER);
      text.setStyle (textStyle);
      line_.addText (text);

      text = new GText ("Last", GPosition.LAST | GPosition.CENTER);
      text.setStyle (textStyle);
      line_.addText (text);

      GStyle symbolStyle = new GStyle();
      symbolStyle.setForegroundColor (new Color (0, 0, 0));
      GImage square = new GImage (GImage.SYMBOL_SQUARE1);
      square.setStyle (symbolStyle);
      
      line_.setVertexImage (square);
    }
    

    
    public void draw()
    {
      // Center of viewport
      int x0     = (int) Math.round (getScene().getViewport().getCenterX());
      int y0     = (int) Math.round (getScene().getViewport().getCenterY());

      int width  = (int) Math.round (getScene().getViewport().getWidth());
      int height = (int) Math.round (getScene().getViewport().getHeight());

      int nPoints = 15;
      
      int[] x = new int[nPoints];
      int[] y = new int[nPoints];
      
      for (int i = 0; i < nPoints; i++) {
        x[i] = 50 + (int) Math.round ((width  - 100) * Math.random());
        y[i] = 20 + (int) Math.round ((height -  40) * Math.random());
      }

      line_.setGeometry (x, y);
    }
  }
  


  public static void main (String[] args)
  {
    new Demo4();
  }
}
