package tda367.dominion.client.model;
/**
 * Will be the main reference for global constants and options variables.
 * @author Glazastik
 *
 */
public class Settings {
	public static final int INGAMESTATE = 0;
	public static final int MAINMENUSTATE = 1;
	public static final int OPTIONSSTATE = 2;
	public static final int SERVERLISTSTATE = 3;
	public static final int SPLASHSTATE = 4;
	public static final int SHOWCARDSTATE = 5;
	public static int SCREENHEIGHT = 1280;
	public static int SCREENWIDTH = 800;
	
	public static boolean FULLSCREEN = false;
	public static boolean FPSSHOW = false;
	private static String name = "Pleb";
	
	public static String  getName(){
		
		return name;
	}
	
	public static void setName(String s){
		name = s;
	}

}
