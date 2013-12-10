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
public class Data {

     public static String checkUserDTO(String username, String password, String password_confirm) {
          if (username.isEmpty()) {
               return "Chưa nhập thông tin tên người dùng";
          } else if (password.isEmpty()) {
               return "Chưa nhập thông tin mật khẩu ";
          } else if (!password.equals(password_confirm)) {
               return "Mật khẩu xác nhận không chính xác";
          }
          return "";
     }
}
