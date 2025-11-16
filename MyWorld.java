import greenfoot.*;
import greenfoot.Color;  
import java.util.List;

public class MyWorld extends World {

    GreenfootImage background = new GreenfootImage("images/background.png");
    public static int topEdge = 275;

    private GreenfootImage humanCashImage;
    private GreenfootImage robotCashImage;

    private int humanCashX = 1065;
    private int humanCashY = 45;
    private int robotCashX = 30;
    private int robotCashY = 45;

    private int frameCount = 0;
    private int spawnInterval = 200;
    private int humanSpawnTimer = 0;
    private int robotSpawnTimer = 0;

    public MyWorld() {    
        super(1240, 700, 1);

        setPaintOrder(
            SuperStatBar.class, 
            Builders.class,
            Fences.class,
            Robot.class
        );
        
        setBackground(background);

        Units.setHumanCash(0);
        Units.setRobotCash(0);
    }

    public void act() {
        updateCash(); 

        humanSpawnTimer++;
        if (humanSpawnTimer >= spawnInterval) {
            humanSpawnTimer = 0;
            spawnHumans();
        }

        robotSpawnTimer++;
        if (robotSpawnTimer >= spawnInterval) {
            robotSpawnTimer = 0;
            spawnRobots();
        }
    }

    private void updateCash() {
        GreenfootImage frame = getBackground();
        frame.drawImage(background, 0, 0); 

        humanCashImage = new GreenfootImage(
            "Human Cash: " + Units.getHumanCash(), 
            24, Color.WHITE, new Color(0, 0, 0, 150)
        );

        robotCashImage = new GreenfootImage(
            "Robot Cash: " + Units.getRobotCash(),
            24, Color.WHITE, new Color(0, 0, 0, 150)
        );

        frame.drawImage(robotCashImage, robotCashX, robotCashY);
        frame.drawImage(humanCashImage, humanCashX, humanCashY);
    }

    private void spawnHumans() {
        int minY = 175;
        int maxY = getHeight() - 1;
        int y = minY + Greenfoot.getRandomNumber(maxY - minY + 1);

        int choice = Greenfoot.getRandomNumber(2);
        if (choice == 0) {
            MeleeHuman human = new MeleeHuman(100, 2, 40, 20, 40, 10);
            addObject(human, getWidth() - 50, y);
        } else {
            RangedHuman human = new RangedHuman(80, 2, 400, 15, 40, 10);
            addObject(human, getWidth() - 50, y);
        }
    }

    private void spawnRobots() {
        int minY = 175;
        int maxY = getHeight() - 1;
        int y = minY + Greenfoot.getRandomNumber(maxY - minY + 1);

        int choice = Greenfoot.getRandomNumber(2);
        if (choice == 0) {
            MeleeRobot robot = new MeleeRobot(150, 2, 40, 10, 40, 10);
            addObject(robot, 50, y);
        } else {
            ExplodingRobot robot = new ExplodingRobot(60, 2, 40, 15, 40, 10);
            addObject(robot, 50, y);
        }
    }
}

