package org.example.service.handler;

public interface FindById <Entity, Repository>{
    public Entity findByIdHandledWithException(Long id, Repository repository);
}
