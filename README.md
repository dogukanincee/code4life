# code4life
Codingame Code4Life Puzzle

## 1) Abstract

The challenge on the codingame is Roche's Code4Life Puzzle. Two robots work in a medical complex. The main goal is stated as producing medicine by transporting items between machines. The complex is composed of 3 modules called diagnosis, molecules, and laboratory.

* In diagnosis module, robots will retrieve samples of data files.
* In molecules module, required molecules are gathered to produce the medicine.
* In laboratory module, robots will create medicine to increase their health points.

Samples need some molecules which will be gathered from the molecules module. That is called cost. After they are gathered, in the laboratory, corresponding medicine will be produced. This cycle goes on until the max move. 

However, there are some restrictions about the complex.

* Each player has one robot with the same starting position.
* A robot can carry up to 3 sample data files and 10 molecules at the same time.
* A player can move his / her robot from one module to another by means of the GOTO module command.
* Once the robot is at a module's interface, it can connect to it with the CONNECT command. This will have a different effect for each module.
* A sample data file is an item representing all known data on a tissue sample collected from an untreated patient. Researching this sample may ultimately lead to the production of medicine to prolong the lives of all patients with the same ailment.
* Sample data files can be in one of two states: undiagnosed (initial state) or diagnosed.
* A diagnosed sample data is associated with the list of molecules needed to produce the medicine for that sample.
* Each sample data file has a rank: 1, 2 or 3. The higher the rank, the more health points you will get from the medicine - but more molecules will be needed to produce the medicine.
* A molecule can be one of five types: A, B, C, D or E.

## 2) Procedure

First of all, a variable called sampleList which will contain the list of samples will be created. 
```
 var sampleList = new ArrayBuffer[Sample]
```
Then, a class named Sample will be created in order to create a sample object. Our sample will consist of its id, carriedBy, rank, gain, health and the costs which will be held in an array. The main arguments of the sample are its cost which shows the total need of each molecules (A,B,C,D,E), and its health which corresponds to the point we gain.
```
class Sample(var sampleId: Int, var carriedBy: Int, var rank: Int, var gain: Int, var health: Int, var cost: Array[Int]) {
}
```
In the the for loop where the game constantly checks for the samples, samples are created and all these created samples will be added into a list which will hold them.
```
var sample = new Sample(_sampleId.toInt,_carriedBy.toInt,_rank.toInt,expertiseGain.toInt,_health.toInt, Array(_costA.toInt,_costB.toInt,_costC.toInt,_costD.toInt,_costE.toInt))
            sampleList+=sample
```
Once the samples are being created and held in the sampleList, we will choose the best Sample which has the hightest health, in our terms, which gives the maximum point to us.
To do this; all samples that are carried currently in the sample list are compared to each other by considerinf their healths. When we identify the sample with highest health, we simply assign bestSample to it which was declared as null before.
```
var bestSample:Sample = null
for (sample <- sampleList){
    if((sample.health>maxHealth)&& sample.carriedBy!=1){
        bestSample=sample
        maxHealth=sample.health
    }
}
```
What we want to next is going to a diagnosis module and collect that sample if we carry a sample. The target specify the module that robot needs to go.
```
if (bestSample.carriedBy != 0 ){
    goToAndConnect("DIAGNOSIS",bestSample.sampleId,selectedRobot.target)
}
```            
Then, where robot checks if it is in the desired module occurs in the following function. If target module is the same as the desired module, we connect it. Otherwise, it goes back the module where it was before.

```
def goToAndConnect(module:String,data:Int,position:String){
        if(position==module){
            println("CONNECT " + data)
        }else{
            println("GOTO " + module)
        }
    }
```
Afterwards, we need to find the desired molecule of our sample. Firstly we create a storage which stores all the molecules that the complex has. Fist we create a variable which will be our desired molecule.

```
var neededMolecule:Char = ' '
```

Then we will have an array representing the complex's molecules which are A, B, C, D, and E respectively.

```
 var moleculeArr = Array('A','B','C','D','E')
```
Then we create a robot and a list of robots to show the target of that robot and the storage it has.

```
class Robot(var target: String, var storage: Array[Int]){
}
```
And a list of robots is also initialized.

```
var robotList = new ArrayBuffer[Robot]
```
Then we check for all the molecules to find how many molecules are needed to produce the sample we have.

```
for(i <- 0 until moleculeArr.length){
   if(selectedRobot.storage(i) < bestSample.cost(i))
      neededMolecule = moleculeArr(i)
}
```

Once we have our sample and we know the desired molecules for that sample, but we have not acquired those molecules yet, what we need to do is go to molecules module.

```
if (neededMolecule != ' ') {
   goToAndConnect1("MOLECULES",neededMolecule, selectedRobot.target)
}
```
               
Unlike the first go to method, now our data is not sample, it is the molecule we need. Thus we create a new function that is almost identical to the first go to method. The only difference is, new one takes Char as a data input instead an integer.

```
def goToAndConnect1(module:String,data:Char,position:String){
    if(position==module){
       println("CONNECT " + data)
    }else{
         println("GOTO " + module)
    }
}               
```
