public class Mhag implements Runnable
{
/*   public Mhag()
   {
   }*/

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
      mhag.init();

//      mhag.run();
   }

   public native void mhagInit();
   public native void mhagProc();

   static
   {
      System.loadLibrary("Mhag");
   }
}
