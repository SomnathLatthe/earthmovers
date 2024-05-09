package com.jaybhavani.earthmovers.utility;

import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class SystemGeneratedDate {

   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public String getDate()
    {
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public String dateFormatter(String inputDateStr)
    {
        String outputDateStr = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr= outputFormat.format(date);
        }
        catch (Exception e)
        {
            System.out.println("Exception in dateFormatter:"+e.getMessage());
        }
        return outputDateStr;
    }
}
