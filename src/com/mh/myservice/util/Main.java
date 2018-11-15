package com.mh.myservice.util;

import com.mh.myservice.db.dao.UserDao;
import com.mh.myservice.entity.UserEntity;

public class Main {

    public static void main(String[] args) {
        /*String[] val = Constants.IGONE_WORD;
        IgonewordDao dao = new IgonewordDao();
        try {
            for (int i = 0; i < val.length; i++) {
                String word = val[i];
                dao.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }*/


        /*String s = StringUtil.md5("111111");
        System.out.println(s);
        //96E79218965EB72C92A549DD5A330112
        //1BBD886460827015E5D605ED44252251*/

        UserDao dao = new UserDao();
        UserEntity entity = new UserEntity("13604189688", "96E79218965EB72C92A549DD5A330112");
        dao.add(entity);
        dao.close();
    }

}
