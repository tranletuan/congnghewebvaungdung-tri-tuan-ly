/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.DAO.AOP;

import java.util.regex.Pattern;

/**
 *
 * @author HuuTri
 */
public class AOPRegex {
     public static final Pattern USERNAME = Pattern.compile("^[a-z0-9_-]{3,30}$");
     
     public static boolean inRange(int data, int min, int max) {
          return (data >= min && data <= max);
     }
} 
