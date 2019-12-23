# AnimalEwolution



## User Manual

### Running program
To start the program download "runnable" directory and run in it "java -jar AnimalEwolution.jar".

### World parameters
User can define custom world parameters in "parameters.json" file as in the example:

```
{
  "width": 100,
  "height": 100,
  "jungleRatio": 0.4,
  "startEnergy": 100,
  "plantEnergy": 100,
  "moveEnergy": 2,
  "initialAnimals": 20
}
```

### Window description

* #### Starting window:
Starting window contains fields for specifying number of maps and duration of one day in milliseconds and
 buttons for creating, starting/stopping maps and exiting program. Clicking "CREATE" button starts given number of
  world's windows. 
  * #### World window:
 World window contains visualisation of current situation in given world and it's statistics. Field "save after" serves for 
specifying number of days after witch program will create "world_id_stats.txt" file with average of that world's
 statistics from these days in format
 ```
...
average number of animals: 22.0
...
```
  Clicking on one of animals on map starts separate window with
  information about
  that
  animal. 
 * #### Animal window:
 Animal window contains fields showing information about animal's genotype, age and energy. In the fourth field user
  can specify number of days after which program will display number of children followed animal had in these days.

## Versions

Current version: 1.0.0 

Other [versions](https://github.com/PKopel/AnimalEwolution/tags)

## Author

* **Paweł Kopel**
