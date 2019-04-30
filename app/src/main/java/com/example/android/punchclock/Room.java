package com.example.android.punchclock;

//HOMEBASE ClOCKI
//Exchange Rate - Continuous charging for every additional 5 minute late.

public class Room {
    private String uniqueID;
    private String location;
    private String date;
    private String time;
    private String title;

    public Room(){}

    public Room(String uniqueID, String title, String date, String time){
        this.uniqueID = uniqueID;
        this.title = title;
        this.date = date;
        this.time = time;

    }

}
