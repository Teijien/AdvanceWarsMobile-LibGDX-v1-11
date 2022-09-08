# MicroWars
Upgraded AdvanceWarsMobile to use LibGDX 1.11 and Java 17

## About
This is a small prototype project used to get familiar with LibGDX and the basic principles of Model-View-Presenter Architecture. The format of the game is
based on the GBA game AdvancedWars in terms of how each unit's attack scales based on their current health. This is not intended to be a full remake of all of
the mechanics, however more features may be added in the future.

## What is Model-View-Presenter (MVP)
MVP is a method of separation of concerns from the model of the game, and how it is displayed to the user/players. The model represents the data holding the
actual game, the view holds information on how that data is displayed, and the presenter handles the passing of information between the view and model.

In this game, the presenter is in charge of processing input and passing it to the model, as the model should only be responsible for the game logic and not how
input is processed. The InputAdapter class from the LibGDX library made a good class to use as the presenter.

## How the game works
Each side has six units starting opposite each other. The game plays out like chess, with each unit being able to move to any tile within 3 spaces, and can
attack a space at most 4 spaces away. Units each have 100 health, represented by the number next to them, and will die once their health reaches 0. Unit damage
is based on their current health, so the less health they have, the less damage they will do. 

Use careful strategy to defeat the other players units!

### Notes on gameplay
- This game is intended for 2 players, so no CPU player has been implemented currently.
- Turns do not exist, so players may take multiple turns in a row if they choose. This is currently left up to the discretion of the players.
- Buildings are for asthetic purposes as well as aiding in determing tile size. They do not have any impact on actual gameplay.

## How to Install
From releases, download desktop-1.0.jar. To run the file, you will need Java 17 or higher.
NOTE: CURRENT RELEASE IS TEMPORARILY UNAVAILABLE
