import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RedFlashEffect extends Effect
{
    private int transparency = 150;   // starting transparency
    private int fadeSpeed = 5;        // how quickly it fades
    private boolean fading = true;    // tracks if it should fade

    public RedFlashEffect(int width, int height)
    {
        super(width, height, Color.RED);
        //sets intial image
        image.setTransparency(transparency);
    }

    public void act()
    {
        super.act(); 
    }

    protected void updateEffect()
    {
        if (fading)
        {
            //makes it more transparent
            transparency -= fadeSpeed;
            if (transparency <= 0)
            {
                getWorld().removeObject(this); //remove effect once it fades out
            }
        }
    }

    protected void drawEffect()
    {
        image.clear();
        image.setColor(Color.RED);
        image.fill();
        image.setTransparency(transparency);
        setImage(image);
    }
}