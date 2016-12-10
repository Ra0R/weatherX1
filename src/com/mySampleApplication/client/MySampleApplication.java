package com.mySampleApplication.client;
import com.google.gwt.core.client.*;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import java.util.ArrayList;




/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {
    private MySampleApplicationServiceAsync dataservice = GWT.create(MySampleApplicationService.class);
    final FlexTable weatherDataTable = new FlexTable(); //Tabelle mit Wetterdaten
    private TextBox currentYear = new TextBox();
    final Hyperlink quelle = new Hyperlink("Copyright K. Meier","http://googol.com") ; //Quelle
    final Hyperlink quelle1 = new Hyperlink("Copyright K. Meier","http://googol.com") ; //Apparenty we cant add same widget twice
    final ListBox filterList = new ListBox();
    final TextBox filterText = new TextBox();
    final Button filterButton = new Button("Filter", new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            String query = filterText.getText();

            int index = filterList.getSelectedIndex();


        filterEntries(query, index);

        }
    });


    private Label debugLabel = new Label("");

    Frame mapframe = new Frame("map.html");

    private Button refreshB = new Button("Refresh", new ClickHandler() {
        public void onClick(ClickEvent event) {
            String year = currentYear.getElement().getInnerText();


          // Window.Location.replace(Window.Location.getPath()+"?year="+ year);
            mapframe.removeFromParent();
            mapframe.setUrl("/map.html?year="+currentYear.getElement().getInnerText());
            RootPanel.get("map").add(mapframe);
            RootPanel.get("map").add(quelle);
          }
    });
    /**
     * This is the entry point method.
     */



    //Queries Entries for the WeatherDataTable
    public void queryEntries(){

        if (dataservice == null) {
            dataservice = GWT.create(MySampleApplicationService.class);
        }
        // Set up the callback object.
        AsyncCallback<ArrayList<String[]>> callback = new AsyncCallback<ArrayList<String[]>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error while requesting table entries");
                debugLabel.setText( caught.toString());

            }

            public void onSuccess(ArrayList<String[]> result){
                for(String[] curInstance: result){
                    int row = weatherDataTable.getRowCount();
                    for(int i=0;i<7;i++){
                        weatherDataTable.setText(row, i, curInstance[i]);

                    }
                }
            }
        };
        dataservice.getEntries("2005",callback);
    }

    public void filterEntries(String query, int index){

        if (dataservice == null) {
            dataservice = GWT.create(MySampleApplicationService.class);
        }
        // Set up the callback object.
        AsyncCallback<ArrayList<String[]>> callback = new AsyncCallback<ArrayList<String[]>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error while requesting filtering");
                    debugLabel.setText(caught.toString());
            }
            public void onSuccess(ArrayList<String[]> result){
                weatherDataTable.removeAllRows();
                for(String[] curInstance: result){
                    addTableColumns();
                    int row = weatherDataTable.getRowCount();

                    for(int i=0;i<7;i++){
                        weatherDataTable.setText(row, i, curInstance[i]);

                    }
                }
            }
        };
        dataservice.filterEntries(query,index,callback);
    }

    //Helper method to pass Java object to java script


    public void addTableColumns(){
        //Wettertabellenspalten werden angelegt
        weatherDataTable.setText(0, 0, "Date");
        weatherDataTable.setText(0, 1, "AverageTemperature");
        weatherDataTable.setText(0, 2, "AverageTemperatureUncertainty");
        weatherDataTable.setText(0, 3, "City");
        weatherDataTable.setText(0, 4, "Country");
        weatherDataTable.setText(0, 5, "Latitude");
        weatherDataTable.setText(0, 6, "Longitude");
    }





    public void onModuleLoad() {

        mapframe.setPixelSize(800,300);

        RootPanel.get().add(debugLabel);
       //Set IDs to access with .css
        weatherDataTable.getElement().setId("WeatherTable");
        quelle.getElement().setId("Source");
        mapframe.getElement().setId("Mapframe");
        refreshB.getElement().setId("refreshB");
        filterList.getElement().setId("selector");
        //FilterListe
        Window.alert("Buttons!");
        filterList.addItem("Date");
        filterList.addItem("AverageTemperature");
        filterList.addItem("AverageTemperatureUncertainty");
        filterList.addItem("City");
        filterList.addItem("Country");
        filterList.addItem("Latitude");
        filterList.addItem("Longitude");

        RootPanel.get("table").add(filterList);
        Window.alert("list!");
        RootPanel.get("table").add(filterText);
        Window.alert("filtertext!");
        RootPanel.get("table").add(filterButton);
        Window.alert("filterbutton!");

        //Tabelle wird dargestellt
        addTableColumns();
        RootPanel.get("table").add(weatherDataTable);
      ;
        //Quellenangabe

        FlowPanel panel = new FlowPanel();

       RootPanel.get("map").add(mapframe);


        // Slider
        Window.alert("SLIDER!");
       HTML html5slider = new HTML("1947 <input id='slider1' type='range' min='1946' max='2013' step='1' onchange='showVal(this.value)'> 2013");
        RootPanel.get("map").add(html5slider);
        RootPanel.get("map").add(currentYear); // not loaded

        currentYear.setText("1990");
        currentYear.getElement().setId("currentYear");
        currentYear.setVisible(false);
        RootPanel.get("map").add(refreshB);
        RootPanel.get("map").add(panel);
        panel.add(mapframe);
        RootPanel.get("map").add(quelle);

        queryEntries();
    }

  // public abstract class GWTBridge extends com.google.gwt.core.shared.GWTBridge {
   // }
}

