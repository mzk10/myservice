package com.mh.myservice.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public class Main {

    public static void main(String[] args) {

        try {
            Class<?> clazz = Class.forName("com.mh.myservice.TestClass");
            Method[] methods = clazz.getMethods();
            for (Method meth : methods) {
                String methName = meth.getName();
                if ("check".equals(methName)) {
                    System.out.println(methName);
                    //Type[] genericParameterTypes = meth.getGenericParameterTypes();
                    Parameter[] parameters = meth.getParameters();
                    for (Parameter param : parameters) {
                        String paramName = param.getName();
                        Type parameterizedType = param.getParameterizedType();
                        String typeName = parameterizedType.getTypeName();
                        System.out.println(typeName + ":" + paramName);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

//        System.out.println(isChineseChar('ａ'));//ｂｃ
//        String quanjiao = "@%（）０１２３４５６７８９ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ，．（）＠＃＄％＾＆";


    private static void setPathText(String path) {
        int length = getStringLength(path);
        if (length > 30) {
            String startText = path.substring(0, 10);
            String lastText = new File(path).getName();
            int namelength = getStringLength(lastText);
            if (namelength > 20) {
                char[] chars = lastText.toCharArray();
                namelength = 0;
                StringBuilder sb = new StringBuilder();
                for (int i = chars.length - 1; i >= 0; i--) {
                    char cha2 = chars[i];
                    namelength += isChineseChar(cha2) ? 2 : 1;
                    if (namelength <= 20) {
                        sb.insert(0, cha2);
                        System.out.println(namelength + ":" + sb.toString());
                    } else {
                        break;
                    }
                }
                lastText = sb.toString();
            }
            path = startText + "..." + lastText;
        }
        System.out.println("最后结果:" + path);
    }

    private static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    private static int getStringLength(String text) {
        char[] chars = text.toCharArray();
        int length = chars.length;
        for (char a : chars) {
            if (isChineseChar(a)) {
                length++;
            }
        }
        return length;
    }

    /*private static <T> T getEntityParameter(Class<T> entityClass) {

        Field[] declaredFields = entityClass.getDeclaredFields();
        T instance = null;
        try {
            instance = entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            String typeName = type.getName();
            String fName = field.getName();
            System.out.println(typeName + ":" + fName);
            String param = "123";
            field.setAccessible(true);
            if ("int".equals(typeName)) {
                try {
                    field.setInt(instance, Integer.parseInt(param));
                } catch (Exception e) {
                }
            } else if ("long".equals(typeName)) {
                try {
                    field.setLong(instance, Long.parseLong(param));
                } catch (Exception e) {
                }
            } else if ("boolean".equals(typeName)) {
                try {
                    field.setBoolean(instance, Boolean.parseBoolean("true"));
                } catch (Exception e) {
                }
            } else if ("float".equals(typeName)) {
                try {
                    field.setFloat(instance, Float.parseFloat(param));
                } catch (Exception e) {
                }
            } else if ("double".equals(typeName)) {
                try {
                    field.setDouble(instance, Double.parseDouble(param));
                } catch (Exception e) {
                }
            } else if ("char".equals(typeName)) {
                try {
                    field.setChar(instance, param.charAt(0));
                } catch (Exception e) {
                }
            } else if ("java.lang.Integer".equals(typeName)) {
                try {
                    field.set(instance, new Integer(param));
                } catch (Exception e) {
                }
            } else if ("java.lang.Long".equals(typeName)) {
                try {
                    field.set(instance, new Long(param));
                } catch (Exception e) {
                }
            } else if ("java.lang.Boolean".equals(typeName)) {
                try {
                    field.set(instance, new Boolean(param));
                } catch (Exception e) {
                }
            } else if ("java.lang.Float".equals(typeName)) {
                try {
                    field.set(instance, new Float(param));
                } catch (Exception e) {
                }
            } else if ("java.lang.Double".equals(typeName)) {
                try {
                    field.set(instance, new Double(param));
                } catch (Exception e) {
                }
            } else if ("java.lang.Character".equals(typeName)) {
                try {
                    field.set(instance, param.charAt(0));
                } catch (Exception e) {
                }
            } else if ("java.lang.String".equals(typeName)) {
                try {
                    field.set(instance, param);
                } catch (Exception e) {
                }
            }
        }
        return instance;
    }*/


}
