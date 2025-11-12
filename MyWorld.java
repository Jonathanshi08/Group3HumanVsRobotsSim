import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MyWorld extends World
{
    GreenfootImage background = new GreenfootImage("images/background.png");
    public MyWorld()
    {    
        super(1240,700, 1); 
        setBackground(background);
        testPrepare();

    }
        private void testPrepare() {
        for (int i = 0; i < 5; i++) {
            RangedHuman rockThrower = new RangedHuman(100, 1.5, 200, 10, 100);
            addObject(rockThrower, Greenfoot.getRandomNumber(getWidth() / 2) + getWidth()/2, Greenfoot.getRandomNumber(getHeight()));
        }

        for (int i = 0; i < 5; i++) {
            ExplodingRobot er = new ExplodingRobot(80, 1.2, 150, 50);
            addObject(er, Greenfoot.getRandomNumber(getWidth() / 2), Greenfoot.getRandomNumber(getHeight()));
        }

        for (int i = 0; i < 3; i++) {
            MeleeHuman rockSmasher = new MeleeHuman(150, 1.0, 75, 20, 10);
            addObject(rockSmasher, Greenfoot.getRandomNumber(getWidth() / 2) + getWidth()/2, Greenfoot.getRandomNumber(getHeight()));
        }
        for (int i = 0; i < 3; i++) {
            MeleeRobot tinyRobot = new MeleeRobot(150, 1.0, 75, 20, 50);
            addObject(tinyRobot, Greenfoot.getRandomNumber(getWidth() / 2), Greenfoot.getRandomNumber(getHeight()));
        }
    
    
        Builders men = new Builders();
        addObject(men, getWidth()/2, getHeight()/4);
        
        Turret turret = new Turret();
        addObject(turret, 100 , getHeight()/2+100);
    }
}
