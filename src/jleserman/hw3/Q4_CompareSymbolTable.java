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
                    sTable.put(randInt, sTable.get(randInt) + 1); //todo
                }else {
                    sTable.put(randInt, 1);
                }
            }
        }
    }

    public static void testOA(int N){
        LinearProbingHashST oa = new LinearProbingHashST();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < N / 2; j++) {
                int randInt = StdRandom.uniform(N*8);
                if(oa.contains(randInt)){
                    Object val = oa.get(randInt);
                    int count = (Integer) val;
                    oa.put(randInt, count+1); //todo
                }else {
                    oa.put(randInt, 1);
                }
            }
        }
    }

    public static void testChain(int N){
        LinearProbingHashST chain = new LinearProbingHashST();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < N / 2; j++) {
                int randInt = StdRandom.uniform(N*8);
                if(chain.contains(randInt)){
                    Object val = chain.get(randInt);
                    int count = (Integer) val;
                    chain.put(randInt, count + 1); //todo
                }else {
                    chain.put(randInt, 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        double timeBST;
        double timeList;
        double timeOA;
        System.out.println("N \t Avg.BST \t Avg.List \t Avg.OA");
        //OA is linear
        //Chain is List
        for(int N = 16384; N < 1048576; N=N*2) {
            StopwatchCPU now_BST = new StopwatchCPU();
            testBST(N);
            timeBST = now_BST.elapsedTime();

            StopwatchCPU now_Chain = new StopwatchCPU();
            testChain(N);
            timeList = now_Chain.elapsedTime();

            StopwatchCPU now_Linear = new StopwatchCPU();
            testOA(N);
            timeOA = now_Linear.elapsedTime();
            System.out.println(N + "\t" + timeBST + "\t" + timeList + "\t" + timeOA);
        }

    }
}