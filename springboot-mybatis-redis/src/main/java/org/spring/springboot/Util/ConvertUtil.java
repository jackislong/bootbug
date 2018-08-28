package org.spring.springboot.Util;

import org.springframework.util.StringUtils;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-22.
 */
public class ConvertUtil {
    public static String  objConverString(Object  object){
        if(object == null){
            return  null;
        }
      return object.toString();
    }
}
