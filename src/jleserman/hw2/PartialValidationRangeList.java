package jleserman.hw2;

/**
 * This code creates RangeList only using odd numbers (which simplifies the problem greatly).
 * If your RangeList class. If you copy this file into your own project, make sure that 
 * there are no extra import statements above, since this is meant to test against YOUR RangeList class.
 * 
 * I created this to give students some ideas as to how to validate that their individual methods 
 * are working as expected.
 */
public class PartialValidationRangeList {
	public static void main(String[] args) {
		jleserman.hw2.RangeList rl = new jleserman.hw2.RangeList();
		jleserman.hw2.RangeList subSetTestMain = new jleserman.hw2.RangeList();
		jleserman.hw2.RangeList subSetTestSubsetT = new jleserman.hw2.RangeList();
		jleserman.hw2.RangeList subSetTestSubsetF = new jleserman.hw2.RangeList();

		subSetTestMain.add(1);
		subSetTestMain.add(2);
		subSetTestMain.add(3);
		subSetTestMain.add(4);
		subSetTestMain.add(8);
		subSetTestMain.add(11);
		subSetTestMain.add(12);

		subSetTestSubsetT.add(2);
		subSetTestSubsetT.add(3);
		subSetTestSubsetT.add(8);
		subSetTestSubsetT.add(11);

		subSetTestSubsetF.add(2);
		subSetTestSubsetF.add(3);
		subSetTestSubsetF.add(5);

		System.out.println("Must be true subSet: " + subSetTestSubsetT.subsetOf(subSetTestMain));
		System.out.println("Must be false subSet: " + subSetTestSubsetF.subsetOf(subSetTestMain));

		rl.add(11);
		System.out.println("Must be 11: " + rl);
		rl.add(7);
		System.out.println("Must be 7,11: " + rl);
		rl.add(3);
		System.out.println("Must be 3,7,11: " + rl);
		
		jleserman.hw2.RangeList rl2 = new RangeList();
		rl2.add(5);
		rl2.add(7);
		rl2.add(3);
		rl2.add(15);
		rl2.add(11);
		System.out.println("Must be 5: " + rl2.numberRanges());
		System.out.println("Must be 5: " + rl2.numberValues());
		System.out.println("Must be true subSet: " + rl.subsetOf(rl2));
		System.out.println("Must be false subSet: " + rl2.subsetOf(rl));
		System.out.println("Must be true: " + rl.contains(11));
		System.out.println("Must be false: " + rl.contains(15));
		
		rl2.remove(15);
		rl2.remove(5);
		System.out.println("Must be true: " + rl.equals(rl2));
		
		// clean up and reduce both RangeLists so they will become equal after removing lots of values
		rl.add(99);
		rl.remove(11);
		rl.remove(3);
		rl.remove(7);
		
		rl2.remove(11);
		rl2.add(99);
		rl2.remove(3);
		rl2.remove(7);
		System.out.println("Must be true: " + rl.equals(rl2));

		jleserman.hw2.RangeList rlA = new RangeList();
		rlA.add(1);
		rlA.add(2);
		rlA.add(3);
		rlA.add(4);
		rlA.add(5);
		rlA.add(6);
		rlA.add(7);
		rlA.add(8);
		rlA.add(9);
		Object p = rlA.toString();
		System.out.println("Must be 1-9: " + p);
		rlA.add(10);
		Object q = rlA.toString();
		System.out.println("Must be 1-9,10 : " + q);
		rlA.remove(3);
		Object g = rlA.toString();
		System.out.println("Must be 1-2, 4-9, 10 : " + g);
		rlA.remove(7);
		Object b = rlA.toString();
		System.out.println("Must be 1-2, 4-8, 8-10 : " + b);
		rlA.add(3);
		Object t = rlA.toString();
		System.out.println(t);
		rlA.add(11);
		Object tt = rlA.toString();
		System.out.println(tt);
		rlA.remove(1);
		Object tttt = rlA.toString();
		System.out.println(tttt);
		rlA.add(1);
		Object ttt = rlA.toString();
		System.out.println(ttt);
		System.out.println("Must be 2: " + rlA.numberRanges());
		Object o =  rlA.numberValues();
		System.out.println("Must be 10: " + o);

	}
}
