package com.ftn.railwayapp.exception;

public class EntityNotFoundException extends AppException {

    public EntityNotFoundException(String entity) {super(String.format("Entity %s is not found.", entity));}
}
