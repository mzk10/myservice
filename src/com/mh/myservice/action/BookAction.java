package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.BookDao;
import com.mh.myservice.entity.BookEntity;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.util.StringUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookAction extends Action {

    @Override
    public Object deFault() throws ServletException, IOException, SQLException {
        return null;
    }

    public Object listBook() {
        if (checkUser()) {
            String userid = getParameter("userid");
            BookDao dao = new BookDao();
            List<BookEntity> booklist = dao.listBook(userid);
            return ResponseData.create(200, booklist);
        }
        return ResponseData.create(210, null);
    }

    public Object addBook() {
        if (checkUser()) {
            BookEntity entity = getEntityParameter(BookEntity.class);
            if (entity != null && !StringUtil.isEmpty(entity.getBookName())) {
                BookDao dao = new BookDao();
                boolean checkBookName = dao.checkBookName(entity.getUserid(), entity.getBookName());
                if (checkBookName) {
                    return ResponseData.create(600);
                }
                String bookid = dao.add(entity);
                entity.setId(bookid);
                return ResponseData.create(200, entity);
            }else {
                return ResponseData.create(100);
            }
        }
        return ResponseData.create(210);
    }

}
