package com.managemystuff.manage_my_stuff.events;

import android.content.DialogInterface;

import java.util.List;

public class DialogEvent extends BaseUnboundViewEvent {

    private int dialogTitle;
    private int dialogBody;
    private List<Integer> dialogList;
    private DialogInterface.OnClickListener onClickListener;

    private DialogEvent(Object emitter) {
        this.emitter = emitter;
    }

    public static DialogEvent build(Object emitter) {
        return new DialogEvent(emitter);
    }

    public DialogEvent dialogTitle(int dialogTitle) {
        this.dialogTitle = dialogTitle;
        return this;
    }

    public DialogEvent buttonList(List<Integer> dialogList) {
        this.dialogList = dialogList;
        return this;
    }

    public DialogEvent onClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public DialogEvent dialogBody(int dialogBody) {
        this.dialogBody = dialogBody;
        return this;
    }

    public boolean hasList() {
        return dialogList != null && dialogList.size() > 0;
    }

    public boolean hasBody() {
        return dialogBody != 0;
    }

    public int getDialogTitle() {
        return dialogTitle;
    }

    public List<Integer> getDialogList() {
        return dialogList;
    }

    public DialogInterface.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public int getDialogBody() {
        return dialogBody;
    }
}
