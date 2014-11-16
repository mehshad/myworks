package com.mehshad.puzzlers.collections;

import java.util.TreeSet;

public class TreeSetTest {
	
	public static void main(String[] args) {
		TreeSet<Integer> s = new TreeSet<Integer>();
        TreeSet<Integer> subs = new TreeSet<Integer>();
        for(int i = 606; i < 613; i++){
            s.add(i);
        }
        subs = (TreeSet<Integer>)s.subSet(608, true, 611, true);
        System.out.println("Subs: "+subs);
        System.out.println("Main: "+s);
        s.add(629);
        System.out.println(s + " " + subs);
        
        TreeSet<String> s1 = new TreeSet<String>();
        TreeSet<String> sub1 = new TreeSet<String>();
        
        s1.add("m");
        s1.add("e");
        s1.add("h");
        s1.add("s");
        s1.add("h");
        s1.add("a");
        s1.add("d");
        
        sub1 = (TreeSet<String>) s1.subSet("a", true, "z", true);
        System.out.println("s1 : "+s1);
        System.out.println("sub1 : "+sub1);
        
        TreeSet<String> sub2 = new TreeSet<String>();
        sub2 = (TreeSet<String>) s1.subSet("a", "z");
        
        System.out.println("sub2 : "+sub2);
        
        s1.add("h");
        s1.add("a");
        s1.add("m");
        s1.add("z");
        s1.add("a");
        
        /*sub1.add("z");
        sub1.add("e");
        sub1.add("h");
        sub1.add("n");
        sub1.add("a");
        */
        System.out.println("s1 : "+s1);
        System.out.println("sub1 : "+sub1);
        System.out.println("sub2 : "+sub2);
	}

}
