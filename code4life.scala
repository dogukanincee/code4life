import math._
import scala.util._
import scala.io.StdIn._
import scala.collection.mutable.ArrayBuffer
//in order to create an arraybuffer, Arraybuffer library is imported

//A class to create sample objects which takes id, rank, expertiseGain, health and array of costs
class Sample(var sampleId: Int, var carriedBy: Int, var rank: Int, var gain: Int, var health: Int, var cost: Array[Int]) {
}

//A class to create robot which takes target as string and array of storages
class Robot(var target: String, var storage: Array[Int]){
}

/**
 * Bring data on patient samples from the diagnosis machine
 * to the laboratory with enough molecules to produce medicine!
 **/
 
object Player extends App {
    val projectCount = readLine.toInt
    for(i <- 0 until projectCount) {
        val Array(a, b, c, d, e) = (readLine split " ").map (_.toInt)
    }

//A fuction for modules to connect or go to which takes its data as integer
    def goToAndConnect(module:String,data:Int,position:String){
        if(position==module){
            println("CONNECT " + data)
        }else{
            println("GOTO " + module)
        }
    } 

//A fuction for modules to connect or go to which takes its data as character
 def goToAndConnect1(module:String,data:Char,position:String){
        if(position==module){
            println("CONNECT " + data)
        }else{
            println("GOTO " + module)
        }
    } 

    // game loop
    while(true) {
        var sampleList = new ArrayBuffer[Sample]
        var myStorage = new ArrayBuffer[Int]
        var robotList = new ArrayBuffer[Robot]
        var moleculeArr = Array('A','B','C','D','E')
        for(i <- 0 until 2) {
            val Array(target, _eta, _score, _storageA, _storageB, _storageC, _storageD, _storageE, _expertiseA, _expertiseB, _expertiseC, _expertiseD, _expertiseE) = readLine split " "
            val eta = _eta.toInt
            val score = _score.toInt
            val storageA = _storageA.toInt
            val storageB = _storageB.toInt
            val storageC = _storageC.toInt
            val storageD = _storageD.toInt
            val storageE = _storageE.toInt
            val expertiseA = _expertiseA.toInt
            val expertiseB = _expertiseB.toInt
            val expertiseC = _expertiseC.toInt
            val expertiseD = _expertiseD.toInt
            val expertiseE = _expertiseE.toInt
            
            /*
            * As initial, storages and targets need to be collected.
            * Thus, robots are created.
            * Afterwards, created robots are added into a list of ArrayBuffer.
            */
            if(i==0){
                var robot = new Robot(target, Array(storageA.toInt,storageB.toInt,storageC.toInt,storageD.toInt,storageE.toInt))
                robotList += robot
            }
        }
        
        val Array(availableA, availableB, availableC, availableD, availableE) = (readLine split " ").map (_.toInt)
        val sampleCount = readLine.toInt
        for(i <- 0 until sampleCount) {
            val Array(_sampleId, _carriedBy, _rank, expertiseGain, _health, _costA, _costB, _costC, _costD, _costE) = readLine split " "
            val sampleId = _sampleId.toInt
            val carriedBy = _carriedBy.toInt
            val rank = _rank.toInt
            val health = _health.toInt
            val costA = _costA.toInt
            val costB = _costB.toInt
            val costC = _costC.toInt
            val costD = _costD.toInt
            val costE = _costE.toInt

            /*< 
            * As initial, samples need to be collected.
            * Thus, robots are created.
            * Afterwards, created samples are added into a list of ArrayBuffer.
            */
            var sample = new Sample(_sampleId.toInt,_carriedBy.toInt,_rank.toInt,expertiseGain.toInt,_health.toInt, Array(_costA.toInt,_costB.toInt,_costC.toInt,_costD.toInt,_costE.toInt))
            sampleList+=sample
            }

            //In order to pick the best sample, a variable of sample is created
            var bestSample:Sample = null
            //max health is initialized
            var maxHealth = 0
            //first item of the list of robots is selectec
            var selectedRobot= robotList(0)

            /*
            * while there are samples in the list 
            * best sample and its health is found
            * by checking every one of those samples            * 
            */
            for (sample <- sampleList){
                if((sample.health>maxHealth)&& sample.carriedBy!=1){
                    bestSample=sample
                    maxHealth=sample.health
                }
            }

        /*
        * If bestsample' carry exists
        * sample goes to diagnosis
        */
            if (bestSample.carriedBy != 0 ){
                goToAndConnect("DIAGNOSIS",bestSample.sampleId,selectedRobot.target)
            }
            
            /*
            * while there is a needed molecule
            * all the molecules are held in an array and checked for the cost
            * of the best samples.
            * If found, then sent to laboratory, otherwise to molecules
            */
            else{
                var neededMolecule:Char = ' '
                for(i <- 0 until moleculeArr.length){
                    if(selectedRobot.storage(i) < bestSample.cost(i))
                        neededMolecule = moleculeArr(i)
                }
                if (neededMolecule != ' ') {
                    goToAndConnect1("MOLECULES",neededMolecule, selectedRobot.target)
                }
                else{
                    goToAndConnect("LABORATORY",bestSample.sampleId,selectedRobot.target)
                }
            }
    }
}
