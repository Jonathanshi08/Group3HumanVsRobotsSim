import greenfoot.*;

public class MeleeHuman extends Human {
    private int cooldown = 0;
    private int detectionRange;
    private int baseRotation;

    public MeleeHuman(int health, double speed, int range, int damage, int delay, int value) {
        super(health, speed, range, damage, delay, value);
        this.detectionRange = range * 3;
        this.baseRotation = 180; 
    }


    public void act() {
        Robot closest = getClosestRobot();

        if (closest != null && getDistanceTo(closest) < detectionRange) {
            if (getDistanceTo(closest) > getRange()) {
                speed = originalSpeed;
                turnTowards(closest.getX(), closest.getY());
                setLocation(getX() + getChaseDX(), getY() + getChaseDY());
            } else {
                attackRobots();
            }
            } else {
                speed = originalSpeed;
                setRotation(baseRotation);
                setLocation(getX() + getChaseDX(), getY() + getChaseDY());
            }    
        if (getX() < 0 || getX() > getWorld().getWidth() || getY() < 0 || getY() > getWorld().getHeight()) {
            getWorld().removeObject(this);
        }

    }

    private double getChaseDX() {
        double angle = Math.toRadians(getRotation());
        return Math.cos(angle) * Math.abs(getSpeed());
    }

    private double getChaseDY() {
        double angle = Math.toRadians(getRotation());
        return Math.sin(angle) * Math.abs(getSpeed());
    }
}
