/**
 * This class creates the fires objects and creates the methods
 * that eliminates these fires methods when its heat becomes less than 1
 * @author Noah Sicklick
 */

import java.util.Random;

public class Fire // creates a constructor
{
	private Graphic graphic; // creates a reference to the graphic object
	private Random randGen; // creates a reference to the randGen object
	private int fireballCountdown; // creates a reference to a private int variable fireballCountdown
	private int heat; // creates a reference to a private int variable heat
	 
	
	/**
	 * This is a getter method so we can use the graphic object
	 * because it is a private field.
	 * @return reference to graphic
	 */
	public Graphic getGraphic()
	{
		return this.graphic; // returns the graphic object
	}
	
	/**
	 * (Write a succinct description of this method here.  If necessary,
	 * additional paragraphs should be preceded by &lt;p&gt;, the html tag for
	 * a new paragraph.)
	 *
	 * @param float x sets the x position of the fire object
	 * @param float y sets the y position of the fire object
	 * @return Random randGen randomizes the x and y positions of the fire object
	 */
	public Fire(float x, float y, Random randGen) // creates a constructor with these parameters
	{
		graphic = new Graphic ("FIRE"); // creates a new graphic object with parameter "FIRE"
		graphic.setPosition(x, y); // sets the positions of the fire object
		fireballCountdown = ((int)(Math.random() * 3001) + 3000); // randomizes fire
		heat = 40; // sets the life of the fire object

	}

/**
 *Creates a method that can handle the fire and
 *water collision.
 *
 * @param Water [] water the water object Array
 * @return returns void
 */
	public void handleWaterCollsions(Water[] water) // creates the method
	{
		for (int i = 0; i < water.length; i++) // creates a for loop that runs to populate the water Array
		{
			if (water[i] != null) // runs when the water object is not null (still in action)
			{
				if (graphic.isCollidingWith(water[i].getGraphic())) // checks to see if the water object array is touching a fire
					{
					heat--; // decreases the life of the fire by 1 after each water collision
					water[i] = null; // sets each live water Array to dead(null)
					}
			}
		}
	}
	
/**
 * creates a method that removes the fire object when 
 * it runs out of heat
 *
 * 
 * @return returns either true or false depending on the values
 */
	public boolean shouldRemove() // creates the method
	{
		if (heat < 1) // creates a conditional that runs when the fire object is out of heat
		{
			return true; // sets the field to true in order to remove the fire object
		}
		else 
		{
			return false; // sets the field to false meaning the fire object is still alive
		}
	}

/**
 *Creates a method that updates when fireballs are hit and dead,
 *also when fireballs are created.
 * @param int time the time parameter
 * @return reutrns null meaning fireball is dead
 */
	public Fireball update(int time) // creates the method
	{
		if (heat < 1) // creates a conditional that runs when fire heat is less than 1
		{
			return null; // returns null which gets rid of the fire object
		}
		graphic.draw(); // draws fireball 
		fireballCountdown-= time; // sets the fireballCountdown variable to itself minus time
		
		if (fireballCountdown <= 0) // creates a conditional that runs when fireball countdown is less or equal to 0
		{
			fireballCountdown = ((int)(Math.random() * 3001) + 3000); // sets fireball countdown to a randon value 
			return new Fireball(graphic.getX(), graphic.getY(), (float)(Math.random() * 2 * Math.PI)); // returns a new random postions of the fireball object
			
		}
		return null; // returns nothing
	}
	
	
}
