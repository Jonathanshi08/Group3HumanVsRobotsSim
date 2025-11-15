import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {
    GreenfootImage background = new GreenfootImage("images/background.png");
    public static int topEdge = 275;
    public static double robotSpawnMultiplier = 1;
    public static double humanSpawnMultiplier = 1;

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
        robotSpawnMultiplier = 1;
        humanSpawnMultiplier = 1;

        // Set text positions (bottom corners)
        humanCashX = getWidth() - 175;
        humanCashY = 45;
        robotCashX = 30;
        robotCashY = 45;
    }

    public void act() {
        updateCash();
        spawnUnits();
    }

    private void updateCash() {
        GreenfootImage frame = getBackground();
        frame.drawImage(background, 0, 0); // redraw background
        humanCashImage = new GreenfootImage("Human Cash: " + Units.getHumanCash(), 24, Color.WHITE, new Color(0, 0, 0, 150));
        robotCashImage = new GreenfootImage("Robot Cash: " + Units.getRobotCash(), 24, Color.WHITE, new Color(0, 0, 0, 150));

        frame.drawImage(robotCashImage, robotCashX, robotCashY);
        frame.drawImage(humanCashImage, humanCashX, humanCashY);
        
        robotSpawnMultiplier += Units.getRobotCash() * 0.000001;
        humanSpawnMultiplier += Units.getHumanCash() * 0.000001;
    }

    private void spawnUnits() {
    
        if (Greenfoot.getRandomNumber(1000) < 3*robotSpawnMultiplier) {   
            RangedRobot rr = new RangedRobot(80, 1.2, 150, 20, 50, 100);
            addObject(rr, -50, Greenfoot.getRandomNumber(getHeight() - 100) + 100);
        }
    
        if (Greenfoot.getRandomNumber(1000) < 2*robotSpawnMultiplier) {   
            MeleeRobot mr = new MeleeRobot(150, 1.0, 75, 20, 50, 100);
            addObject(mr, -50, Greenfoot.getRandomNumber(getHeight()));
        }
    
        if (Greenfoot.getRandomNumber(1000) < 3*robotSpawnMultiplier) {   
            ExplodingRobot er = new ExplodingRobot(80, 1.2, 150, 50, 99999,100);
            addObject(er, -50, Greenfoot.getRandomNumber(getHeight()));
        }
    
        if (Greenfoot.getRandomNumber(1000) < 3*humanSpawnMultiplier) {   
            RangedHuman rh = new RangedHuman(100, 1.5, 200, 10, 100, 100);
            addObject(rh, getWidth() + 50, Greenfoot.getRandomNumber(getHeight()));
        }
    
        if (Greenfoot.getRandomNumber(1000) < 2*humanSpawnMultiplier) {   
            MeleeHuman mh = new MeleeHuman(150, 1.0, 75, 20, 10, 100);
            addObject(mh, getWidth() + 50, Greenfoot.getRandomNumber(getHeight()));
        }
    }    
}

