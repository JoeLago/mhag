import java.io.*;
import java.util.*;

public class Mhag implements Runnable
{
   	boolean lowrank;
   	boolean blader;
   	int [] num_list;
    	int [][] slots;
   	String[][] menus;

   public Mhag()
   {
	   lowrank = false;
	   blader = true;
	   num_list = new int[9];
	   slots = new int[9][300];
	   menus = new String[9][300];
	   genMenuList(lowrank, blader, num_list, menus, slots);
   }  

   public void run()
   {
     // mhagInit();
     // mhagProc();
   }

   public void init()
   {
	   mhagInit();
   }


   public static void main(String [] args)
   {
	   Mhag mhag = new Mhag();


//      mhag.run();
   }

   public native void mhagInit();
   public native void mhagProc();
   public native void genMenuList(boolean lowrank, boolean blader, int[] numlist
         ,String[][] menus, int[][]slots);

   static
   {
      System.loadLibrary("Mhag");
   }
}
