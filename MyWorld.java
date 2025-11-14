import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {
    GreenfootImage background = new GreenfootImage("images/background.png");
    public static int topEdge = 275;

    // Text display images
    private GreenfootImage humanCashImage;
    private GreenfootImage robotCashImage;

    // Positions for text
    private int humanCashX, humanCashY;
    private int robotCashX, robotCashY;

    public MyWorld() {    
        super(1240, 700, 1); 

        setBackground(background);

        Units.setHumanCash(0);
        Units.setRobotCash(0);

        //testPrepare();

        // Set text positions (bottom corners)
        humanCashX = getWidth() - 250;
        humanCashY = getHeight() - 40;
        robotCashX = 30;
        robotCashY = getHeight() - 40;
    }

    public void act() {
        updateCashDisplay();
    }

    private void updateCashDisplay() {
        GreenfootImage frame = getBackground();
        frame.drawImage(background, 0, 0); // redraw background

        humanCashImage = new GreenfootImage("Human Cash: " + Units.getHumanCash(), 24, Color.WHITE, new Color(0, 0, 0, 150));
        robotCashImage = new GreenfootImage("Robot Cash: " + Units.getRobotCash(), 24, Color.WHITE, new Color(0, 0, 0, 150));

        frame.drawImage(robotCashImage, robotCashX, robotCashY);
        frame.drawImage(humanCashImage, humanCashX, humanCashY);
    }
}

