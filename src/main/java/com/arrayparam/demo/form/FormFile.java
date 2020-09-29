package com.arrayparam.demo.form;

import org.springframework.web.multipart.MultipartFile;

public class FormFile {
    //fileName
    private String fileName;
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*
    //file
    private MultipartFile file;
    public MultipartFile getFile() {
        return this.file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }

     */
}
