package base.chat.util;
import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JTextPaneWithIcon {
  public static void main(String args[]) throws BadLocationException {
    JFrame frame = new JFrame("TextPane Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    StyleContext context = new StyleContext();
    StyledDocument document = new DefaultStyledDocument(context);

    Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);

    Icon icon = new ImageIcon("test.png");
    JLabel label = new JLabel(icon);
    StyleConstants.setComponent(labelStyle, label);

    try {
      document.insertString(document.getLength(), "s", labelStyle);
    } catch (BadLocationException badLocationException) {
      System.err.println("Oops");
    }
    
    JTextPane textPane = new JTextPane(document);
    textPane.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textPane);
    frame.add(scrollPane, BorderLayout.CENTER);

    frame.setSize(300, 300);
    frame.setVisible(true);
    document.insertString(document.getLength(), "\nHi\n",new SimpleAttributeSet());
    context = new StyleContext();
    labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
    icon = new ImageIcon("test.png");
    label = new JLabel(icon);
    StyleConstants.setComponent(labelStyle, label);
    document.insertString(document.getLength(), "s", labelStyle);
    
    byte[] byteArr = "Str".getBytes();
    System.out.println("String to byte array: " + Arrays.toString(byteArr));
    
    

  }
}