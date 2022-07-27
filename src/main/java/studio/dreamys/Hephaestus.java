package studio.dreamys;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import studio.dreamys.gui.Panel;
import studio.dreamys.gui.Window;
import studio.dreamys.gui.sub.Button;
import studio.dreamys.gui.sub.EditorPane;

import java.awt.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

public class Hephaestus {
    public static Font font;

    public static void main(String[] args) {
        System.out.println("Hephaestus");

        //theming
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Hephaestus.class.getResourceAsStream("/Rubik-Regular.ttf"))));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        font = new Font("Rubik-Regular", Font.PLAIN, 14);

        UIManager.put("Button.select", new ColorUIResource(Color.MAGENTA));
        setUIFont(new FontUIResource(font));

        Window window = new Window("Hephaestus - R.A.T Forger");

        Button button = new Button("Clone");

        EditorPane editorPane = new EditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText("<h1>Java</h1><p>is a general-purpose computer programming language that is       concurrent, class-based, object-oriented, and specifically designed to have as few          implementation dependencies as possible.</p>");

        button.addActionListener(e -> {
            button.setText("Cloning...");

//            try {
//                Git.cloneRepository().setURI("https://github.com/DxxxxY/R.A.T.git").setDirectory(new File(System.getenv("APPDATA") + "\\Hephaestus")).call();
//            } catch (GitAPIException ex) {
//                JOptionPane.showMessageDialog(null, "[Hephaestus] Error while cloning R.A.T repository.");
//                throw new RuntimeException(ex);
//            }

            button.setText("Successfully cloned!");
        });

        window.add(button);
        window.add(editorPane);
    }

    public static void setUIFont(FontUIResource f){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
