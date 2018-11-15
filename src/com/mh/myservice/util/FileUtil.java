package com.mh.myservice.util;

import java.io.*;

public class FileUtil {

    public static String loadTextOfFile(File file)
    {
        if (!file.exists())
        {
            return "";
        }else
        {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            try
            {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis);
                char[] buffer = new char[2048];
                StringBuffer sb = new StringBuffer();
                int len = 0;
                while((len=isr.read(buffer))!=-1)
                {
                    sb.append(buffer, 0, len);
                }
                String html = sb.toString();
                return html;
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
                return "";
            } catch (IOException e)
            {
                e.printStackTrace();
                return "";
            }finally
            {
                if (isr!=null)
                {
                    try
                    {
                        isr.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
