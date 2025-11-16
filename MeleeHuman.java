import greenfoot.*; 
import java.util.List;

public class MeleeHuman extends Human {

    private GreenfootImage idleImage;              
    private GreenfootImage[] walkingFrames = new GreenfootImage[7]; 
    private GreenfootImage[] attackFrames = new GreenfootImage[3]; // Damage frames

    private int walkAnimationCounter = 0;
    private int walkAnimationSpeed = 5; // acts per frame

    private int attackFrameIndex = 0;
    private int attackFrameCounter = 0;
    private int attackFrameSpeed = 10; // acts per frame
    private boolean attacking = false;

    public MeleeHuman(int health, double speed, int range, int damage, int delay, int value) {
        super(health, speed, range, damage, delay, value);

        // Idle image
        idleImage = new GreenfootImage("meeleHuman000.png");
        idleImage.mirrorHorizontally();
        idleImage.scale(65,65);
        setImage(idleImage);

        // Walking frames 1–7
        for (int i = 1; i <= 7; i++) {
            walkingFrames[i-1] = new GreenfootImage("meeleHuman00" + i + ".png");
            walkingFrames[i-1].mirrorHorizontally();
            walkingFrames[i-1].scale(65,65);
        }

        // Attack frames 0–2
        for (int i = 0; i < 3; i++) {
            attackFrames[i] = new GreenfootImage("humanMeeleAttack00" + i + ".png");
            attackFrames[i].mirrorHorizontally();
            attackFrames[i].scale(65,65);
        }
    }

    @Override
    protected void attackBehavior() {
        // Not used in act()
    }

    @Override
    public void act() {
        if (getWorld() == null || getHealth() <= 0) return;

        MeleeRobot target = getClosestMeleeRobot();
        if (target != null) {
            double distance = getDistanceTo(target);

            if (distance > range) {
                // Move toward robot
                attacking = false;
                moveToward(target);
                animateWalking();
            } else {
                // Attack when touching
                attacking = true;
                animateAttack(target);
            }
        } else {
            // No target → idle
            attacking = false;
            setImage(idleImage);
        }
    }

    private void animateAttack(MeleeRobot target) {
        setImage(attackFrames[attackFrameIndex]);

        // Deal damage only when cooldown allows
        if (cooldown == 0) {
            target.takeDamage(damage);
            cooldown = delay;
        } else {
            cooldown--;
        }

        // Advance attack frame at controlled speed
        attackFrameCounter++;
        if (attackFrameCounter >= attackFrameSpeed) {
            attackFrameCounter = 0;
            attackFrameIndex = (attackFrameIndex + 1) % attackFrames.length;
        }
    }

    private void animateWalking() {
        int frame = (walkAnimationCounter / walkAnimationSpeed) % walkingFrames.length;
        setImage(walkingFrames[frame]);
        walkAnimationCounter++;
    }

    private void moveToward(MeleeRobot target) {
        double dx = target.getX() - getX();
        double dy = target.getY() - getY();
        double distance = Math.hypot(dx, dy);

        if (distance > 0) {
            setLocation(
                getX() + (int)(dx / distance * getSpeed()),
                getY() + (int)(dy / distance * getSpeed())
            );
        }
    }

    private MeleeRobot getClosestMeleeRobot() {
        if (getWorld() == null) return null;

        List<MeleeRobot> robots = getWorld().getObjects(MeleeRobot.class);
        MeleeRobot closest = null;
        double minDist = Double.MAX_VALUE;

        for (MeleeRobot r : robots) {
            if (r.getHealth() <= 0) continue;
            double dist = getDistanceTo(r);
            if (dist < minDist) {
                minDist = dist;
                closest = r;
            }
        }
        return closest;
    }
}




