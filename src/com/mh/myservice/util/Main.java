package com.mh.myservice.util;

import com.mh.myservice.db.dao.VersionCheckDao;
import com.mh.myservice.entity.VersionCheckEntity;

public class Main {

    public static void main(String[] args) {

        VersionCheckDao dao = new VersionCheckDao();
        VersionCheckEntity lastVersion = dao.getLastVersion();
        System.out.println(lastVersion);
    }

}
