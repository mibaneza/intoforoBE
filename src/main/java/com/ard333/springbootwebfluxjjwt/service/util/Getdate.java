package com.ard333.springbootwebfluxjjwt.service.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Getdate {
    private String strDateFormat = "yyyy-MM-dd HH:mm";
    public Date date() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormatter.format(new Date());
        Date dates = null;
        try {
            dates = dateFormatter.parse( formattedDate);
        }catch (ParseException e){
            System.out.println(e);
        }
        return dates;
    }
    public String converter(Date id) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormatter.format(id);
        return formattedDate;
    }

}
