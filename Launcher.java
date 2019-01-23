package Russia;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import in_out.In;
import in_out.Out;

public class Launcher {
	//declare public variables
	public static int choice;
	public static String name;

	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException  {
		// TODO Auto-generated method stub
		//Display options and get there response
		Out.println("Welcome to war Please select your side\n1. Story Mode \"The Siege of leningrad\"");
		choice = In.getInt();
	
		//if a player chooses the first option allow them to customize settings then start the tutorial
		/*if(choice == 1) {
			Out.println("please tell us your name: ");
			name = In.getString();
			Out.println("Please enter the maximum number of deaths per player you wish to occur");
			TutorialAi.maxDeaths = In.getInt();
			Out.println("enter the map size (Must be greater than 2)");
			TutorialAi.mapSize = In.getInt();
			Out.println("enter the playing field size(must be greater than 9)");
			TutorialAi.playingField = In.getInt();
			Out.println("enter number of miliseconds between each \"Frame\" (I recommend above 200)");
			TutorialAi.timerDelay = In.getInt();
			TutorialAi.deathCount = 0;
			TutorialAi.enemyDeathCount = 0;
			TutorialAi.start();
		}*/
		if(choice == 1)
		{
			
			Out.println("all choices are Yes or No\n");
			Out.println("Directional movement\n"
					+ "N = North\n"
					+ "E = East\n"
					+ "S = South\n"
					+ "W = West\n"
					+ "Directional Shooting\n"
					+ "Y = North\n"
					+ "J = East\n"
					+ "H = South\n"
					+ "G = West\n");
			StoryLine.Intro();
		}
		//if number not listed chosen then restart the launcher
		else {
			Launcher.main(null);
		}
		
	}

}
