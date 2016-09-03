# Avego-Game-Exercise
In accordance with our standard practice, we ask that you complete the game exercise detailed below
and return same to us within the next 48 hours, together with supporting material (testing/assumptions/docs).
  As you will understand, this will enable us to assess your level of understanding of object design and the Java language.
Please follow the exercise as directly, keeping your assumptions to a minimum.
Also, please document any assumptions or instructions that would be necessary to interpret your submission.
(assume your documentation is going to be used by a developer who has to maintain your code)

##Game Exercise
===========================
A playing area is 100m x 100m. A game has a referee and 10 players.
 A player moves 1m every second and starts out in a random place on the playing area.
 A referee has to give a yellow card to a player if that player moves within 2m of another player.
  It is the player that moves to within 2m gets the card.
  If a player gets 2 yellow cards, the player is ejected from the game for 10 seconds.
  When a player is off 10 secs the player needs to ask the referee if they are eligible to play again.
  The referee will let the player return to playing the first time this happens, but not subsequent times.
  The last player left playing is the winner.
Write a console application that emulates this activity.


## About Avego Game Implementation and Assumptions:
===========================
* This game design base on the practice request info in the "AvegoQuestion.txt" file. In this game
assume that there is a Playground that Referee and Players play the game in it. The width and height of the Playground come from the command line otherwise
its will be as default value that set in the code.
* Referee control the moving, fine and finding of Players winner in the Playground. Referee Class implement IReferee and Runable interface. Also there is just one Referee instance in the game. Referee control the move of the Players. If the Player get out of the Playground, Referee will return
it back to the game (in a random location) and check its move again. There is no penalty for getting off the field.
*Each Player run as a Thread and will be alive until kick out of the match or game finished and it will be winner. Player
class implement the IPlayable and Runable interface. All properties of the player and the Referee base on the assumption
in the "AvegoQuestion.txt". Players will move in 4 directions like the Arrow Keys. Private Zone will be calculating as
a circle around player. If any player come to the Private Zone of other player he will be fine for that.Maybe Player
will fine for more  than one(Its can be in more that one player private zone).
* Referee need to know about the Players so the Player array will be pass to the Referee instance. Also each Player
should know who is his/her Referee, so the Referee instance will be pass through argument to all players.
* There is not any communication between Players. Referee will fine and control Players. Referee and the end of
the match--there is just one Player in the field/waiting out side of field-- will find the Winner of the game and print
its information.
* There is a Util Class that calculate the two Player zone conflict. That's use a
simple mathematical formula for finding intersect of two circle in a board. The PrivateZone of the player can be pass
thought command line otherwise its will consider as default(PrivateZone=2). Also its can be different for each Player.
* The Playground consider as Thread, because this can be a leaf of the tree game and the Winner will be play with winners
of the other branches in different Playground.


###Requirement: For run program you need JRE 1.6.
This program code with JDK 1.6. The source code is a Maven module and its will be easy to import in any IDE.
