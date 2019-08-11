//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Pants on Fire
// Files:            Graphic.java, GameEngine.java
// Semester:         CS 302 Fall 2016
//
// Author:           Noah Sicklick
// Email:            sicklick@wisc.edu
// CS Login:         sicklick
// Lecturer's Name:  Gary Dahl
// Lab Section:      332
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     (name of your pair programming partner)
// Partner Email:    (email address of your programming partner)
// Partner CS Login: (your partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    ___ Write-up states that Pair Programming is allowed for this assignment.
//    ___ We have both read the CS302 Pair Programming policy.
//    ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.
//
// Persons:          (identify each person and describe their help in detail)
// Online Sources:   (identify each URL and describe its assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Level class is responsible for managing all of the objects in the game.
 * The GameEngine creates a new Level object for each level, and then calls that
 * Level object's update() method repeatedly until it returns either "ADVANCE"
 * (to go to the next level), or "QUIT" (to end the entire game).
 * <br/><br/>
 * This class should contain and use at least the following private fields:
 * <tt><ul>
 * <li>private Random randGen;</li>
 * <li>private Hero hero;</li>
 * <li>private Water[] water;</li>
 * <li>private ArrayList&lt;Pant&gt; pants;</li>
 * <li>private ArrayList&lt;Fireball&gt; fireballs;</li>
 * <li>private ArrayList&lt;Fire&gt; fires;</li>
 * </ul></tt>
 * @author Noah Sicklick
 */
public class Level
{
	/**
	 * This constructor initializes a new Level object, so that the GameEngine
	 * can begin calling its update() method to advance the game's play.  In
	 * the process of this initialization, all of the objects in the current
	 * level should be instantiated and initialized to their beginning states.
	 * @param randGen is the only Random number generator that should be used
	 * throughout this level, by the Level itself and all of the Objects within.
	 * @param level is a string that either contains the word "RANDOM", or the 
	 * contents of a level file that should be loaded and played. 
	 */
	private Graphic graphic; // creates a private field for the object graphic
	private float speed; // creates a private float field called speed
	private int controlType; // creates a private int field called controlType
	public Hero hero; // creates a public field for the object Hero
	private Water[] water; // creates a private field for the water object array
	private ArrayList<Pant> pants; // creates a private ArrayList for the pants object
	private ArrayList<Fireball> fireballs; // creates a private ArrayList for the fireballs object
	private ArrayList<Fire> fires; //creates a private ArrayList for the fires object
	private Random randGen; // creates a private field for the randGen object
	
	
	public Level(Random randGen, String level) // creates a constructor with these parameters
	{ 
		this.randGen = randGen; // creates an instance of the randGen object
		final float PI =  (float) Math.PI; // creates a final float variable called PI to store the value of PI to use throughout the level class
		hero = new Hero(GameEngine.getWidth() / 2, GameEngine.getHeight() / 2, 3); // creates a new hero object with these set parameters that relate to the parameters in the Hero class
		water = new Water[8]; // creates a new water object Array of length 8
		float x = randGen.nextInt(GameEngine.getWidth()); // creates a float x variable that generates a random width value of the game screen
		float y = randGen.nextInt(GameEngine.getHeight()); // creates a float y variable that generates a random height value of the game screen
		this.pants = new ArrayList<Pant>(); // creates an instance of the object pants that stores an ArrayList 
		if (level.equals("RANDOM")) // conditional that determines when the program loads a random level or loads a pre loaded level
		{
			this.createRandomLevel(); // calls the createRandomLevel
		}
		else
		{
			this.loadLevel(level); // calls the loadLevel method if the if statement is not true
		}
		
	
		
	}
	
	

