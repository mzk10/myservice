package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.entity.FileInfoEntity;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class UploadAction extends Action {

    @Override
    public Object deFault() throws IOException, ServletException {
        RequestDispatcher dispatcher = getRequest().getRequestDispatcher("/WEB-INF/html/upload.html");
        dispatcher.forward(getRequest(), getResponse());
        return null;
    }

    public Object uploadfile() throws IOException {
        try {
            List<FileInfoEntity> fileInfos = uploadFile("upfile", "test");
            return createResponseData(CODE_SUCCESS, fileInfos);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return createResponseData(CODE_SESSION_ERROR);
    }

    public Object testUploadfile() throws IOException {
        try {
            List<FileInfoEntity> fileInfos = uploadFile("upfile2", "test2");
            return createResponseData(CODE_SUCCESS, fileInfos);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return createResponseData(CODE_SESSION_ERROR);
    }

}
