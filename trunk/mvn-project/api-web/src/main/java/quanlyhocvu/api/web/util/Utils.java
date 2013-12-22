     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.util;

import org.springframework.security.core.context.SecurityContextHolder;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
public class Utils {

     /**
      * Get current user id
      * @param mongoService 
      * @return 
      */
     public static String getCurrentUserId(MongoService mongoService) {
          String username = SecurityContextHolder.getContext().getAuthentication().getName();
          return mongoService.loadUserByUserName(username).getUserId();
     }
}
