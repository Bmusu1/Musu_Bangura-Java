package sl.edu.prog209;

import javax.swing.JFrame;
import java.awt.Dimension;

/** Shared frame configuration used by the assignment application. */
public class BaseWindow {
    protected static JFrame createBaseFrame(String title, int width, int height) {
        JFrame result = new JFrame(title);
        result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        result.setSize(new Dimension(width, height));
        result.setResizable(false);
        result.setLocationRelativeTo(null);
        return result;
    }
}
