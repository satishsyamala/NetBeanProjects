/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmscode;


import java.util.Random;

public class AppCode {


    public static int appToken() {
        Random r = new Random();
        int i1 = r.nextInt(999999 - 100000) + 100000;
        return i1;
    }

    public static String getActivationCode(String deviceId, int token, int versionCode) {
        String code = "";
        char[] a = deviceId.toCharArray();
        int sumt = codeSum(token);
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            int v = 0;
            try {
                v = Integer.parseInt(a[i] + "");
            } catch (Exception e) {
                v = a[i];
            }
            if (i % versionCode == 0)
                sum += v;
        }
        String st1 = sumt + "";
        String st2 = sum + "";
        int length = st1.length() > st2.length() ? st1.length() : st2.length();
        for (int i = 0; i < length; i++) {
            if (st1.length() > i)
                code += st1.charAt(i);
            else
                code += "" + versionCode;
            if (st2.length() > i)
                code += st2.charAt(i);
            else
                code += "" + versionCode;
        }
        return code;
    }

    public static int codeSum(int token) {
        int sum = 0;
        while (token > 0) {
            int r = token % 10;
            sum += r;
            token = token / 10;
        }
        return sum;
    }
    
    public static void main(String[] args)
    {
    	int tok=appToken();
    	String code=getActivationCode("c2235abe34e83bd4",746212,1);
    	//String code=getActivationCode("863592053187573",502474,1);
    	System.out.println("Code : "+code);
    }
}
