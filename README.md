# MLP
A (M)ulti (L)ayer (P)erceptron program for the assignment in the class Connectionist Computing.

TODO:

Create Neuron class
 ! Initialize weights randomly

Create Perceptron class
 ! Can use any number of inputs and nuerons
 ! Need a training function that can take different types of inputs
 ! Also needs to be able to have different numbers of hidden units
 ? Use a matrix? of nuerons to represent the perceptron
 ? Need to figure out a way to connect the nuerons inputs and outputs

Test MLP
 ! Must be able to work an XOR
 ! Randomly create 500 groups of 4 numbers (-1 - 1) 
   and train it to output 'sin(x1-x2+x3-x4)' from 400 groups
   leaving 100 groups for testing

 ! Potentially train the MLP on image recognition
 ! 17 inputs, 26 outputs
 - http://archive.ics.uci.edu/ml/machine-learning-databases/letter-recognition/letter-recognition.data
 - Save 1/5 of examples for testing.
