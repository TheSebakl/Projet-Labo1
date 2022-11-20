<a name="readme-top"></a>



<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">Projets - Labo 1</h3>
    <p>
    Realisation of a Genetic Algorithm to find the most optimized actions to do to finish a game.
    </p>
    <p><b>Author: </b> Sébastien Klein - MASI2</p>
    <p><b>Date: </b>Novembre 2022</p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table </summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a>
        <ul>
            <li><a href="#manual">Manual</a></li>
            <li><a href="#automatic">Automatic</a></li>
            <li><a href="#genetic-algorithm">Genetic Algorithm</a></li>
        </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


[![Java][Project-Image-Raw]][Project-Image]

To realize the program, we needed to write both the game and the genetic algorithm.
Afterwards, we needed to mix them together to find the best combination of actions to reach the end of the game.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [Java][Java-URL]
* [Slick2d][Slick-URL]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.


### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/TheSebakl/Projet-Labo1.git
   ```
   OR
   ```sh
   git clone git@github.com:TheSebakl/Projet-Labo1.git
   ```
2. Install Maven Repository dependencies (slick)
   ```sh
   maven clean install
   ```
3. Launch SetupClass.java <br/>
    Note: MainGenetic is also launchable, but it was only to test the genetic algorithm.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
There are 8 Arguments:
* P Worker Threads (it can differ a little, see below)
* Width of the Grid
* Height of the Grid
* Number of Characters of a batch of the algorithm
* Cross Over Rate (20% --> 20% of new Childs and 80% of parents) (Values between 1-100)
* Mutation rate --> (% to have a mutation. See Mutations) (Values between 1-100)
* Milliseconds between each actions.
<br/>
Note 1 : you have to provide no arguments or all arguments. Nothing was implemented to only do a part of them.
<br/>
Note 2 : It was asked to be possible to generate a fixed map with a 9th argument, but didn't have time to implement it.

### Manual
The Manual part is playable by the user, there is no genetic algorithm behind.
You can play it without arguments, of with fixing the Number of Characters (4th arguments) to 1.

You can control the character with the numpad
* 7 - to go upside left
* 8 - to go upside
* 9 - to go upside right
* 4 - to go left
* 5 - to stand
* 6 - to go right
* 1 - to go downside left
* 2 - to go downside
* 3 - to go downside right

You can also use these commands:
* r - keep the same map but reload
* n - renew a new map

The number of Working Threads are totally ignored in manual mode, and only 2 threads are generated:
1. To play (game) (main)
2. To apply Gravity

### Automatic

If you set a number of Characters (4th arguments) different to 1, the automatic mode is activated.
The genetic algorithm is applied and will calculate the score on P Working Thread (first argument).

In addition of these P Thread, there are the Main Thread (show) and a hidden Thread to play the best one with a key.

In automatic mode, the following keys are available:
* Left and Right Arrow - To switch between the differents working Threads
* B - To show on a hidden Thread the best path calculated at the moment
* N - To reload a new map but apply the same path (not implemented yet).

On the hidden Thread, the ticks between action is multiplied by 5 to have time to see the action.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- Genetic Algorithm -->
### Genetic Algorithm

The path (subject of the genetic Algorithm) is defined by some Movements.
The working Threads play these paths switching between action and gravity. 
When the game is finished, the score is put in the path, and the next one is defined.

The algorithm waits that everything has a score (it checks every seconds).

#### Score
The score is calculated on the distance between the character and the final tile.
The Score is squared so it will make the priority at the distance before the number of actions.
In the case of Genetic Algorithm, it will add the number of actions, to try to keep the same place, but with less movements.

<!-- Cross-Over -->
#### Cross-Over

When everything has a score, it will keep the X% (cross-over rate) best parents,
and will remove the other ones.

It will fill the remaining empty places with a cross-over of the parents.
For this, it will take the size of the longer one of the parents.

For each Movements, it will take one of them calculated on the ration of the score (the lower score = the best chance to put his Movement).

<!-- Mutation -->
#### Mutation

When the cross-over is done, it will take everything and try to apply a mutation.
The mutation will happen if Random is smaller than X% (Mutation rate)

if there is a mutation, it will be one of the following:
* 33.3% Remove a gene (random)
* 33.3% Add a gene (random, at random place)
* 33.3% Change a gene (random, and can keep his)

<!-- Remarks -->
## Remarks

I know my project is not perfect, here is a list of remarks for my project.
* The critic part of the multi-Thread is the Score Part. It is in Synchronized mode.
* I Wanted to allow the possibility of obstacle with the possibility to kill them by upside but there were 2 problems:
    * Sometimes 2 spawns side-by-side and with the gravity it was impossible to do it.
    * When i started the automatic one, the enemies didn't respawn after the first one killed them
    I could solve these 2 problems, but because my time was limited, i chose to remove them from the generation, but the code to generate them is still there.
* I didn't make the possibility to create a fixed map (time was a factor).
* I didn't finish the way to reload a brand new map and continue the last generation
* I don't have a specific stop to genetic algorithm because the pathing can be different. 

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Optimization

Most of my Tests were done with the following arguments: 
10 18 8 100 20 3 15
    
Here is what I noticed with my tests:
* The number of working Threads of my tests (10) and the ticks between action (15) were perfect to have a rapid result and to see the algorithm work.
* The CrossOver already add a lot of change (without mutation) because of the difference in size.
* On some maps, a local minimum can be present, and it is good to have a greater crossOver rate, but in general, 30% is a good one.
* On mutation, I didn't see a lot of changes between a smaller one and a bigger one, because it depends of the map. (a greater mutation is interesting with maps with local minimum)
    

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[Project-Image-raw]: https://github.com/TheSebakl/Projet-Labo1/blob/master/image_projet.png?raw=true
[Project-Image]: https://github.com/TheSebakl/Projet-Labo1/blob/master/image_projet.png
[Java-URL]: https://www.java.com/fr/
[Slick-URL]: https://slick.ninjacave.com/