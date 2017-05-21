package utils;


public class Utils {

    public static void sleep(int sec){
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void print(Object obj, String str) {
        if (str.equals(""))
        System.out.print(obj);
    }
}
