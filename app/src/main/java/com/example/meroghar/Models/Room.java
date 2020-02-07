package com.example.meroghar.Models;

public class Room {
    private String room;
    private String noofroom;


    public Room(String room, String noofroom) {
        this.room = room;
        this.noofroom = noofroom;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNoofroom() {
        return noofroom;
    }

    public void setNoofroom(String noofroom) {
        this.noofroom = noofroom;
    }


}
