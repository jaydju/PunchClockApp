package com.example.android.punchclock;

//HOMEBASE ClOCKI
//Exchange Rate - Continuous charging for every additional 5 minute late.

public class Room {
    private String uniqueID;
    private String location;
    private String date;
    private String time;
    private String title;
    private String users;

    public Room(){}

    public Room(String uniqueID, String title, String date, String time, String users, String location){
        this.uniqueID = uniqueID;
        this.title = title;
        this.date = date;
        this.time = time;
        this.users = users;
        this.location = location;

    }

    public String getUniqueID(){
        return uniqueID;
    }

    public String getTitle(){
        return title;
    }

    public String getLocation() {return location;}

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }
    public String getUsers() {return users;}

}
