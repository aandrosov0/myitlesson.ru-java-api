package ru.myitlesson.api.request;

import ru.myitlesson.api.MyItLessonClient;

public abstract class Request<S> {

    protected MyItLessonClient client;
    protected S service;

    public Request(MyItLessonClient client, S service) {
        this.client = client;
        this.service = service;
    }
}
