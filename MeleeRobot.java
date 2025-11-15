import greenfoot.*;
import java.util.ArrayList;

public class MeleeRobot extends Robot {
    private int cooldown = 0;
    private int detectionRange;
    private int baseRotation;

    public MeleeRobot(int health, double speed, int range, int damage, int delay, int value) {
        super(health, speed, range, damage, delay, value);
        this.detectionRange = range * 3;
        this.baseRotation = getRotation();
    }

    public void act() {
        attackHumans();
        Human closest = getClosestHuman();

        if (closest != null && getDistanceTo(closest) < detectionRange) {
            turnTowards(closest.getX(), closest.getY());
        } else {
            setRotation(baseRotation);
        }

        if (getHealth() <= 0) {
            getWorld().removeObject(this);
            return;
        }

        super.act();
    }
}
