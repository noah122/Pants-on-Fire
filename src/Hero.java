/**
 * Class that creates the hero object and draws it.
 * Sets the position of the hero object and also the
 * controlTypes of the hero object.
 *
 * @author Noah Sicklick
 */
import java.util.ArrayList;

public class Hero // creates the Hero class
{
	private Graphic graphic; // creates a graphic field
	private float speed; // creates a private float speed field
	private int controlType; // creates a private int controlType field
	private ArrayList<Fireball> fireballs; // creates a private fireballs ArrayList
	
/**
 * This is a getter method so we can get the private graphic 
 * object and use it.
 *
 * @return returns reference to the graphic object
 */
	public Graphic getGraphic() // creates the method
	{
		return this.graphic; // returns the graphic object so we can use it
	}

/**
 * This creates the controlType of the hero graphic.
 * Also handles when the hero is hit by a fireball object.
 *
 * @param float x x position on the game screen
 * @param float y y position on the game screen
 * @param int controlType creates the controlType of the hero
 * @return returns nothing because it is a constructor
 */
	public Hero(float x, float y, int controlType) // creates method
	{
		
		graphic = new Graphic("HERO"); // creates a hero object graphic
		graphic.setPosition(x, y); // sets the x and y position of the hero object
		speed = 0.12f; // sets the speed of the hero object
		this.controlType = controlType; // sets the reference of the controlType to the controlType
		this.fireballs = new ArrayList<Fireball>(); // sets fireballs equal to a new ArrayList
		
		
	}

/**
 * Creates a method that hadnles when the hero hits into fireball objects.
 * @param ArrayList<Fireball> fireballs the ArrayList fireballs
 * @return returns either true or false
 */
	public boolean handleFireballCollisions(ArrayList<Fireball> fireballs) // creates the method
	{
		for (int i = 0; i < fireballs.size(); i++) // populates the fireballs ArrayList
		{
			fireballs.get(i).getGraphic().isCollidingWith(graphic); // calss these method when the fireball is being hit with the hero graphic
			if (fireballs.get(i).getGraphic().isCollidingWith(graphic) == true) // if the fireballs is hitting the hero
			{
				return true; // sets this field to true
			}
		}
		return false; // returns false
	}

/**
 * Creates method that updates the hero object based on time.
 * Also the contains the code for the controlType movements.
 *
 * @param int time the time variable
 * @param Water [] water the water object Array
 */
	public void update(int time, Water [] water) // creates the method
	{
		for (int i = 0; i < water.length; i++) // populates the water array for the length of the water Array
		{
			if ((GameEngine.isKeyHeld("SPACE") || GameEngine.isKeyHeld("MOUSE")) && water[i] == null) // if all these fields are null
			{
				water[i] = new Water(graphic.getX(), graphic.getY(), graphic.getDirection()); // it will get the water arrays graphic and direction postion 
				break; // exits this conditional
			}
			
		}
		final float PI =  (float) Math.PI; // creates a final float of the Math PI command
		graphic.draw(); // draws the hero object
		
		if (controlType == 1) // if the controlType is 1
		{
		
			if (GameEngine.isKeyHeld("D") == true) // if the D key is held down
			{
				graphic.setPosition((speed * time) + graphic.getX(), graphic.getY()); // sets the position of the hero object
				graphic.setDirection(0); // sets the direction of the hero to the right
			}
			else if (GameEngine.isKeyHeld("A") == true) // if the a key is being held
			{
				graphic.setPosition(-(speed * time) + graphic.getX(), graphic.getY()); // sets the position of the water object to the left
				graphic.setDirection(PI); // sets the direction to the left
			}
			else if (GameEngine.isKeyHeld("W") == true) // if the w key is being held
			{
				graphic.setPosition(graphic.getX(), (-(speed * time) + graphic.getY())); // sets the position of the hero to the up direction
				graphic.setDirection((3 * PI) / 2); // sets the direction towards the top of the screen
			}
			else if (GameEngine.isKeyHeld("S") == true) // if the s key is being held down
			{
				graphic.setPosition(graphic.getX(), (speed * time) + graphic.getY()); // sets the position of the hero to the bottom of the screen
				graphic.setDirection(PI / 2); // sets the direction towards the bottom of the screen
			}
		}
		if (controlType == 2) // if the controlType is 2
		{
			if (GameEngine.isKeyHeld("D") == true) // if the d key is being held down
			{
				graphic.setPosition((speed * time) + graphic.getX(), graphic.getY()); // sets the position of the hero to the bottom of the screen
				graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY()); // sets the direction to the position of the mouse
			}
			else if (GameEngine.isKeyHeld("A") == true) // if the a key is being held down
			{
				graphic.setPosition(-(speed * time) + graphic.getX(), graphic.getY()); // sets the position of the hero to the left
				graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY()); // sets the direction to the position of the mouse
			}
			else if (GameEngine.isKeyHeld("W") == true) // if the w of the key is being held down
			{
				graphic.setPosition(graphic.getX(), (-(speed * time) + graphic.getY())); // sets the position of the hero to the top of the screen
				graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY()); // sets the direction to the position of the mouse
			}
			else if (GameEngine.isKeyHeld("S") == true) // if the s key is being held down
			{
				graphic.setPosition(graphic.getX(), (speed * time) + graphic.getY()); // sets the position to the bottom of the screen
				graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY()); // sets the direction to the position of the mouse
			}
		}
		if (controlType == 3) // if the controlType is 3
		{
			if (Math.sqrt(Math.pow(graphic.getX() - GameEngine.getMouseX(), 2) + // if the hero object is within a distance of 20 pixels of the mouse
					Math.pow(graphic.getY() - GameEngine.getMouseY(), 2)) > 20)
			{
				graphic.setPosition(graphic.getX() + (graphic.getDirectionX() * (speed * time)) , graphic.getY() + (graphic.getDirectionY() * (speed * time))); // sets the position to the position of the mouse
				graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY()); // sets the direction to that of the mouse
		
			
			}	
		}
	}
}
