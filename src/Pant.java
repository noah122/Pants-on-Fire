/**
 * This class creates the pants object and sets the
 * pants object. Also removes it when it is killed by the
 * fireball.
 *
 * @author Noah Sicklick
 */
import java.util.ArrayList;
import java.util.Random;

public class Pant // creates the class
{
	private Graphic graphic; // creates a private field of the graphic object
	private Random randGen; // creates a private field to a randGen object
	private boolean isAlive; // creates a private boolean field for the isAlive field

/**
 * Creates a getter method that allows us to access
 * the private graphic field.
 * @return returns the graphic object
 */
	public Graphic getGraphic()
	{
		return this.graphic; // returns the graphic object
	}

/**
 * Creates a pant constructor.
 *
 * @param float x x position on the game screen
 * @param float y y position on the game screen
 * @param Random randGen random number generator
 */
	public Pant (float x, float y, Random randGen) // creates the method
	{
		isAlive = true; // sets the isAlive field to true
		graphic = new Graphic ("PANT"); // creates a new graphic PANT object
		graphic.setPosition(x, y); // sets the postion of the new graphic PANT object
		
		
	}

/**
 * Creates a method that handles when the the fireball collides with 
 * the pant object.
 *
 * @param ArrayList<Fireball> fireballs fireballs ArrayList
 * @return returns a new fire graphic and returns null
 */
	public Fire handleFireballCollisions(ArrayList<Fireball> fireballs) // creates the method
	{
		for (int i = 0; i < fireballs.size(); i++) // populates the ArrayList
		{
			if (graphic.isCollidingWith(fireballs.get(i).getGraphic())) // if the pants graphic is colliding with a fireball
			{
				isAlive = false; // sets the field to false
				fireballs.get(i).destroy(); // destroys the fireball object when it hits the pant object
				return new Fire(graphic.getX(), graphic.getY(), randGen); // returns a new fire object where the pant object was
			}
		}
		return null; // returns null
	}

/**
 * Creates a method that removes the pant object.
 * @return returns either true or false
 */
	public boolean shouldRemove() // creates the method
	{
		if (isAlive != true) // if the isAlive field is false
		{
			return true; // returns true
		}
		else 
		{
			return false; // else returns false
		}
	}

/**
 * Creates a method that keeps track of the updating of the pant
 * object with respect to time.
 *
 * @param int time time variable
 * @return returns void
 */
	public void update(int time) // creates the method
	{
		isAlive = true; // sets the isAlive field to true
		if (isAlive == true) // if it is true
		{
			graphic.draw(); // draw the pant graphic
		}
		
	}
}
