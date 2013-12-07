/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.mongodb.utils;

/**
 *
 * @author HuuTri
 */
public enum Authorities {
     STAFF("STAFF"),
     TEACHER("TEACHER"),
     STUDENT("STUDENT"),
     ADMIN("ADMIN");
     
     private Authorities(final String value) {
          this.value = value;
     }
     
     private final String value;
     
     @Override 
     public String toString() {
          return value;
     }
}
