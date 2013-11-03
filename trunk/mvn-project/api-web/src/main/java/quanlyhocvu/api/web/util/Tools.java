/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author HuuTri
 */
public class Tools {
    public static User getCurrentUser() {
        Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
        return (User)auth.getPrincipal();
    }
}
