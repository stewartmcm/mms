package com.mms.manage_my_stuff.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.mms.manage_my_stuff.BaseLifeCycleViewModel;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxTypeListViewModel extends BaseLifeCycleViewModel {

    protected BoxTypeListAdapter boxTypeListAdapter;
    protected UnboundViewEventBus eventBus;

    private BoxTypeItemViewModel.Factory boxTypeItemViewModelFactory;
    private List<Box> boxes;
    private List<BoxTypeItemViewModel> boxTypeItemViewModelList = new ArrayList<>();

    @Inject
    public BoxTypeListViewModel(UnboundViewEventBus eventBus,
                                BoxTypeItemViewModel.Factory boxTypeItemViewModelFactory) {
        this.eventBus = eventBus;
//        this. = ;
        this.boxTypeItemViewModelFactory = boxTypeItemViewModelFactory;
    }

    public BoxTypeListAdapter getBoxTypeListAdapter() {
        return new BoxTypeListAdapter(this);
    }

    public List<BoxTypeItemViewModel> getBoxTypeList() {
        List<BoxTypeItemViewModel> boxSelectionItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxSelectionItemViewModelList.add(new BoxTypeItemViewModel("Box type " + i, eventBus));
        }

        return boxSelectionItemViewModelList;
    }

    public void onItemSelected() {
        StartActivityEvent event = StartActivityEvent.build(this).activityName(BoxDetailsActivity.class);
        eventBus.send(event);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateBoxTypeList(List<Box> boxes) {
        this.boxes = boxes;
        boxTypeItemViewModelList.clear();

        for (Box box : boxes) {
            boxTypeItemViewModelList.add(boxTypeItemViewModelFactory.newInstance(box.getTitle()));
        }

        boxTypeListAdapter.notifyDataSetChanged();
    }

//    public static class Factory {
//
//        private final UnboundViewEventBus eventBus;
//
//        @Inject
//            this.eventBus = eventBus;
//            this. = ;
//        }
//
//        public BoxTypeListViewModel newInstance(Box[] boxes) {
//            return new BoxTypeListViewModel(eventBus, , bo);
//        }
//
//    }
}
