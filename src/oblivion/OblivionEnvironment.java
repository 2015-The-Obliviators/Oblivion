/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import environment.Environment;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author marieleger
 */
class OblivionEnvironment extends Environment {

    private GameLevel gameLevel = GameLevel.START;

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/starstree.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

    }

    @Override
    public void timerTaskHandler() {
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameLevel = GameLevel.STORY;
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            gameLevel = GameLevel.START;
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        switch (gameLevel) {

            //<editor-fold defaultstate="collapsed" desc="START">
            case START:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/starstree.jpg").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));
                graphics.setColor(new Color(230, 230, 230, 25));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.BOLD, 80));
                graphics.drawString("OBLIVION", 450, 100);
                graphics.setColor(new Color(230, 230, 230, 75));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                graphics.drawString("Start", 450, 200);
                graphics.drawString("Instructions", 450, 300);
                graphics.drawString("Credits", 450, 400);
                graphics.drawString("Music", 450, 500);
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="STORY">
            case STORY:

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.rotate(Math.toRadians(0));
                this.setBackground(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

                graphics.setColor(new Color(230, 230, 230, 25));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.BOLD, 80));
                graphics.drawString("OBLIVION", 100, 100);
                graphics.setColor(new Color(230, 230, 230, 75));
                graphics.setFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
                graphics.drawString("Where are we really? This world is nothing.", 100, 200);
                graphics.drawString("It is oblivion", 100, 250);
                graphics.drawString("But even oblivion cannot always stay together", 100, 300);
                graphics.drawString("What if our world were to break?", 100, 350);
                graphics.drawString("And the complete nothing-ness would scatter", 100, 400);
                break;
//</editor-fold>
        }

    }
}
