import greenfoot.*;
import java.util.List;

public class TerritoryBar extends Actor {
    private int barWidth = 200;
    private int barHeight = 20;
    private int borderThickness = 2;

    private Color humanColor = Color.BLUE;
    private Color robotColor = Color.RED;
    private Color neutralColor = Color.GRAY;
    private Color borderColor = Color.BLACK;

    private GreenfootImage barImage;

    public TerritoryBar() {
        // Only create blank image here
        barImage = new GreenfootImage(barWidth, barHeight);
        setImage(barImage);
    }

    public void addedToWorld(World w) {
        // Initial drawing with neutral color
        drawNeutral();
    }

    public void act() {
        // Update every frame
        updateBar();
    }

    private void drawNeutral() {
        barImage.clear();
        barImage.setColor(neutralColor);
        barImage.fillRect(0, 0, barWidth, barHeight);
        barImage.setColor(borderColor);
        for (int i = 0; i < borderThickness; i++) {
            barImage.drawRect(i, i, barWidth - 1 - (i*2), barHeight - 1 - (i*2));
        }
        setImage(barImage);
    }

    private void updateBar() {
        if (getWorld() == null) return;

        int worldWidth = getWorld().getWidth();

        int humanX = getFurthestHumanX();
        int robotX = getFurthestRobotX();

        // convert world positions to bar pixels
        int humanPos = (int)((double)humanX / worldWidth * barWidth);
        int robotPos = (int)((double)robotX / worldWidth * barWidth);

        // draw bar
        barImage.clear();
        barImage.setColor(neutralColor);
        barImage.fillRect(0, 0, barWidth, barHeight);

        barImage.setColor(humanColor);
        barImage.fillRect(0, 0, humanPos, barHeight);

        barImage.setColor(robotColor);
        barImage.fillRect(robotPos, 0, barWidth - robotPos, barHeight);

        // border
        barImage.setColor(borderColor);
        for (int i = 0; i < borderThickness; i++) {
            barImage.drawRect(i, i, barWidth - 1 - (i*2), barHeight - 1 - (i*2));
        }

        setImage(barImage);
    }

    private int getFurthestHumanX() {
        List<Human> humans = getWorld().getObjects(Human.class);
        if (humans.isEmpty()) return 0;

        int minX = getWorld().getWidth(); // humans start on right side
        for (Human h : humans) {
            if (h.getX() < minX) minX = h.getX();
        }
        return minX;
    }

    private int getFurthestRobotX() {
        List<Robot> robots = getWorld().getObjects(Robot.class);
        if (robots.isEmpty()) return 0;

        int maxX = 0;
        for (Robot r : robots) {
            if (r.getX() > maxX) maxX = r.getX();
        }
        return maxX;
    }
}




