package sl.edu.prog209;

import javax.swing.*;
import java.awt.*;

/** No-dependency smoke test. Run with a display or xvfb-run on Linux. */
public class GuiSmokeTest {
    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) throws Exception {
        final SwingAssignment[] app = new SwingAssignment[1];
        SwingUtilities.invokeAndWait(() -> app[0] = new SwingAssignment());
        SwingUtilities.invokeAndWait(() -> {
            SwingAssignment ui = app[0];
            JFrame frame = ui.frameForTest();
            require("Java Swing Assignment".equals(frame.getTitle()), "Incorrect frame title");
            require(frame.getWidth() == 800 && frame.getHeight() == 800, "Frame must be 800x800");
            require(frame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE, "Close operation mismatch");
            require(ui.panelForTest() != null, "JPanel missing");
            require(ui.labelForTest().getText().contains("Musu Bangura"), "Welcome label missing student name");
            require(ui.iconLabelForTest().getIcon() instanceof ImageIcon, "ImageIcon resource not loaded");
            require(ui.changeTextButtonForTest().getText().equals("Change Text"), "Change Text button missing");
            require(ui.changeBackgroundButtonForTest().getText().equals("Change Background"), "Change Background button missing");
            require(ui.changeTextButtonForTest().getActionListeners().length > 0, "Change Text listener missing");
            require(ui.changeBackgroundButtonForTest().getActionListeners().length > 0, "Background listener missing");
            String original = ui.labelForTest().getText();
            require(!ui.applyText("   "), "Blank input should be rejected");
            require(ui.labelForTest().getText().equals(original), "Blank input must not replace label");
            require(ui.applyText("Musu updated welcome"), "Valid text should apply");
            require(ui.labelForTest().getText().contains("Musu updated welcome"), "Label not updated");
            Color initial = ui.panelForTest().getBackground();
            ui.toggleBackground();
            Color alternate = ui.panelForTest().getBackground();
            require(!initial.equals(alternate), "Background did not change");
            ui.toggleBackground();
            require(ui.panelForTest().getBackground().equals(initial), "Second toggle should restore background");
            require(!"Theme Color".equals(ui.changeBackgroundButtonForTest().getText()), "Unexpected colour-chooser control");
            frame.dispose();
        });
        System.out.println("PASS: frame title, 800x800 size, close operation, JPanel, JLabel/name, ImageIcon, both buttons, event listeners, blank-text rejection, valid-text update, background toggle, background restoration, and fixed colour theme.");
    }
}
