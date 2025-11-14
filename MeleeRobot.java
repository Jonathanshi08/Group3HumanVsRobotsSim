import greenfoot.*;
import java.util.ArrayList;

public class MeleeRobot extends Robot {
    private int cooldown = 0;
    private int detectionRange = range*3;

    public MeleeRobot(int health, double speed, int range, int damage, int delay, int value) {
        super(health, speed, range, damage, delay, value);
    }

    public void act() {
        attackHumans();
        Human closest = getClosestHuman();
        if(closest != null){
            if(getDistanceTo(closest) < detectionRange){
            turnTowards(closest);
            }
        }
        if (getHealth() <= 0)getWorld().removeObject(this);
        super.act();
    }
}
