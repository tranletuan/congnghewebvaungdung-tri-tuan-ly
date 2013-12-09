/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;

/**
 *
 * @author HuuTri
 */
@Aspect
public class StaffAOP {

     @Around("execution(* quanlyhocvu.api.mongodb.DAO.StaffDAO.*(..))")
     public void LookTestAOP(ProceedingJoinPoint joinPoint) throws Throwable {
          boolean result = true;
          for (Object obj : joinPoint.getArgs()) {
               if (obj instanceof StaffDTO) {
                    if (!checkStaffData((StaffDTO) obj)) {
                         result = false;
                         break;
                    }
               } 
          }
          if (!result)
               throw new Exception("Sai thông tin nhân viên");
          else 
               joinPoint.proceed();

     }

     public boolean checkStaffData(StaffDTO staff) {
          return AOPRegex.USERNAME.matcher(staff.getManhanvien()).matches()
                  && !staff.getdiaChi().isEmpty() && !staff.gethoTen().isEmpty()
                  && staff.getngaySinh() != null && staff.getngayVaoLam() != null
                  && AOPRegex.inRange(staff.getgioiTinh(), 0, 1);
     }
}
