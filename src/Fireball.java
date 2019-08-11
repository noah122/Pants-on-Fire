/**
 * This class creates and sets the fireball obejct.
 * Also checks when the fireball object is dead.
 *
 * @author Noah Sicklick
 */
public class Fireball // creates class
{
	private Graphic graphic; // creates a private field of the graphic object
	private float speed; // creates a private float field for the variable speed
	private boolean isAlive; // creates a private boolean field for the isLAive variable
	private Fireball fireballs; // creates a private reference for the fireball object
	
/**
 * Creates a getter method to get the private
 * graphic object.
 * @return reurns the graphic object
 */
	public Graphic getGraphic() // creates the method
	{
		return this.graphic; // returns the private field of the graphic reference
	}


/**
 * Creates the position and direction of the fireball object.
 * Also checks when the fireball hits a water object and destroys the 
 * object and removes it.
 *
 * @param float x x position of the fireball object
 * @param float y y position of the fireball object
 * @param float directionAngle sets the direction angle of the fireball obbject
 * @return (description of the return value)
 */
	public Fireball(float x, float y, float directionAngle) // creates the method
	{
		speed = 0.2f; // sets the speed of the fireball object
		this.isAlive = true; // sets the isAlive field to true
		graphic = new Graphic("FIREBALL"); // creates the graphic of the fireball object
		graphic.setPosition(x, y); // sets the x and y position of the fireball
		graphic.setDirection(directionAngle); // sets the direction of the fireball object
		
	}
/**
 * Creates a method that handles the collisions 
 * between the water object and the fireball 
 * object.
 *
 * @param Water [] water the water object Array
 * @return returns void 
 */
	public void handleWaterCollisions(Water[] water) // creates the method with these parameters
	{
		for (int i = 0; i < water.length; i++) // creates a for loop the populates the water Array
		{
			if(water[i] != null) // if the water Array is null it runs this conditional
			{
				if (graphic.isCollidingWith(water[i].getGraphic())) // if the water object is colliding with a fireball this conditional runs
				{
					isAlive = false; // sets the isAlive field to false
					water[i] = null; // sets water object to null because it got destroyed by a fireball
				}
			}
		}
	}

/**
 * THis method is called when the fireball object is destroyed.
 * @return returns void
 */
	public void destroy() // creates the method
	{
		isAlive = false; // sets the isAlive field to false
	}

/**
 * This method is called to remove the fireball object
 * when is is hit and has to be removed.
 *
 * @return returns true or false
 */
	public boolean shouldRemove() // creates the method
	{
		if(isAlive != true) // if the isAlive field isn't true this conditional runs
		{
			return true; // returns true
		}
		else 
		{
			return false; // else returns false
		}
	}

/**
 * Creates a method that updates the fireball object
 * with the parameter of time.
 *
 * @param int time the time variable
 * @return returns void
 */
	public void update(int time) // creates the method with this parameter
	{
		if ((graphic.getDirectionX() > (100 + GameEngine.getWidth())) || (graphic.getX() < -100) || (graphic.getY() < -100) || 
				(graphic.getY() > (100 + GameEngine.getHeight()))) // creates a conditional that runs when the fireball graphic goes more than 100 pixels off of the game screen
		{
			isAlive = false; // sets isAlive field to false
		}
		if (isAlive == true) // runs when the isAlive field is true 
		{
			graphic.setPosition(graphic.getX() + (time * speed * graphic.getDirectionX()), 
					graphic.getY() + (time * speed * graphic.getDirectionY())); // sets the position of the fireball field
			graphic.draw(); // draws the fireball object
		}
	}
}
