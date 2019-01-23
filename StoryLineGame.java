package Russia;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import in_out.In;
import in_out.Out;

public class StoryLineGame {
	
		//declare public variables
		public static int mapSize = 10;
		public static int playingField = 20;
		public static String location[][];
		public static int xCord;
		public static int yCord;
		public static int fightXCord;
		public static int fightYCord;
		public static int enemyXCord;
		public static int enemyYCord;
		public static int enemy1XCord;
		public static int enemy1YCord;
		public static int enemy2XCord;
		public static int enemy2YCord;
		public static int enemy3XCord;
		public static int enemy3YCord;
		public static int bulletX;
		public static int bulletY;
		public static int randomNumber;
		public static char direction;
		public static int timerDelay = 200;
		public static double odds;
		public static double randomDouble;
		public static int chapter = 1;
		
		//Create method start, used to set up bounds and initialize many variables.
		public static void start() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
			//make sure that the array size is the largest of the two maps
			location = new String[Math.max(playingField +1, mapSize + 1)][Math.max(playingField +1, mapSize + 1)];
			//get a random number and make it so the player starts at the top in a random spot
			randomNumber = (int)(Math.random()*mapSize);
			xCord = randomNumber;
			yCord = 0;
			Out.println("\"Welcome to Leningrad Nikolai or whats left of it\" \n");
			// create your grid array and fill with [ ]
			for(int y = 0; y < mapSize; y++) {
				for(int x = 0; x < mapSize; x++) {
					location[y][x] = "[ ]";
				}
			}
			//make it so your location is differentiated from empty blocks
			location[yCord][xCord] = "[#]";
			//get enemy a random location on the map and check to make sure they do not generate on the player
			enemy1XCord = (int)(Math.random()*mapSize);
			enemy1YCord = (int)(Math.random()*mapSize);
			while(location[enemy1YCord][enemy1XCord].equals(location[yCord][xCord]) ) {
				enemy1XCord = (int)(Math.random()*mapSize);
				enemy1YCord = (int)(Math.random()*mapSize);
			}
			enemy2XCord = (int)(Math.random()*mapSize);
			enemy2YCord = (int)(Math.random()*mapSize);
			while(location[enemy2YCord][enemy2XCord].equals(location[yCord][xCord])) {
				enemy2XCord = (int)(Math.random()*mapSize);
				enemy2YCord = (int)(Math.random()*mapSize);
			}
			enemy3XCord = (int)(Math.random()*mapSize);
			enemy3YCord = (int)(Math.random()*mapSize);
			while(location[enemy3YCord][enemy3XCord].equals(location[yCord][xCord])) {
				enemy3XCord = (int)(Math.random()*mapSize);
				enemy3YCord = (int)(Math.random()*mapSize);
			}
			// check to see if the locations of enemy 1 and 2 are the same
			while(enemy2YCord == enemy1YCord && enemy2XCord == enemy1XCord) {
				enemy2XCord = (int)(Math.random()*mapSize);
				enemy2YCord = (int)(Math.random()*mapSize);
			}
			// check to see if the locations of enemy 1 and 3 and 2 and 3 are the same
			while((enemy3YCord == enemy1YCord && enemy3XCord == enemy1XCord) || (enemy3YCord == enemy2YCord && enemy3XCord == enemy2XCord) ) {
				enemy3XCord = (int)(Math.random()*mapSize);
				enemy3YCord = (int)(Math.random()*mapSize);
			}
			// call method to allow movement
			Movement();
		}
		public static void PrintScreen(){
			//create loops to display the map
			for(int y = 0; y < mapSize; y++) {
				for(int x = 0; x < mapSize; x++) {
					Out.print(location[y][x]);
				}
				Out.println();
			}
			
		}
		public static void FightPrintScreen() {
			//create loops to display the map
			for(int y = 0; y < playingField; y++) {
				for(int x = 0; x < playingField; x++) {
					Out.print(location[y][x]);
				}
				Out.println();
				
			}
			
		}
		//create a method to allow movement in the map and detect collisions.
		public static void Movement() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
			//make it so your location is differentiated from empty blocks
			location[yCord][xCord] = "[#]";
			// call method to print the screen
			PrintScreen();
			// while the player is not sharing coordinates with an enemy allow free movement
			while((location[yCord][xCord] != location[enemy1YCord][enemy1XCord]) && (location[yCord][xCord] != location[enemy2YCord][enemy2XCord]) && (location[yCord][xCord] != location[enemy3YCord][enemy3XCord])) {
				//clear the players location to prevent multiple character symbols
				location[yCord][xCord] = "[ ]";
				Out.print("enter your direction\n");
				//get the compass direction the player wishes to go
				direction = In.getChar();
				//create a switch to determine the direction and move the player in that direction
				switch(direction) {
				case 's':
				case 'S':
					if((yCord < mapSize - 1)) {
						yCord++;
						location[yCord][xCord] = "[#]";
					}
					else {
						Out.println("invalid");
						location[yCord][xCord] = "[#]";
					}
					break;
				case 'n':
				case 'N':
					if((yCord > 0)) {
						yCord--;
						location[yCord][xCord] = "[#]";
					}
					else {
						Out.println("invalid");
						location[yCord][xCord] = "[#]";
					}
					break;
				case 'e':
				case 'E':
					if(( xCord < mapSize - 1)) {
						xCord++;
						location[yCord][xCord] = "[#]";
					}
					else {
						Out.println("invalid");
						location[yCord][xCord] = "[#]";
					}
					break;
				case 'w':
				case 'W':
					if((xCord > 0)) {
						xCord--;
						location[yCord][xCord] = "[#]";
					}
					else {
						Out.println("invalid");
						location[yCord][xCord] = "[#]";
					}
					break;
					default:
						location[yCord][xCord] = "[#]";
						Out.println("invalid Entry");
						break;
				}
				//print the screen
				PrintScreen();
			}
			//if the player is located on the same coordinates of an enemy initiate the fight and/or start the next chapter
			if(location[yCord][xCord] == location[enemy1YCord][enemy1XCord])
			{
				locationCalc();
				location[fightYCord][fightXCord] = "[#]";
				Out.println();
				// move the enemy to a unseen coordinate
				enemy1YCord = 20;
				enemy1XCord = 20;
				fight();
				if((enemy1XCord == 20 )&&(enemy2XCord == 20)&&(enemy3XCord == 20)) {
					StoryLine.Ending();
					System.exit(0);
				}
				chapter++;
				switch(chapter) {
				case 2:
					StoryLine.Chapter2();
					break;
					
				case 3:
					StoryLine.Chapter3();
					break;
					
				case 4:
					StoryLine.Chapter4();
					break;
					
				default:
					StoryLine.Ending();
					break;
				}
				
			}
			//if the player is located on the same coordinates of an enemy initiate the fight and/or start the next chapter
			else if(location[yCord][xCord] == location[enemy2YCord][enemy2XCord])
			{
				locationCalc();
				location[fightYCord][fightXCord] = "[#]";
				Out.println();
				// move the enemy to a unseen coordinate
				enemy2YCord = 20;
				enemy2XCord = 20;
				fight();
				if((enemy1XCord == 20 )&&(enemy2XCord == 20)&&(enemy3XCord == 20)) {
					StoryLine.Ending();
					System.exit(0);
				}
				chapter++;
				switch(chapter) {
				case 2:
					StoryLine.Chapter2();
					break;
					
				case 3:
					StoryLine.Chapter3();
					break;
					
				case 4:
					StoryLine.Chapter4();
					break;
					
				default:
					StoryLine.Ending();
					break;
				}
				
			}
			//if the player is located on the same coordinates of an enemy initiate the fight and/or start the next chapter
			else if(location[yCord][xCord] == location[enemy3YCord][enemy3XCord])
			{
				locationCalc();
				location[fightYCord][fightXCord] = "[#]";
				Out.println();
				// move the enemy to a unseen coordinate
				enemy3YCord = 20;
				enemy3XCord = 20;
				fight();
				if((enemy1XCord == 20 )&&(enemy2XCord == 20)&&(enemy3XCord == 20)) {
					StoryLine.Ending();
					System.exit(0);
				}
				chapter++;
				switch(chapter) {
				case 2:
					StoryLine.Chapter2();
					break;
					
				case 3:
					StoryLine.Chapter3();
					break;
					
				case 4:
					StoryLine.Chapter4();
					break;
					
				default:
					StoryLine.Ending();;
					break;
				}
				
			}
		
		}
		public static void locationCalc () throws LineUnavailableException, UnsupportedAudioFileException, IOException {
			//calculate what direction the player was last seen heading and set up the fight screen to depict as if the came from that location
			randomNumber = (int)(Math.random()*playingField);
			switch(direction) {
			case 'n':
			case 'N':
				fightYCord = playingField - 1;
				fightXCord = randomNumber;
				enemyYCord = 0;
				randomNumber = (int)(Math.random()*playingField);
				enemyXCord = randomNumber;
				break;
			case 'e':
			case 'E':
				fightXCord = 0;
				fightYCord = randomNumber;
				enemyXCord = playingField - 1;
				randomNumber = (int)(Math.random()*playingField);
				enemyYCord = randomNumber;
				break;
			case 's':
			case 'S':
				fightYCord = 0;
				fightXCord = randomNumber;
				enemyYCord = playingField - 1;
				randomNumber = (int)(Math.random()*playingField);
				enemyXCord = randomNumber;
				break;
			case 'w':
			case 'W':
				fightXCord = playingField - 1;
				fightYCord = randomNumber;
				enemyXCord = 0;
				randomNumber = (int)(Math.random()*playingField);
				enemyYCord = randomNumber;
				break;
			}
			for(int y = 0; y < playingField; y++) {
				for(int x = 0; x < playingField; x++) {
					location[y][x] = "[ ]";
				}
			}
		}
		public static void fight() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
			//while the enemy is on screen allow the game to loop
			while(enemyYCord >= 0) {
				randomDouble = Math.random();
				;
				//if a enemy is able to and has the right amount of chance to allow them to shoot 
				if((fightXCord == enemyXCord || fightYCord == enemyYCord)&& randomDouble <= odds) {
					AI();
					Out.println();
				}
				//if none of that is possible start your turn to fight
				else {
					//set enemies starting location
				location[enemyYCord][enemyXCord] = "[O]";
				//print the screen
				FightPrintScreen();
				//clear the players previous location
					location[fightYCord][fightXCord] = "[ ]";
					//get the direction they wish to move or shoot
					direction = In.getChar();
					//switch the direction they wish to shoot or move and do so accordingly
							switch(direction) {
							case 's':
							case 'S':
								if((fightYCord < playingField - 1)) {
									fightYCord++;
									location[fightYCord][fightXCord] = "[#]";
								}
								else {
									Out.println("invalid");
									location[fightYCord][fightXCord] = "[#]";
								}
								break;
							case 'n':
							case 'N':
								if((fightYCord > 0)) {
									fightYCord--;
									location[fightYCord][fightXCord] = "[#]";
								}
								else {
									Out.println("invalid");
									location[fightYCord][fightXCord] = "[#]";
								}
								break;
							case 'e':
							case 'E':
								if(( fightXCord < playingField - 1)) {
									fightXCord++;
									location[fightYCord][fightXCord] = "[#]";
								}
								else {
									Out.println("invalid");
									location[fightYCord][fightXCord] = "[#]";
								}
								break;
							case 'w':
							case 'W':
								if((fightXCord > 0)) {
									fightXCord--;
									location[fightYCord][fightXCord] = "[#]";
								}
								else {
									Out.println("invalid");
									location[fightYCord][fightXCord] = "[#]";
								}
								break;
							case 'Y':
							case 'y':
								//start the bullet relative to where the player shot from
								bulletX = fightXCord;
								bulletY = fightYCord - 1;
								// play a sound of a cannon firing 
								ShootSound();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//move the bullet in the direction shot and re-apply the player on the screen, print the screen
								while(bulletY >= 0) {
									location[fightYCord][fightXCord] = "[#]";
									location[bulletY][bulletX] = "[o]";
									FightPrintScreen();
									Out.println();
									location[bulletY][bulletX] = "[ ]";
									bulletY--;
									//if the bullet hits an enemy play a explosion sound  and remove the enemy from screen ending the fight
									if(bulletX == enemyXCord && bulletY == enemyYCord) {
				
										location[bulletY][bulletX] = "[ ]";
										bulletY = -1;
										enemyXCord = -1;
										enemyYCord = -1;
										DeathSound();
									}
									try {
										Thread.sleep(timerDelay);
									} catch (InterruptedException e) {
										e.printStackTrace();
										Out.println("didnt work debug time");
									}
								}
								break;
							case 'G':
							case 'g':
								//start the bullet relative to where the player shot from
								bulletX = fightXCord - 1;
								bulletY = fightYCord;
								// play a sound of a cannon firing 
								ShootSound();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//move the bullet in the direction shot and re-apply the player on the screen, print the screen
								while(bulletX >= 0) {
									location[fightYCord][fightXCord] = "[#]";
									location[bulletY][bulletX] = "[o]";
									FightPrintScreen();
									Out.println();
									location[bulletY][bulletX] = "[ ]";
									bulletX--;
									//if the bullet hits an enemy play a explosion sound  and remove the enemy from screen ending the fight
									if(bulletX == enemyXCord && bulletY == enemyYCord) {
										
										location[bulletY][bulletX] = "[ ]";
										bulletX = -1;
										enemyXCord = -1;
										enemyYCord = -1;
										DeathSound();
									}
									try {
										Thread.sleep(timerDelay);
									} catch (InterruptedException e) {
										e.printStackTrace();
										Out.println("didnt work debug time");
									}
								}
								
								break;
							case 'H':
							case 'h':
								//start the bullet relative to where the player shot from
								bulletX = fightXCord;
								bulletY = fightYCord + 1;
								// play a sound of a cannon firing 
								ShootSound();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//move the bullet in the direction shot and re-apply the player on the screen, print the screen
								while(bulletY < playingField) {
									location[fightYCord][fightXCord] = "[#]";
									location[bulletY][bulletX] = "[o]";
									FightPrintScreen();
									Out.println();
									location[bulletY][bulletX] = "[ ]";
									bulletY++;
									//if the bullet hits an enemy play a explosion sound  and remove the enemy from screen ending the fight
									if(bulletX == enemyXCord && bulletY == enemyYCord) {
									
										location[bulletY][bulletX] = "[ ]";
										bulletY = playingField;
										enemyXCord = -1;
										enemyYCord = -1;
										DeathSound();
									}
									try {
										Thread.sleep(timerDelay);
									} catch (InterruptedException e) {
										e.printStackTrace();
										Out.println("didnt work debug time");
									}
								}
								break;
							case 'J':
							case 'j':
								//start the bullet relative to where the player shot from
								bulletX = fightXCord + 1;
								bulletY = fightYCord;
								// play a sound of a cannon firing 
								ShootSound();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//move the bullet in the direction shot and re-apply the player on the screen, print the screen
								while(bulletX < playingField) {
									location[fightYCord][fightXCord] = "[#]";
									location[bulletY][bulletX] = "[o]";
									FightPrintScreen();
									Out.println();
									location[bulletY][bulletX] = "[ ]";
									bulletX++;
									//if the bullet hits an enemy play a explosion sound  and remove the enemy from screen ending the fight
									if(bulletX == enemyXCord && bulletY == enemyYCord) {
										
										location[bulletY][bulletX] = "[ ]";
										bulletX = playingField;
										enemyXCord = -1;
										enemyYCord = -1;
										DeathSound();
										
									}
									try {
										Thread.sleep(timerDelay);
									} catch (InterruptedException e) {
										e.printStackTrace();
										Out.println("didnt work debug time");
									}
								}
							
								
								break;
								default:
									location[fightYCord][fightXCord] = "[#]";
									Out.println("invalid Entry");
									break;
							}
					
					
					
							
			}
				//once your turn is done allow the enemy to move in a random direction
				AIMove();
			
						
			}
			//when the fight is done clear the screen
			location[fightYCord][fightXCord] = "[ ]";
			fightYCord = mapSize +1;
			fightXCord = mapSize +1;
			return;
		}
		public static void AI() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
			if(enemyXCord == fightXCord && enemyYCord < fightYCord) {
				//start the bullet relative to where the enemy shot from
				 bulletY = enemyYCord + 1;
					bulletX = enemyXCord;
					ShootSound();
					// play a sound of a cannon firing 
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//move the bullet in the direction shot and re-apply the enemy on the screen, print the screen
					while(bulletY < playingField) {
						location[enemyYCord][enemyXCord] = "[0]";
						location[bulletY][bulletX] = "[o]"; 
						FightPrintScreen();
						Out.println();
						location[bulletY][bulletX] = "[ ]";
						bulletY++;
						//if the bullet hits the player play a explosion sound  and remove the player from screen ending the fight
						if(bulletX == fightXCord && bulletY == fightYCord) {
							
							location[bulletY][bulletX] = "[ ]";
							bulletY = playingField;
							DeathSound();
							Thread.sleep(1500);
							StoryLine.DeadEnding();
						}
						try {
							Thread.sleep(timerDelay);
						} catch (InterruptedException e) {
							e.printStackTrace();
							Out.println("didnt work debug time");
						}
					}
					
					FightPrintScreen();
					
			 }
			 else if(enemyXCord == fightXCord && enemyYCord > fightYCord){
				//start the bullet relative to where the enemy shot from
				 bulletY = enemyYCord - 1;
					bulletX = enemyXCord;
					ShootSound();
					// play a sound of a cannon firing 
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//move the bullet in the direction shot and re-apply the enemy on the screen, print the screen
					while(bulletY > 0) {
						location[enemyYCord][enemyXCord] = "[0]";
						location[bulletY][bulletX] = "[o]";
						FightPrintScreen();
						Out.println();
						location[bulletY][bulletX] = "[ ]";
						bulletY--;
						//if the bullet hits the player play a explosion sound  and remove the player from screen ending the fight
						if(bulletX == fightXCord && bulletY == fightYCord) {
							
							location[bulletY][bulletX] = "[ ]";
							bulletY = -1;
							DeathSound();
							Thread.sleep(1500);
							StoryLine.DeadEnding();
						}
						try {
							Thread.sleep(timerDelay);
						} catch (InterruptedException e) {
							e.printStackTrace();
							Out.println("didnt work debug time");
						}
					}
					
					FightPrintScreen();
			 }
			else if(enemyYCord == fightYCord && enemyXCord > fightXCord){
				//start the bullet relative to where the enemy shot from
				 bulletX = enemyXCord - 1;
					bulletY = enemyYCord;
					ShootSound();
					// play a sound of a cannon firing 
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//move the bullet in the direction shot and re-apply the enemy on the screen, print the screen
					while(bulletX > 0) {
						location[enemyYCord][enemyXCord] = "[0]";
						location[bulletY][bulletX] = "[o]";
						FightPrintScreen();
						Out.println();
						location[bulletY][bulletX] = "[ ]";
						bulletX--;
						//if the bullet hits the player play a explosion sound  and remove the player from screen ending the fight
						if(bulletX == fightXCord && bulletY == fightYCord) {
							
							location[bulletY][bulletX] = "[ ]";
							bulletX = -1;
							DeathSound();
							Thread.sleep(1500);
							StoryLine.DeadEnding();
							
						}
						try {
							Thread.sleep(timerDelay);
						} catch (InterruptedException e) {
							e.printStackTrace();
							Out.println("didnt work debug time");
						}
					}
					
					FightPrintScreen();
			}
			else if(enemyYCord == fightYCord && enemyXCord < fightXCord){
				//start the bullet relative to where the enemy shot from
					 bulletX = enemyXCord + 1;
						bulletY = enemyYCord;
						ShootSound();
						// play a sound of a cannon firing 
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//move the bullet in the direction shot and re-apply the enemy on the screen, print the screen
						while(bulletX < playingField) {
							location[enemyYCord][enemyXCord] = "[0]";
							location[bulletY][bulletX] = "[o]";
							FightPrintScreen();
							Out.println();
							location[bulletY][bulletX] = "[ ]";
							bulletX++;
							//if the bullet hits the player play a explosion sound  and remove the player from screen ending the fight
							if(bulletX == fightXCord && bulletY == fightYCord) {
								
								location[bulletY][bulletX] = "[ ]";
								bulletX = playingField;
								DeathSound();
								Thread.sleep(1500);
								StoryLine.DeadEnding();
							}
							try {
								Thread.sleep(timerDelay);
							} catch (InterruptedException e) {
								e.printStackTrace();
								Out.println("didnt work debug time");
							}
						}
						
						FightPrintScreen();
				 
			}
		}
		public static void AIMove() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
			if(enemyYCord != -1) {
				//create a random number and if possible move the enemy in the direction reletive to the number
				randomNumber = (int)(Math.random()*4);
				switch(randomNumber) {
				case 0:
					if(enemyXCord < playingField - 1) {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyXCord++;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					else {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyXCord--;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					break;
				case 1:
					if(enemyXCord > 0) {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyXCord--;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					else {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyXCord++;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					break;
				case 2:
					if(enemyYCord < playingField - 1) {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyYCord++;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					else {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyYCord--;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					break;
				case 3:
					if(enemyYCord > 0) {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyYCord--;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					else {
						location[enemyYCord][enemyXCord] = "[ ]";
						enemyYCord++;
						location[enemyYCord][enemyXCord] = "[X]";
					}
					break;
				default:
					
					break;
				}
			}
		}
		public static void DeathSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
			// specify the sound to play
		    // (assuming the sound can be played by the audio system)
		    File soundFile = new File("DeathSound.wav");
		    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

		    // load the sound into memory (a Clip)
		    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		    Clip clip = (Clip) AudioSystem.getLine(info);
		    clip.open(sound);

		    // due to bug in Java Sound, explicitly exit the VM when
		    // the sound has stopped.
		    clip.addLineListener(new LineListener() {
		        public void update(LineEvent event) {
		            
		        	if (event.getType() == LineEvent.Type.STOP) {
		                event.getLine().close();
		            }
		        }
		    }); clip.start();
		}
		public static void ShootSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
			// specify the sound to play
		    // (assuming the sound can be played by the audio system)
		    File soundFile = new File("Shoot.wav");
		    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

		    // load the sound into memory (a Clip)
		    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		    Clip clip = (Clip) AudioSystem.getLine(info);
		    clip.open(sound);

		    // due to bug in Java Sound, explicitly exit the VM when
		    // the sound has stopped.
		    clip.addLineListener(new LineListener() {
		        public void update(LineEvent event) {
		            
		        	if (event.getType() == LineEvent.Type.STOP) {
		                event.getLine().close();
		                
		            }
		        }
		    }); clip.start();
		}
		

}