	/**
	 * The GameEngine calls this method repeatedly to update all of the objects
	 * within your game, and to enforce all of the rules of your game.
	 * @param time is the time in milliseconds that have elapsed since the last
	 * time this method was called.  This can be used to control the speed that
	 * objects are moving within your game.
	 * @return When this method returns "QUIT" the game will end after a short
	 * 3 second pause and a message indicating that the player has lost.  When
	 * this method returns "ADVANCE", a short pause and win message will be 
	 * followed by the creation of a new level which replaces this one.  When
	 * this method returns anything else (including "CONTINUE"), the GameEngine
	 * will simply continue to call this update() method as usual. 
	 * @author Noah Sicklick
	 */
	public String update(int time) // creates a method that updates the game
	{
		
		if (hero.handleFireballCollisions(fireballs) == true) // creates a conditional that runs if the hero is hit with a fireball
		{
			hero.handleFireballCollisions(fireballs); // this calls the handleFireballCollisions from the Hero class with the parameter fireballs
			return "QUIT"; // this means that the player has lost 
		}
		for (int i = 0; i < water.length; i++) // a for loop used to populate the contents of the water array
		{
			if(water[i] != null) water[i] = water[i].update(time); // conditional that runs as long as water[i] isn't null and then it updates the water[i] array
		}
		hero.update(time, water); // calls the update method from the Hero class with these parameters
		for (int i = 0; i < pants.size(); i++ ) // creates a for loop to populate the pants ArrayList
		{
			pants.get(i).update(time); // calls the method from the pants class
		}
		for(int i = 0; i < fires.size(); i++) // cretaes a for loop that populates the fires ArrayList
		{
			fires.get(i).handleWaterCollsions(water); // calls the handleWaterCollisions from the fires class when a collision occurs
			Fireball temp = fires.get(i).update(time); // creates a new Fireball object that stores the value of fires.get(i).update(time)
			if(!(temp == null)) // cretaes a conditional that runs when temp does not equal null
			{
				fireballs.add(temp); // adds the value of temp to the fireballs ArrayList
			}
			if (fires.get(i).shouldRemove() == true) // if fires is hit then this method is called to remove the fires object
			{
				fires.remove(i); // removes the fires object 
			}
		}
		for (int i = 0; i < fireballs.size(); i++) // populates the firebalss ArrayList
		{
			fireballs.get(i).update(time); // calls these methods from the Fireball class
			fireballs.get(i).handleWaterCollisions(water); // calls the handleWaterCollision from the Fireball class when fireballs collide with water
			if (fireballs.get(i).shouldRemove() == true) // if fireballs are hit then they need to be removed
			{
				fireballs.remove(i); // this removes the firebalss object that are hit
			}
		}
		for (int i = 0; i < pants.size(); i++) // populates the pants ArrayList
		{
			pants.get(i).update(time); // calls these methods from the Pant class
			Fire tempVal =  pants.get(i).handleFireballCollisions(fireballs); // creates a new Fire object called tempVal that stores when pants get hit by firebalss
			if (tempVal != null) // creates a conditional that runs when tempVal isn't null
			{ 
				fires.add(tempVal); // adds the tempVal to the fires ArrayList
				pants.remove(i); // removes the pants object when hit by a fireball
			}
		}
		if (fires.isEmpty() == true) // runs when all fires objects are eliminated
		{
			return "ADVANCE"; // loads a new level
		}
		if (pants.isEmpty() == true) // runs when all the pants objects are destroyed
		{
			return "QUIT"; // player has lost the game
		}
	
		
		return "CONTINUE"; // keeps running throughout the program
	}	
	
	

	/**
	 * This method returns a string of text that will be displayed in the
	 * upper left hand corner of the game window.  Ultimately this text should 
	 * convey the number of unburned pants and fires remaining in the level.  
	 * However, this may also be useful for temporarily displaying messages that 
	 * help you to debug your game.
	 * @return a string of text to be displayed in the upper-left hand corner
	 * of the screen by the GameEngine.
	 * @author Noah Sicklick
	 */
	public String getHUDMessage() 
	{
		
		return "Pants left: " + pants.size() + "\n" + "Fires left: " + fires.size(); // displays how many pants and fires are left on screen while the player is playing
	}

	/**
	 * This method creates a random level consisting of a single Hero centered
	 * in the middle of the screen, along with 6 randomly positioned Fires,
	 * and 20 randomly positioned Pants.
	 * @author Noah Sicklick
	 */
	public void createRandomLevel() 
	{ 
		final float PI =  (float) Math.PI; // creates a final float variable called PI to store the value of PI to use throughout the level class
		hero = new Hero(GameEngine.getWidth() / 2, GameEngine.getHeight() / 2, randGen.nextInt(3) + 1); // creates a new hero object with these parameters form the Hero class
		water = new Water[8]; // creates a water object array with 8 objects
		float x = randGen.nextInt(GameEngine.getWidth()); // defines a float x variable that stores the width of the game screen
		float y = randGen.nextInt(GameEngine.getHeight()); // defines a float y variable that stores the height of the game screen
		this.pants = new ArrayList<Pant>(); // creates an instance of the pants object that stores an ArrayList of type Pant
		for (int i = 0; i < 20; i++) // creates a for loop that iterates 20 times in order to populate the pants ArrayList
		{
			x = randGen.nextInt(GameEngine.getWidth()); // generates a random width position on the game screen
			y = randGen.nextInt(GameEngine.getHeight()); // generates a random height position on the game screen
			pants.add(i, new Pant(x, y, randGen)); // adds these values to the pants ArrayList
			
		}
		this.fireballs = new ArrayList<Fireball>(); // creates an instance of the fireballs object and sets it equal to the ArrayList with parameter Fireball
		this.fires = new ArrayList<Fire>(); // creates an instance of the fires object and stores an ArrayList with parameter Fire
		for (int j = 0; j < 6; j++) // creates a for loop that iterates 6 times to populate the fires ArrayList
		{
			x = randGen.nextInt(GameEngine.getWidth()); // generates a random width position on the game screen
			y = randGen.nextInt(GameEngine.getHeight()); // generates a random height position on the game screen
			fires.add(j, new Fire(x, y, randGen)); // adds these values to the fires ArrayList
		}
	}

