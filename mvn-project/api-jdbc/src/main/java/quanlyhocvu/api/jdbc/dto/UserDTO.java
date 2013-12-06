/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.jdbc.dto;

import java.util.List;

/**
 *
 * @author HuuTri
 */
public class UserDTO {
    private String username;
    private String password;
    private Integer enabled;
    private List<String> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
    
    @Override
    public String toString() {
        return "User{" + 
                "username=" + username + 
                ", password=" + password + 
                ", enabled=" + enabled.toString();
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
