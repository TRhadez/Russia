package Russia;


import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import in_out.In;
import in_out.Out;

public class StoryLine {
	public static String choice;
	public static void Intro() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		//print out text followed by a pause of a set length(5 seconds)
		Out.println("\"Nikolai it is time\"");
		Sleep();
		Out.println("\"Nikolai you must do it\"");
		Sleep();
		Out.println("\"Nikolai you must kill him he is German, Nikolai are you listening?\"");
		Sleep();
		Out.println("Infront of you stands Hans the leutenant of the 23rd Panzer Division Wounded from a stray bullet he holds his arm");
		Sleep();
		Out.println("To your left is a Makarov Pistol you reach over to pick it up and shoot but Hans strikes you");
		Sleep();
		Sleep();
		Out.println("You wake back up to see his back turned as he looks at your partially destroyed T34-76 Do you pick up your Makarov and shoot");
		//give the player a choice and change the story to a relevant line.
		choice = In.getString();
		 if (choice.equalsIgnoreCase("Yes")) {
			 Out.println("You reach over and pick up the Makarov and shoot Hans in the back only to be met with bullets from his officers");
			 Sleep();
			 Out.println("You get struck with 6 bullets");
			 Sleep();
			 Out.println("\"He'll Never walk again\"");
			 Sleep();
			 Out.println("\"See the funny thing is Nikolai we told you you would never walk again but you will and you will back on the field soon\"");
			 Sleep();
			 
		 }
		 if (choice.equalsIgnoreCase("No")) {
			 Out.println("Hans walks away and as they are walking away a german bomber flies overhead");
			 Sleep();
			 Out.println("\"We need a medic stat he still has vitals\"");
			 Sleep();
			 Out.println("\"Nikolai everything will be alright\"");
			 
		 }
		 Sleep();
		 Chapter1();
	}
	public static void Chapter1() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		Out.println("Welcome back Nikolai, Leningrad is being over run by Germans your job is to clear the city of them");
		Sleep();
		Out.println("The area you are patrolling will be broken into a 1km grid made of 100m sectors. you must clear all of them "
				+ "of panzers with your newly built T34-85");
		Sleep();
		//set odds to 10%
		StoryLineGame.odds = 0.1;
		StoryLineGame.start();
		
	}
	public static void Chapter2() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		Out.println("\"Nikolai you have done it you took out a panzer IV with the power of the red these Nazis will stand no chance\"");
		Sleep();
		Out.println("\"Head back onto the battlefield right now before we get ambushed your doing the motherland good Nikolai\"");
		//set odds to 25%
		StoryLineGame.odds = 0.25;
		StoryLineGame.Movement();
		
	}
	public static void Chapter3 () throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		Out.println("\"Ha ha you did it Nikolai another German panzer off the streets of Leningrad you are doing a fantastic job\"");
		Sleep();
		Out.println("You better get back on the battlefield quickly as i believe they have spotted an enemy less than a kilo out");
		//set odds to 40%
		StoryLineGame.odds = 0.4;
		StoryLineGame.Movement();
	}
	public static void Chapter4 () throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		StoryLineGame.Movement();
	}
	public static void DeadEnding() {
		Out.println("\"Here lies Nikolai Tank Commander of the 21st tank division\"");
		//terminate the program
		System.exit(0);
	}
	public static void Ending() {
		Out.println("\"Nikolai you did it you helped clear Leningrad of those damned Nazis\""
				+ "\n\"I hear that the westereners are fighting in Normandy hopefully they can defeat them there\""
				+ "\n\"We must continue our push and defeat them on their ground\""
				+ "\n\"We must Take back Poland\""
				+ "\n\"You can do it Nikolai\"");
		//terminate the program
		System.exit(0);
	}
	
	public static void Sleep () {
		//put the game to sleep for 5000 milliseconds 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

} 
