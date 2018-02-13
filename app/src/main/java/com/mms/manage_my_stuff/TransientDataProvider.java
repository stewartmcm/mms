package com.mms.manage_my_stuff;

import com.mms.manage_my_stuff.ui.ElementAlreadyExistsException;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

public class TransientDataProvider {

    private ConcurrentMap<String, Object> transientData = new ConcurrentHashMap<>();
    private PublishSubject<String> dataSubject = PublishSubject.create();
    ConcurrentMap<Class, UseCase> transientDataNew = new ConcurrentHashMap<>();
    PublishSubject<Class> dataSubjectNew = PublishSubject.create();

    @Inject
    public TransientDataProvider() {}

    public void save(UseCase useCase) {
        final Class<? extends UseCase> useCaseClass = useCase.getClass();
        if (!transientDataNew.containsKey(useCaseClass)) {
            transientDataNew.put(useCaseClass, useCase);
            dataSubjectNew.onNext(useCaseClass);
        } else {
            throw new ElementAlreadyExistsException("Element of type " + useCaseClass + " already exists");
        }
    }

    public <T extends UseCase> T get(Class<T> useCaseClass) {
        if (constainsUseCase(useCaseClass)) {
            final UseCase removedUseCase = transientDataNew.remove(useCaseClass);
            return useCaseClass.cast(removedUseCase);
        } else {
            throw new NoSuchElementException("Expected element for use case " + useCaseClass.getName());
        }
    }

    public Observable<Class> observeUseCase(Class useCaseClass) {
        return dataSubjectNew.asObservable().filter(clazz -> clazz.equals(useCaseClass));
    }

    public <T extends UseCase> boolean constainsUseCase(Class<T> useCaseClass) {
        return transientDataNew.containsKey(useCaseClass);
    }
}
