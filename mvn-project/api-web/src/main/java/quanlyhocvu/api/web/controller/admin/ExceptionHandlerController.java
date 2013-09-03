package quanlyhocvu.api.web.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
public class ExceptionHandlerController {
	 @ExceptionHandler(IOException.class )
	  public @ResponseBody String handleIOException(IOException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(ConversionNotSupportedException.class)
	  public @ResponseBody String handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler( HttpMediaTypeNotAcceptableException.class)
	  public @ResponseBody String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	  public @ResponseBody String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	  public @ResponseBody String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(HttpMessageNotWritableException.class)
	  public @ResponseBody String handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler( HttpRequestMethodNotSupportedException.class )
	  public @ResponseBody String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(MissingServletRequestParameterException.class)
	  public @ResponseBody String handleIOException(MissingServletRequestParameterException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler( NoSuchRequestHandlingMethodException.class)
	  public @ResponseBody String handleNoSuchRequestHandlingMethodException(NoSuchRequestHandlingMethodException ex, HttpServletRequest request) {
		 System.out.println("404 here");
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 @ExceptionHandler(TypeMismatchException.class
	 )
	  public @ResponseBody String handleTypeMismatchException(TypeMismatchException ex, HttpServletRequest request) {
	    return ClassUtils.getShortName(ex.getClass());
	  }
	 
	 @RequestMapping("/exception")
		public @ResponseBody String exception() {
			throw new IllegalStateException("Sorry!");
		}
         @RequestMapping("/uncaughtException")
		public @ResponseBody String uncaughtException() {
			throw new IllegalStateException("Sorry!");
		}
         
         
	
}
