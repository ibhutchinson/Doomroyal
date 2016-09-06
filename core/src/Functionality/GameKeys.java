package Functionality;

public class GameKeys {
     private static boolean[] keys;
     private static boolean[] pkeys;
     
     private static final int NUM_KEYS = 5;
     public static final int W = 0;
     public static final int S = 1;
     public static final int A = 2;
     public static final int D = 3;
     public static final int SPACE = 4;
     
     static{
    	 keys = new boolean[NUM_KEYS];
    	 pkeys = new boolean[NUM_KEYS];
     }
    /*
     * The method update updates the keys.
     */
     public static void update(){
    	 for(int i=0; i<NUM_KEYS; i++){
    		 pkeys[i] = keys[i];
    	 }
     }
     /*
      * The method setKey sets a new key.
      * 
      * @param int k
      * @param boolean b
      */
     public static void setKey(int k, boolean b){
    	 keys[k] = b;
     }
     /*
      * The method isDown checks if the key is currently down.
      * @param int k
      * @return keys[k]
      */
    public static boolean isDown(int k){
    	return keys[k];
    }
    /*
     * The method isPressed checks if the key was pressed.
     * 
     * @param int k
     * @return keys[k] && !pkeys[k].
     */
    public static boolean isPressed(int k){
    	return keys[k] && !pkeys[k];
    }

}
