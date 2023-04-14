package com.bai.servlet;

import java.io.IOException;

public abstract class BaseServlet {
    public void service(Request request, Response response) throws IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doGet(Request request, Response response) throws IOException;

    protected abstract void doPost(Request request, Response response) throws IOException;

}
