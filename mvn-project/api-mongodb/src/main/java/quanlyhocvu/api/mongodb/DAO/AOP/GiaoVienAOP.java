/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.DAO.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;

/**
 *
 * @author HuuTri
 */
@Aspect
public class GiaoVienAOP {

     @Around("execution(* quanlyhocvu.api.mongodb.DAO.GiaoVienDAO.*(..))")
     public Object LookTestAOP(ProceedingJoinPoint joinPoint) throws Throwable {
          boolean result = true;
          for (Object obj : joinPoint.getArgs()) {
               if (obj instanceof GiaoVienDTO) {
                    if (!checkGiaoVienData((GiaoVienDTO) obj)) {
                         result = false;
                         break;
                    }
               }
          }
          if (!result) {
               throw new Exception("Sai thông tin giáo viên");
          } else {
               return joinPoint.proceed();
          }
     }

     public boolean checkGiaoVienData(GiaoVienDTO GiaoVien) {
          return AOPRegex.USERNAME.matcher(GiaoVien.getmaGiaoVien()).matches()
                  && !GiaoVien.getdiaChi().isEmpty() && !GiaoVien.gethoTen().isEmpty()
                  && !AOPRegex.inRange(GiaoVien.getgioiTinh(), 0, 1);
     }
}
