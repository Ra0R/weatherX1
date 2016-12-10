package com.mySampleApplication.client;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.core.client.EntryPoint;


/**
 * Created by renato on 22.11.2016.
 */
public class Slider implements EntryPoint{
    public void Slider(){

    }
    public void onModuleLoad(){
        HTML html5slider = new HTML("<input id='slider1' type='range' min='2005' max='1920' step='1' />");
        RootPanel.get("map").add(html5slider);
    }
}
