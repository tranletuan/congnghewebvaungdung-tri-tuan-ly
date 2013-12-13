/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    
    public static String namHocHienTai(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int nextYear = currentYear + 1;
        return  currentYear + "-" + nextYear;        
    }
    //Chuyen tu dang String 365/12/1992 sang Date 30/12/1992
    public static Date formatStringDateExcel(String date){
        String[] arr = date.split("/"); 
        Date end = new Date(Integer.parseInt(arr[2]), Integer.parseInt(arr[1])-1 , 1);
        Date begin = new Date(Integer.parseInt(arr[2]), 0, 1);
        long diffInMillies = end.getTime() - begin.getTime();
        long dayDiff = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
        Date res = new Date(Integer.parseInt(arr[2]) - 1900, Integer.parseInt(arr[1])-1, (int)(Integer.parseInt(arr[0])- dayDiff));
        return res;
    }
        
    
}
