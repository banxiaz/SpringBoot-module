package com.bai.servlet;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 对输出添加消息头，上的浏览器可以解析
 */
@Slf4j
@Getter
@Setter
public class Response {
    private OutputStream resp;

    public Response(OutputStream resp) {
        this.resp = resp;
    }

    public void write(String context) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Context-Type: text/html\n")
                .append("\r\n")
                .append(context);
        resp.write(sb.toString().getBytes());
    }
}
