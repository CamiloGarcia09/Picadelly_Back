package com.picadelly.usecase;

public interface UseCaseWhitReturn <D, R> {

    R execute(D domain);
}
