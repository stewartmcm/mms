package com.mms.manage_my_stuff.ui.boxtype;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.mms.manage_my_stuff.models.Box;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxTypeListViewModel extends AndroidViewModel {

    protected BoxTypeListAdapter boxTypeListAdapter;

    private BoxTypeItemViewModel.Factory boxTypeItemViewModelFactory;
    private List<Box> boxes;
    private List<BoxTypeItemViewModel> boxTypeItemViewModelList = new ArrayList<>();

    @Inject
    public BoxTypeListViewModel(Application application) {
        super(application);
    }

    public List<Box> getBoxTypeList() {
        List<Box> boxList = new ArrayList<>();

        boxList.add(new Box(0, "Dish-pack; Drum; Barrel", new ArrayList<>(), 1, false, false));
        boxList.add(new Box(1, "Carton: 1 1/2 cubic feet", new ArrayList<>(), 2, false, false));
        boxList.add(new Box(2, "Carton: 3 cubic feet", new ArrayList<>(), 3, false, false));
        boxList.add(new Box(3, "Carton: 4 1/2 cubic feet", new ArrayList<>(), 4, false, false));
        boxList.add(new Box(4, "Carton: 6 cubic feet", new ArrayList<>(), 6, false, false));
        boxList.add(new Box(5, "Corrugated Mirror Carton", new ArrayList<>(), 8, false, false));

        return boxList;
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
}
