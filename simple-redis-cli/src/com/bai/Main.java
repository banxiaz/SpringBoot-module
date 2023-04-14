package com.bai;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Socket socket;
    static PrintWriter writer;
    static BufferedReader reader;

    public static void main(String[] args) {
        // 1.建立连接
        // 2.获取输入流、输出流
        // 3.发出set name 请求
        // 4.解析响应
        // 5.释放连接

        try {
            String host = "127.0.0.1";
            int port = 6379;
            socket = new Socket(host, port);

            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            sendRequest("set", "name", "虎哥");
            Object obj = handleResponse();
            System.out.println(obj);

            sendRequest("get", "cache:shop:1");
            obj = handleResponse();
            System.out.println(obj);

            sendRequest("mget","name","cache:shop:1");
            obj = handleResponse();
            System.out.println(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //set name 虎哥
    private static void sendRequest(String... args) {
        writer.println("*" + args.length);
        for (String arg : args) {
            writer.println("$" + arg.getBytes(StandardCharsets.UTF_8).length);
            writer.println(arg);
        }

        writer.flush();
        System.out.println("已发送命令 args");
    }

    private static Object handleResponse() throws IOException {
        int prefix = reader.read();
        switch (prefix) {
            case '+':
                return reader.readLine();
            case '-':
                throw new RuntimeException(reader.readLine());
            case ':':
                return Long.parseLong(reader.readLine());
            case '$':  //多行字符串，先读长度，再读数据
                int len = Integer.parseInt(reader.readLine());
                if (len == -1) {
                    return null;
                }
                if (len == 0) {
                    return "";
                }
                return reader.readLine();
            case '*':
                return readerBulkString();
            default:
                throw new RuntimeException("未知的数据格式！");
        }
    }

    private static Object readerBulkString() throws IOException {
        int len = Integer.parseInt(reader.readLine());
        if (len <= 0) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(handleResponse());
        }
        return list;
    }


}
