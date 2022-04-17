package jleserman.hw3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinearProbingHashST;

import java.sql.SQLSyntaxErrorException;

public class Q4_CompareSymbolTable {

    public static void updateNodeValue(BST_SymbolTable.Node node, int key){
       // BST_SymbolTable.Node = Node; ask about shortcut TODO
        if (node == null) { return; }
        if(node.key == key){
            node.value = node.value + 1;
            return;
        }
        updateNodeValue (node.left, key);
        updateNodeValue (node.right,key);
        return;
    }

    public static void testBST(int N){
        BST_SymbolTable sTable = new BST_SymbolTable();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < N / 2; j++) {
                int randInt = StdRandom.uniform(N*8);
                if(sTable.contains(randInt)){
                    updateNodeValue(sTable.root, randInt); //perhaps return node and add here
                }else {
                    sTable.put(randInt, 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        double timeBST;
        double timeList;
        double timeOA;
        for(int N = 16384; N < 1048576; N=N*2) {
            StopwatchCPU now_BST = new StopwatchCPU();
            testBST(N);
            timeBST = now_BST.elapsedTime();
            System.out.println("N: " + N + " AVG: " + timeBST/N);
        }

    }
}