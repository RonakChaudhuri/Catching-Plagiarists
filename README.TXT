PROJECT: Catching Plagiarists
AUTHOR: Ronak Chaudhuri
DATE: May 22nd, 2020
PROGRAM DESCRIPTION: This program checks the similarity of two documents in a directory of files, and determines the most plagiarized documents in a directory of files.
ALGORITHM DESCRIPTION: This algorithm first reads in a directory of files determined by user input. It then creates an array list of n-word phrases determined by user input of all the files. Then, the hitList method checks the similarity of all the phrases in the arrayLists, which then will be sorted by Comparable interface. Then, it will print the results, with the threshold of hits corresponding to user input.
ALGORITHM COMPLEXITY: O(N^2)
