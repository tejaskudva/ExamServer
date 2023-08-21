package com.exam.logger;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LogWritter {
    
	 public static void WriteToLog(String strOutput)
	  {
	    StringBuffer str = new StringBuffer();
	    str.append(DateFormat.getDateTimeInstance(0, 2).format(new Date()));

	    str.append("\n");
	    str.append(strOutput);
	    str.append("\n");
	    StringBuffer strFilePath = null;
	    String tmpFilePath = "";
            long iFileSize = 50240000;
	    Calendar calendar = new GregorianCalendar();
	    String DtString = String.valueOf("" + calendar.get(5) + (calendar.get(2) + 1) + calendar.get(1));
	    try
	    {
	      strFilePath = new StringBuffer(50);
	      strFilePath.append(System.getProperty("user.dir"));
	      strFilePath.append(File.separatorChar);
	      strFilePath.append("Logs");
	      strFilePath.append(File.separatorChar);	     
	      File test1 = new File(strFilePath.toString());
	      if (!(test1.exists())) {
	        test1.mkdirs();
	      }
              strFilePath.append("Log_" + DtString);
	      tmpFilePath = strFilePath.toString();
              String fileName = tmpFilePath;
              File f1 = new java.io.File(fileName + ".log");
              System.out.println("f1 length > "+f1.length());
              System.out.println("iFileSize > "+iFileSize);
               if (f1.length() >= iFileSize) {
                  int i =0; 
                  File f2 = new File(fileName + "_" + i + ".log");
                  while (true) {
                    f2 = new java.io.File(fileName + "_" + i + ".log");
                    if (!f2.exists()) {
                        for (int j = i - 1; j >= 1; j--) {
                            java.io.File f3 = new java.io.File(fileName + "_" + j + ".log");
                            f3.renameTo(f2);
                            f2 = new java.io.File(fileName + "_" + j + ".log");
                        }
                        break;
                    }
                    i++;
                }
                  f1.renameTo(f2);
               }
	      RandomAccessFile logFile = new RandomAccessFile(f1, "rw");
            if (f1.length() < iFileSize) {
                logFile.seek(logFile.length());
            }
            String str_1 = str.toString();
            logFile.write(str_1.getBytes());
            logFile.close();
	    } catch (Exception exception) {
	    }
	    finally {
	      strFilePath = null;
	    }
	  }
}