	/**
	 * This method initializes the current game according to the Object location
	 * descriptions within the level parameter.
	 * @param level is a string containing the contents of a custom level file 
	 * that is read in by the GameEngine.  The contents of this file are then 
	 * passed to Level through its Constructor, and then passed from there to 
	 * here when a custom level is loaded.  You can see the text within these 
	 * level files by dragging them onto the code editing view in Eclipse, or 
	 * by printing out the contents of this level parameter.  Try looking 
	 * through a few of the provided level files to see how they are formatted.
	 * The first line is always the "ControlType: #" where # is either 1, 2, or
	 * 3.  Subsequent lines describe an object TYPE, along with an X and Y 
	 * position, formatted as: "TYPE @ X, Y".  This method should instantiate 
	 * and initialize a new object of the correct type and at the correct 
	 * position for each such line in the level String.
	 * @author Noah Sicklick
	 */
	public void loadLevel(String level) // creates the method
	{ 
		String[] lvlX = level.split("\n"); // creates a 2D Array that equals when the level file is split by a new line
		String[][] lvl = new String[lvlX.length][]; // creates a 2D array that equals a new string of with parameter lvlX.length
		for(int i = 0; i < lvlX.length; i++) // populates the lvlX Array
		{
			lvlX[i] = lvlX[i].replace("@", ""); // replaces @ with ""
			lvlX[i] = lvlX[i].replace(",", ""); // replaces "," with ""
			lvlX[i] = lvlX[i].replace("  ", " "); // replaces " " with " "
			lvl[i] = lvlX[i].split(" "); // splits at " "
		}
		
		speed = 0.12f; // sets the speed
		controlType = Integer.parseInt(lvl[0][1]); // controlType equals the integer parsing of that array
		this.pants = new ArrayList <Pant>(); // creates a new pants ArrayList
		this.fires = new ArrayList <Fire>(); // creates a new fires ArrayList
		this.fireballs = new ArrayList <Fireball>(); // creates a new fireballs ArrayList
		
		int p = 0; // sets p to 0
		for(int i = 0; i < lvl.length; i++) // populates the lvl array
		{
			if(lvl[i][0].equals("PANT")) // if the contents in this array equal the string PANT
			{
				pants.add(p, new Pant(Float.parseFloat(lvl[i][1]), Float.parseFloat(lvl[i][2]), randGen)); // add these values to the pants arraylist
				p++; // increment p
			}
		}
		p = 0; // resets to p to 0
		for(int i = 0; i < lvl.length; i++) // populates the lvl array
		{
			if(lvl[i][0].equals("FIRE")) // if the contents in this array equal the string FIRE
			{
				fires.add(p, new Fire(Float.parseFloat(lvl[i][1]), Float.parseFloat(lvl[i][2]), randGen)); // adds these values to the fires ArrayList
				p++; // increments p
			}
		}
		for(int i = 0; i < lvl.length; i++) // populates the lvl array
		{
			if(lvl[i][0].equals("HERO")) // if the contents of this string equal the string HERO
			{
				this.hero = new Hero(Float.parseFloat(lvl[i][1]), Float.parseFloat(lvl[i][2]), controlType); // add these values to the hero object
			}
			this.water = new Water[8]; // creates the water object array of 8
		}
	}

	/**
	 * This method creates and runs a new GameEngine with its first Level.  Any
	 * command line arguments passed into this program are treated as a list of
	 * custom level filenames that should be played in a particular order.
	 * @param args is the sequence of custom level files to play through.
	 *
	 */
	public static void main(String[] args)
	{
		GameEngine.start(null,args);
		
	}
}
