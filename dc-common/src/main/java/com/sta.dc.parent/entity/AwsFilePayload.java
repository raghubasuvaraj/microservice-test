package com.sta.dc.parent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AwsFilePayload {
    private String fileName;
    private Long fileSize;
    private String filePath;
    private byte[] file;

    public AwsFilePayload() {

    }
    public AwsFilePayload(String fileName, Long fileSize, String filePath) {
        super();
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }


}
