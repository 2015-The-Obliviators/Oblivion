/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblivion;

import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author brookeireland
 */
public class Level implements AccelerationProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors and Factory Methods">
    public static Level getLevel(int levelNumber) {
        Level level = new Level();

        //default gravity acceleration
        level.setAcceleration(new Vector2D(0, 2));

        level.setBackgroundImage(ResourceTools.loadImageFromResource("resources/stars.png").getScaledInstance(1000, 700, Image.SCALE_SMOOTH));

        //default text settings
        level.setTextFont(new Font("FOOTLIGHTMT LIGHT", Font.ITALIC, 30));
        level.setTextColor(new Color(230, 230, 230, 75));
        level.setTextX(100);
        level.setTextY(75);

        switch (levelNumber) {

            //<editor-fold defaultstate="collapsed" desc="1">
            case 1:
                level.getBlocks().add(new Block(0, 500, 1000, 100, true)); //floor of level
                level.getBlocks().add(new Block(0, 0, 1000, 100, true)); //ceiling of level

                level.setText("Use the right and left arrows to move");
                level.setAcceleration(new Vector2D(0, 1));

                level.setLetterHeight(150);
                level.setLetterWidth(125);
                level.setLetterX(700);
                level.setLetterY(375);
                level.setLetterImage(ResourceTools.loadImageFromResource("resources/Oblivion.png"));
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="2">
            case 2:
                level.getBlocks().add(new Block(0, 500, 1000, 100, true)); //floor of level
                level.getBlocks().add(new Block(0, 0, 1000, 100, true)); //ceiling of level
                level.getBlocks().add(new Block(350, 100, 400, 325, true)); //Duck under block

                level.setText("Use the up and down arrows to grow and shrink");

                level.setAcceleration(new Vector2D(0, 2));

                level.setLetterHeight(150);
                level.setLetterWidth(125);
                level.setLetterX(775);
                level.setLetterY(380);
                level.setLetterImage(ResourceTools.loadImageFromResource("resources/B.png"));
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="3">
            case 3:
                level.getBlocks().add(new Block(0, 0, 1000, 100, true));   //ceiling of level
                level.getBlocks().add(new Block(0, 500, 1000, 100, true)); //floor of level

                level.getBlocks().add(new Block(300, 225, 50, 50, true)); //chin up cube
                level.getBlocks().add(new Block(475, 400, 300, 100, true)); //landing cube

                level.setText("Can you chin up?");
                level.setAcceleration(new Vector2D(0, 2));

                level.setLetterHeight(150);
                level.setLetterWidth(125);
                level.setLetterX(600);
                level.setLetterY(380);
                level.setLetterImage(ResourceTools.loadImageFromResource("resources/L.png"));
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="4">
            case 4:
                level.setAcceleration(new Vector2D(0, 2));
                level.getBlocks().add(new Block(0, 0, 1000, 100, true));   //ceiling of level
                level.getBlocks().add(new Block(0, 500, 1000, 100, true)); //floor of level

                level.getBlocks().add(new Block(200, 170, 50, 50, true)); //chin up cube
                level.getBlocks().add(new Block(350, 420, 30, 80, true)); //button left
                level.getBlocks().add(new Block(550, 420, 30, 80, true)); //button right
                level.getBlocks().add(new Block(600, 220, 300, 280, true)); //landing cube

                level.setTextX(150);
                level.setText("I wonder what that button does...");
                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="5">
            case 5:
                level.setAcceleration(new Vector2D(0, 2));
//                level.setBarriers(new ArrayList<>());

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="6">
            case 6:
                level.setAcceleration(new Vector2D(0, 2));
//                level.setBarriers(new ArrayList<>());

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="7">
            case 7:
                level.setAcceleration(new Vector2D(0, 2));
//                level.setBarriers(new ArrayList<>());

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="8">
            case 8:
                level.setAcceleration(new Vector2D(0, 2));
//                level.setBarriers(new ArrayList<>());

                break;
//</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="9">
            case 9:
                level.setAcceleration(new Vector2D(0, 2));
//                level.setBarriers(new ArrayList<>());
//</editor-fold>
        }

        level.getBlocks().stream().forEach((block) -> {
            block.setDrawBarriers(true);
            block.setBorderColor(Color.BLACK);
            block.setFillColor(new Color(20, 20, 20, 200));
        });

        return level;
    }

    {
        blocks = new ArrayList<>();
        text = "";
        acceleration = new Vector2D(0, 2);
        setLetterI(new BlockLetterI(100, 120, 100, 150, false, this));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int levelNumber;
    private ArrayList<Block> blocks;
    private Vector2D acceleration;
    private String text = "";
    private Color TextColor;
    private Font TextFont;
    private int TextX;
    private int TextY;
    private BlockLetterI letterI;
    private Image backgroundImage;
    private Image letterImage;
    private int letterX;
    private int letterY;
    private int letterHeight;
    private int letterWidth;

    /**
     * @return the backgroundImage
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * @return the levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * @param levelNumber the levelNumber to set
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * @return the barriers
     */
    public ArrayList<Barrier> getBarriers() {
        ArrayList<Barrier> barriers = new ArrayList<>();

        getBlocks().stream().forEach((block) -> {
            barriers.addAll(block.barriers);
        });

        return barriers;
    }

    /**
     * @return the acceleration
     */
    @Override
    public Vector2D getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
        this.letterI.setAccelerationProvider(this);
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the letterI
     */
    public BlockLetterI getLetterI() {
        return letterI;
    }

    /**
     * @param letterI the letterI to set
     */
    public void setLetterI(BlockLetterI letterI) {
        this.letterI = letterI;
    }

    /**
     * @return the TextColor
     */
    public Color getTextColor() {
        return TextColor;
    }

    /**
     * @param TextColor the TextColor to set
     */
    public void setTextColor(Color TextColor) {
        this.TextColor = TextColor;
    }

    /**
     * @return the TextFont
     */
    public Font getTextFont() {
        return TextFont;
    }

    /**
     * @param TextFont the TextFont to set
     */
    public void setTextFont(Font TextFont) {
        this.TextFont = TextFont;
    }

    /**
     * @return the TextX
     */
    public int getTextX() {
        return TextX;
    }

    /**
     * @param TextX the TextX to set
     */
    public void setTextX(int TextX) {
        this.TextX = TextX;
    }

    /**
     * @return the TextY
     */
    public int getTextY() {
        return TextY;
    }

    /**
     * @param TextY the TextY to set
     */
    public void setTextY(int TextY) {
        this.TextY = TextY;
    }

    /**
     * @return the blocks
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * @return the letterImage
     */
    public Image getLetterImage() {
        return letterImage;
    }

    /**
     * @param letterImage the letterImage to set
     */
    public void setLetterImage(Image letterImage) {
        this.letterImage = letterImage;
    }

    /**
     * @return the letterX
     */
    public int getLetterX() {
        return letterX;
    }

    /**
     * @param letterX the letterX to set
     */
    public void setLetterX(int letterX) {
        this.letterX = letterX;
    }

    /**
     * @return the letterY
     */
    public int getLetterY() {
        return letterY;
    }

    /**
     * @param letterY the letterY to set
     */
    public void setLetterY(int letterY) {
        this.letterY = letterY;
    }

    /**
     * @return the letterHeight
     */
    public int getLetterHeight() {
        return letterHeight;
    }

    /**
     * @param letterHeight the letterHeight to set
     */
    public void setLetterHeight(int letterHeight) {
        this.letterHeight = letterHeight;
    }

    /**
     * @return the letterWidth
     */
    public int getLetterWidth() {
        return letterWidth;
    }

    /**
     * @param letterWidth the letterWidth to set
     */
    public void setLetterWidth(int letterWidth) {
        this.letterWidth = letterWidth;
    }
}//</editor-fold>
