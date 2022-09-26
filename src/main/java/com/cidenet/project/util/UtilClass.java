package com.cidenet.project.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class UtilClass {

    public static boolean checkDate(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ld = LocalDate.parse(date, formatter);
            boolean flag = ld.isBefore(LocalDate.now().minusMonths(1))? true : false;
            return flag;
        }catch(Exception e){
            return true;
        }
    }

    public static Date setDateAdm(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        java.util.Date date = format.parse(dateStr);

        return new Date(date.getTime());
    }


}
