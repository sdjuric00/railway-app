package com.ftn.railwayapp.exception;

public class EntityAlreadyExistException extends AppException {

    public EntityAlreadyExistException(String entity) {super(String.format("%s already exist.", entity));}
}
