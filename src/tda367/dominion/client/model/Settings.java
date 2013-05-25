package tda367.dominion.client.model;
/**
 * Will be the main reference for global constants and options variables.
 * @author Group 28
 */
public class Settings {
	public static final int INGAMESTATE = 0;
	public static final int MAINMENUSTATE = 1;
	public static final int OPTIONSSTATE = 2;
	public static final int SERVERLISTSTATE = 3;
	public static final int SPLASHSTATE = 4;
	public static final int SHOWCARDSTATE = 5;
	public static final int ENDGAMESTATE = 6;
	public static int SCREENHEIGHT = 1280;
	public static int SCREENWIDTH = 800;
	
	public static boolean fullscreen = false;
	public static boolean fpsshow = false;
	public static boolean inGame = false;
	private static String name = "Pleb";
	
	public static String  getName(){
		
		return name;
	}
	
	public static void setName(String s){
		name = s;
	}
	
	public static void setFullscreen(boolean b) {
		fullscreen = b;
	}
	
	public static void showFps(boolean b) {
		fpsshow = b;
	}
	
	public static void setResolution(int height, int width) {
		SCREENHEIGHT = height;
		SCREENWIDTH = width;
	}

}
