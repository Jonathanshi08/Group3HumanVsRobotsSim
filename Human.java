import greenfoot.*;
import java.util.ArrayList;

public abstract class Human extends Units {
    private static int numHumans = 0;

    protected Human(int health, double speed, int range, int damage, int delay) {
        super(health, speed, range, damage, delay, false); 
        numHumans++;
        this.originalSpeed = speed;
    }


    public void act() {
        super.act();
    }

    //Attacks all Robots within range by dealing damage.
    public void attackRobots() {
        if (cooldown > 0) cooldown--;
        ArrayList<Robot> targets = new ArrayList<>(getObjectsInRange(range, Robot.class));
        if (!targets.isEmpty() && cooldown == 0) {
            for (Robot r : targets) r.takeDamage(damage);
            cooldown = delay;
            speed = 0;
        } else if (targets.isEmpty()){
            speed = originalSpeed;
        }
    }
     
    public static int getNumHumans() {
        return numHumans;
    }

    //Decrease human count when removed from the world.
    public void removedFromWorld(World world) {
        numHumans--;
    }
}
