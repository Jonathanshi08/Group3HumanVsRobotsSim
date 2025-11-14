import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Effect extends SuperSmoothMover
{
    //manages the dimensions of the effect
    protected int width;
    protected int height;
    protected GreenfootImage image;

    //tracks the time
    protected int timer = 0;

    public Effect(int width, int height, Color baseColor)
    {
        //creates the required rectangle based on paramters and set values
        this.width = width;
        this.height = height;
        
        image = new GreenfootImage(width, height);
        image.setColor(baseColor);
        image.fill();
        setImage(image);
    }

    public void act()
    {
        timer++; //increases timer
        updateEffect();   // describe what happens (flash speed)
        drawEffect();     // describe how it looks (colour, dimension, transparency)
    }

    protected abstract void updateEffect();
    protected abstract void drawEffect();
}
