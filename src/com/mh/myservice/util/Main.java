package com.mh.myservice.util;

import com.mh.myservice.db.dao.BookDao;
import com.mh.myservice.entity.BookEntity;

public class Main {

    public static void main(String[] args) {

        BookEntity entity = new BookEntity();
        entity.setUserId(1);
        entity.setBookName("而写撒旦法");
        BookDao dao = new BookDao();
        dao.add(entity);

    }

}
