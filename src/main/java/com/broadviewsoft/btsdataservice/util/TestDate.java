package com.broadviewsoft.btsdataservice.util;

import java.text.ParseException;
import java.util.Date;

public class TestDate
{

  /**
   * @param args
   * @throws ParseException 
   */
  public static void main(String[] args) throws ParseException
  {
    Date d = new Date((long)1363613400*1000);
    System.out.println("date is " + d);

  }

}
 
