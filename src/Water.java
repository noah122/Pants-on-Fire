/**
 * Creates a Water class that creates the water object
 * as well as destroys the water object when it travels
 * more than 200 pixels.
 * @author Noah Sicklick
 */
public class Water // creates the water class
{
	private Graphic graphic; // creates a private field of the graphic object
	private float speed; // creates a private float field for speed
	private float distanceTraveled; // creates a private float field for distanceTraveled
	private Water water; // creates a private field for the water object
	
	/**
	 * This is a getter method that gets the graphic object
	 * because it is a private field.
	 *
	 * @return returns the graphic
	 */
	public Graphic getGraphic() // creates the method
	{
		return this.graphic; // returns the graphic
	}
	
	/**
	 * Creates a method that creates the visual object 
	 * and sets its position and direction.
	 *
	 * @param float x x position of the game screen
	 * @param float y y position of the game screen
	 * @param float direction direction on the game screen
	 */
	public Water(float x, float y, float direction) // creates the method
	{
		final float PI =  (float) Math.PI; // creates a final float of the Math PI call so we can use its value in a variable
		speed = 0.7f; // sets the speed of the water object
		graphic = new Graphic("WATER"); // creates the visual graphic of water
		graphic.setPosition(x, y); // sets the x and y position of the water object
		graphic.setDirection(direction); // sets the direction of the water object
		
		
		
	}
	
	/**
	 * Creates a method that updates the water object with respect to time.
	 * It draws the water object when the distanceTraveled is less than 200 pixels.
	 *
	 * @param int time the time variable
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return returns either this or null
	 */
	public Water update(int time) // creates the method
	{
		
		if (distanceTraveled > 200) // if the distanceTravled is greater than 200
		{
			return null; // return null reference to the water object
		}
		else 
		{
		distanceTraveled += (speed*time); // sets the distanceTraveld to itself plus speed*time
		final float PI =  (float) Math.PI; // allows the use of the Math PI method in variable form
		graphic.setX(graphic.getX() + ((speed * time) * graphic.getDirectionX())); // sets the x position of the water object
		graphic.setY(graphic.getY() + ((speed * time) * graphic.getDirectionY())); // sets the y position of the water object
		System.out.println(distanceTraveled);
		
		graphic.draw(); // draws the water object
		
		return this; // returns this
		}
	}
}

