/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.service;

/**
 *
 * @author linhly
 */
public class FunctionService {
    public static int SoHocSinhToiDaMotLop = 10;
    //format date from "day/month/year" to "month/day/year"
    public static String formatStringDate(String date){
        String res = "";
        String[] arr = date.split("/");
        res += arr[1] + "/" + arr[0]+ "/" + arr[2];
        return res;
        
    }
        
    
}
