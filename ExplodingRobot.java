import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class ExplodingRobot extends Robot {
    private int detectionRange;
    
    private double speed;
    private double rushSpeed;
    private boolean rushing = false;
    
    private GreenfootImage[] walkFrames;
    private int currentFrame = 0;
    private int animationDelay = 8; 
    private int frameCounter = 0;

    public ExplodingRobot(int health, double speed, int range, int damage, int delay, int value) {
        super(health, speed, range, damage, delay, value);
        this.detectionRange = range*2;
        this.rushSpeed = 2.5 * speed;
        loadWalkFrames();
        setImage(walkFrames[0]);
    }


    public void act() {
        if (getWorld() == null) return;
        if (getHealth() <= 0) {
            explode();
            return;
        }

        checkFenceAhead();
        if (getWorld() == null) return;
        Human closest = getClosestHuman();
        if (closest != null) {
            double dist = getDistanceTo(closest);

            if (dist <= range) {
                explode();
                return;
            }

            if (dist <= detectionRange) {
                turnTowards(closest);
            }
        }

        animateWalk();
        super.act();
    }
    
    private void loadWalkFrames() {
        walkFrames = new GreenfootImage[8];
        for (int i = 0; i < 8; i++) {
            walkFrames[i] = new GreenfootImage("images/ExplodingRobotWalking" + (i + 1) + ".png");
            walkFrames[i].scale(25, 50); // optional: adjust to fit your world size
        }
    }

    private void checkFenceAhead() {
        if (getWorld() == null) return;
        // Look a short distance in front of the robot
        int lookDistance = 40;
        int checkWidth = 25;

        List<Fences> fencesAhead = getObjectsInRange(lookDistance, Fences.class);
        if (!fencesAhead.isEmpty()) {
            rushing = true;
            setSpeed(speed);

            Fences fence = fencesAhead.get(0);
            if (isTouching(Fences.class)) {
                System.out.println("damaged");
                explode();
            }
        } else if (rushing) {
            // go back to normal speed if no fence ahead
            rushing = false;
            setSpeed(speed);
        }
    }
    
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }
    
    private void animateWalk() {
        frameCounter++;
        if (frameCounter >= animationDelay) {
            frameCounter = 0;
            currentFrame = (currentFrame + 1) % walkFrames.length;
            setImage(walkFrames[currentFrame]);
        }
    }

    private void explode() {
        BombEffect explosionEffect = new BombEffect();
        getWorld().addObject(explosionEffect, getX(), getY());
        //attackHumans();
        getWorld().removeObject(this);
    }
}
