# Software Architecture Project Group 24

This project was done as a part of the evaluation of the subject TDT4240 “Software
Architecture” during the Spring of 2022. The educational goal of the project was
“Learn to design, evaluate, implement and test a software architecture through game
development”. Therefore, we have made a game for Android with software
architecture as the main focus.

## Structure

You will find the main parts of the code under core/src/com/mygdx/game/.
In this folder you will see four classes, a package for all the sprites, and a package for all the states.
The four classes not contained within one of the packages are dealing with higher level tasks and can not be classified as either a state nor a sprite. This is the MyGdxGame.java module, dealing with general application tasks, and the FireBaseInterface.java and CoreInterfaceClass.java modules, which both deal with the communication to the Firebase server. 

Naturally, all java files contain a single class each.

## Installation and running the game

* Install Android Studio
* Get the project by cloning with “git clone https://github.com/Hallvaeb/TDT4240_progark_prosjekt.git” in a terminal at the location where you wish the project to exist, or by downloading the zip-file.
* Open the project in Android Studio, and connect your android device. Alternatively, open AVD Manager and create a virtual machine.
* Sync the project with Gradle Files.
* Run the game on an Android device or emulator.

## Check out the promo video!

https://www.youtube.com/watch?v=EZ7M69i-h0I

## Documents

Implementation: [Implementation.pdf](https://github.com/Hallvaeb/TDT4240_progark_prosjekt/files/8558292/Implementation.pdf)

Revised Requirement Design: [Requirements Design.pdf](https://github.com/Hallvaeb/TDT4240_progark_prosjekt/files/8558293/Requirements.Design.pdf)

Revised Architecture: [Architecture.pdf](https://github.com/Hallvaeb/TDT4240_progark_prosjekt/files/8558296/Architecture.pdf)

## Diagrams

Diagrams:  https://drive.google.com/file/d/15TcfDem8b2CAecJHgehR5X1ClGo7eGnT/view?usp=sharing

## Architecture tradeoff analysis method (ATAM) evaluations

Of this project, by group 5: [ATAM_24.pdf](https://github.com/Hallvaeb/TDT4240_progark_prosjekt/files/8558258/ATAM_24.pdf)

Of group 5, by group 24 (this): [ATAM_5.pdf](https://github.com/Hallvaeb/TDT4240_progark_prosjekt/files/8558261/ATAM_5.pdf)

## First submission (outdated):

Requirements:  https://docs.google.com/document/d/17_xFGxvue2xvbUH-N1ImPpMiIEx3t3rC0wpJVPmIN-A/edit

Architecture:  https://docs.google.com/document/d/1HzAi8gTwZqwSEfr6mED551_65aetOPkZC-gfHLCh7A0/edit?usp=sharing

### Contributors and creators of this project (alphabetical):

Hallvard Enger Bjørgen, Andreas Lindkjenn Bø, Mads Olav Eek, Mats Guddingsmo, Ulrik 
Rørnes, Kristofer Sejersted
