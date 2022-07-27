package studio.dreamys.gui.sub;

import studio.dreamys.Hephaestus;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setBackground(Color.BLACK);
        setForeground(Color.MAGENTA);
        setSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
        setFocusable(false);
    }

    @Override
    public Dimension getPreferredSize() {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return new Dimension((int)(Hephaestus.font.getStringBounds(getText(), frc).getWidth()) + 40, (int)(Hephaestus.font.getStringBounds(getText(), frc).getHeight()) + 25);
    }
}
