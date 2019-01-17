package com.br.startmeup.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    private static String pattern = "yyyy-MM-dd HH:mm";

    public static String parseDateToString(Date date){
        String result = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            result = dateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
