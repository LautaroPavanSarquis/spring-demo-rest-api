package com.example.demo.Common;

public interface RequestHandler<T extends Request<R>, R> {

    R handler(T request);

    Class<T> getRequestType();
}
