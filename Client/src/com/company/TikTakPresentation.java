package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class TikTakPresentation {

    private ArrayList<Draw> views=new ArrayList<Draw>();

    public void addPresentation(Draw d){
        views.add(d);
    }

    public void removePresentation(Draw d){
        Iterator<Draw> it=views.iterator();
        while(it.hasNext()){
            if(it.equals(d)){
                views.remove(it);
                break;
            }
        }
    }

    public void redraw(){
        for(Draw d:views){
            d.redraw();
        }
    }
}
