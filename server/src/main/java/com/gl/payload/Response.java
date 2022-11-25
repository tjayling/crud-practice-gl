package com.gl.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
