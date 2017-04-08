/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mis;

import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 * My Util class for this soccer project.
 * @author pguan
 */
public class Utils {
    /**
     * Return the setter method name of that field.
     * @param field
     * @return 
     */
    public static String getSetterName(Field field) {
        String s = field.getName();
        String setterString = (new StringBuilder()).append("set").append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        return setterString;
    }
    
    /**
     * 
     * @param field
     * @return
     * @throws Exception 
     */
    public static String getLabelName(Field field) throws Exception {
        Column columnAnnotation = field.getAnnotation(Column.class);
        if(null==columnAnnotation) {
            JoinColumn jc = field.getAnnotation(JoinColumn.class);
            if(null==jc) {
                throw new Exception("Entity Field not annotated" + field.getName());
            } else {
                return null;
            }            
        } else {
            String columnString = columnAnnotation.name();
            if(columnString.equals("crosspass")) {
                return "cross";
            }
            if(columnString.contains("_date")) {
                return "date";
            }
            return columnString;            
        }
    }
}
