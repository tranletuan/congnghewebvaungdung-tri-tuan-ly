/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;

/**
 *
 * @author HuuTri
 */
@Aspect
public class HocSinhAOP {

     @Around("execution(* quanlyhocvu.api.mongodb.DAO.HocSinhDAO.*(..))")
     public Object LookTestAOP(ProceedingJoinPoint joinPoint) throws Throwable {
          boolean result = true;
          for (Object obj : joinPoint.getArgs()) {
               if (obj instanceof HocSinhDTO) {
                    if (!checkHocSinhData((HocSinhDTO) obj)) {
                         result = false;
                         break;
                    }
               }
          }
          if (!result) {
               throw new Exception("Sai thông tin học sinh");
          } else {
              return  joinPoint.proceed();
          }
     }

     public boolean checkHocSinhData(HocSinhDTO hocsinh) {
          return AOPRegex.USERNAME.matcher(hocsinh.getmaHocSinh()).matches()
                  && !hocsinh.getdiaChi().isEmpty() && !hocsinh.gethoTen().isEmpty()
                  && !AOPRegex.inRange(hocsinh.getgioiTinh(), 0, 1);
     }
}
