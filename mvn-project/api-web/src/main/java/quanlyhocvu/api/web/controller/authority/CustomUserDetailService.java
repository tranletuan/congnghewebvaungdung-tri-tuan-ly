/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.authority;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import quanlyhocvu.api.mongodb.DTO.Authority.RoleDTO;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
public class CustomUserDetailService implements UserDetailsService {

     @Autowired
     MongoService mongoService;

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          UserDTO user = mongoService.loadUserByUserName(username);
          UserDetails userdetail = new User(user.getUsername(),
                  user.getPassword(),
                  user.isEnabled(),
                  true,
                  true,
                  user.isNonlocked(),
                  getAuthorities(user));
          return userdetail;
     }

     /**
      * Get user Authorities list
      *
      * @param user: UserDTO
      * @return List<GrantedAuthority> object
      */
     public List<GrantedAuthority> getAuthorities(UserDTO user) {
          if (user.getRoles() == null) {
               return null;
          }

          List<GrantedAuthority> result = new ArrayList<>();
          for (RoleDTO role : user.getRoles()) {
               result.add(new SimpleGrantedAuthority(role.getRolename()));
          }
          return result;
     }
}
