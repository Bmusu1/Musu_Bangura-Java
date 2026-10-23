package sl.edu.prog209;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;


public class SwingAssignment extends BaseWindow {
    private static final Color YELLOW = new Color(255, 220, 0);
    private static final Color DARK_BACKGROUND = new Color(16, 18, 20);
    private static final Color LIGHT_BACKGROUND = new Color(255, 250, 220);
    private static final Color DARK_TEXT = new Color(246, 244, 225);
    private static final Color LIGHT_TEXT = new Color(30, 30, 28);

    // Required components: frame, panel and label are declared in this class.
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JLabel subtitleLabel;
    private JLabel statusLabel;
    private JLabel iconLabel;
    private JButton changeTextButton;
    private JButton changeBackgroundButton;
    private boolean lightBackground = false;

    @SuppressWarnings("this-escape")
    public SwingAssignment() {
        frame = createBaseFrame("Java Swing Assignment", 800, 800);
        buildInterface();
        frame.setVisible(true);
    }

    @SuppressWarnings("this-escape")
    private void buildInterface() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(32, 42, 26, 42));
        panel.setBackground(DARK_BACKGROUND);

        iconLabel = new JLabel();
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        URL iconUrl = getClass().getResource("/assets/application-icon.png");
        if (iconUrl != null) {
            ImageIcon icon = new ImageIcon(iconUrl);
            Image scaled = icon.getImage().getScaledInstance(154, 154, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(scaled));
            frame.setIconImage(icon.getImage());
        } else {
            iconLabel.setText("MB");
            iconLabel.setFont(new Font("Serif", Font.BOLD, 64));
            iconLabel.setForeground(YELLOW);
        }

        JLabel eyebrow = new JLabel("PROG209  /  EVENT-DRIVEN PROGRAMMING");
        eyebrow.setAlignmentX(Component.CENTER_ALIGNMENT);
        eyebrow.setForeground(YELLOW);
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 12));

        label = new JLabel("Welcome to Java Swing First Individual Assignment by Musu Bangura");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 21));
        label.setForeground(DARK_TEXT);

        subtitleLabel = new JLabel("A simple desktop interface with two interactive actions");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(196, 197, 192));

        changeTextButton = createButton("Change Text");
        changeBackgroundButton = createButton("Change Background");
        changeTextButton.addActionListener(event -> requestNewText());
        changeBackgroundButton.addActionListener(event -> toggleBackground());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        controls.setOpaque(false);
        controls.add(changeTextButton);
        controls.add(changeBackgroundButton);

        statusLabel = new JLabel("Status: Ready");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setForeground(YELLOW);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(Box.createVerticalGlue());
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(22));
        panel.add(eyebrow);
        panel.add(Box.createVerticalStrut(19));
        panel.add(label);
        panel.add(Box.createVerticalStrut(12));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(26));
        panel.add(controls);
        panel.add(Box.createVerticalGlue());
        panel.add(statusLabel);

        frame.setContentPane(panel);
        updateForegrounds();
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setForeground(new Color(24, 24, 20));
        button.setBackground(YELLOW);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 245, 150), 1),
            new EmptyBorder(10, 18, 10, 18)
        ));
        button.setOpaque(true);
        return button;
    }

    private void requestNewText() {
        String input = JOptionPane.showInputDialog(
            frame,
            "Enter the new welcome message:",
            "Change Text",
            JOptionPane.PLAIN_MESSAGE
        );
        if (input == null) {
            statusLabel.setText("Status: Text change cancelled");
            return;
        }
        applyText(input);
    }

    /** Applies non-empty user text and returns whether the label was changed. */
    boolean applyText(String input) {
        if (input == null || input.trim().isEmpty()) {
            statusLabel.setText("Status: Enter some text before applying the change");
            return false;
        }
        label.setText("<html><div style='text-align:center;width:500px;'>" + escapeHtml(input.trim()) + "</div></html>");
        statusLabel.setText("Status: Welcome text updated");
        return true;
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    /** Switches between two fixed, readable background states; no colour chooser is provided. */
    void toggleBackground() {
        lightBackground = !lightBackground;
        panel.setBackground(lightBackground ? LIGHT_BACKGROUND : DARK_BACKGROUND);
        updateForegrounds();
        panel.revalidate();
        panel.repaint();
        statusLabel.setText(lightBackground
            ? "Status: Light background enabled"
            : "Status: Dark background enabled");
    }

    private void updateForegrounds() {
        Color mainText = lightBackground ? LIGHT_TEXT : DARK_TEXT;
        Color supportingText = lightBackground ? new Color(74, 70, 54) : new Color(196, 197, 192);
        label.setForeground(mainText);
        subtitleLabel.setForeground(supportingText);
        statusLabel.setForeground(lightBackground ? new Color(122, 94, 0) : YELLOW);
    }

    // Package-private accessors are used by the no-dependency smoke test.
    JFrame frameForTest() { return frame; }
    JPanel panelForTest() { return panel; }
    JLabel labelForTest() { return label; }
    JLabel iconLabelForTest() { return iconLabel; }
    JButton changeTextButtonForTest() { return changeTextButton; }
    JButton changeBackgroundButtonForTest() { return changeBackgroundButton; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAssignment::new);
    }
}
