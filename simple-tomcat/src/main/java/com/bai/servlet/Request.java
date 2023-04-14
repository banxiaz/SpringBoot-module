package com.bai.servlet;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 对请求的输入流进行解析
 * InputStream和OutputStream是字节流，Reader和Writer是字符流
 * 带Buffered的是缓冲流，当输入数据时，数据以块为单位读入缓冲区，此后如有读操作，则直接访问缓冲区；当输出数据时，先将数据写入缓冲区，当缓冲区的数据满时，才将缓冲区中的数据写入输出流中，提高了输入/输出的效率。
 * InputStreamReader和OutputStreamWriter是两个桥接流，用于字节流和字符流的相互转换
 */

@Slf4j
@Getter
@Setter
public class Request {
    private String method;
    private String url;

    static {
        log.info("初始化Request类...");
    }

    public Request(InputStream req) {
        try {
            // byte[] bytes = new byte[1024];
            // int len = req.read(bytes);
            // System.out.println(len);
            // System.out.println(new String(bytes, 0, len));

            BufferedReader reqReader = new BufferedReader(new InputStreamReader(req));
            String line = reqReader.readLine();
            log.info("收到消息[{}]", line); // 收到消息[GET /first HTTP/1.1]
            String[] strings = line.split("\\s");
            log.info(Arrays.toString(strings));
            this.method = strings[0];
            this.url = strings[1].split("\\?")[0];


        } catch (Exception e) {
            log.error("Request实例化错误[{}]", e.getMessage());
        }
    }

}
