package com.mms.manage_my_stuff.events;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.PublishSubject;

@Singleton
public class UnboundViewEventBus {

    private final PublishSubject<ToastEvent> toastSubject = PublishSubject.create();
    private final PublishSubject<SnackbarEvent> snackbarSubject = PublishSubject.create();
    private final PublishSubject<StartActivityEvent> startActivitySubject = PublishSubject.create();
    private final PublishSubject<FinishActivityEvent> finishActivitySubject = PublishSubject.create();
    private final PublishSubject<DialogEvent> dialogSubject = PublishSubject.create();

    @Inject
    UnboundViewEventBus() {
    }

    public void send(ToastEvent event) {
        toastSubject.onNext(event);
    }

    public void send(SnackbarEvent event) {
        snackbarSubject.onNext(event);
    }

    public void send(StartActivityEvent event) {
        startActivitySubject.onNext(event);
    }

    public void send(FinishActivityEvent event) {
        finishActivitySubject.onNext(event);
    }

    public void send(DialogEvent event) {
        dialogSubject.onNext(event);
    }

    public Observable<ToastEvent> toast(Object viewModel) {
        return toast(viewModel.getClass());
    }

    public Observable<ToastEvent> toast(final Class viewModelClass) {
        return toastSubject.filter(event -> fromEmitter(event, viewModelClass));
    }

    public Observable<SnackbarEvent> snackbar(Object viewModel) {
        return snackbar(viewModel.getClass());
    }

    public Observable<SnackbarEvent> snackbar(Class viewModelClass) {
        return snackbarSubject.filter(event -> fromEmitter(event, viewModelClass));
    }

    public Observable<StartActivityEvent> startActivity(Object viewModel) {
        return startActivity(viewModel.getClass());
    }

    public Observable<StartActivityEvent> startActivity(Class viewModelClass) {
        return startActivitySubject.filter(event -> fromEmitter(event, viewModelClass));
    }

    public Observable<FinishActivityEvent> finishActivity(Object viewModel) {
        return finishActivity(viewModel.getClass());
    }

    public Observable<FinishActivityEvent> finishActivity(Class viewModelClass) {
        return finishActivitySubject.filter(event -> fromEmitter(event, viewModelClass));
    }

    public Observable<DialogEvent> dialog(Object viewModel) {
        return dialog(viewModel.getClass());
    }

    public Observable<DialogEvent> dialog(Class viewModelClass) {
        return dialogSubject.filter(event -> fromEmitter(event, viewModelClass));
    }

    private boolean fromEmitter(BaseUnboundViewEvent event, Class viewModelClass) {
        return viewModelClass.getName().equals((event).getEmitter().getClass().getName());
    }
}