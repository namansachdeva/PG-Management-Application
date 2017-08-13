package com.naman.dev.royalgroup.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by naman on 8/13/2017.
 */

public class Room {
    public String guest1;
    public String guest2;
    public String guest3;
    public String guest4;
    public int roomCapacity;
    public int roomRemaingSeats;

    public Room(String guest1, String guest2, String guest3, String guest4, int roomCapacity) {
        this.guest1 = guest1;
        this.guest2 = guest2;
        this.guest3 = guest3;
        this.guest4 = guest4;
        this.roomCapacity = roomCapacity;
        this.roomRemaingSeats = 0;
    }

    public Room(String guest1, String guest2, String guest3, int roomCapacity) {
        this.guest1 = guest1;
        this.guest2 = guest2;
        this.guest3 = guest3;
        this.roomCapacity = roomCapacity;
        this.roomRemaingSeats = 0;
    }

    public Room(String guest1, String guest2, int roomCapacity) {
        this.guest1 = guest1;
        this.guest2 = guest2;
        this.roomCapacity = roomCapacity;
        this.roomRemaingSeats = 0;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> room = new HashMap<>();
        room.put("guest1", guest1);
        room.put("guest2", guest2);
        room.put("guest3", guest3);
        room.put("guest4", guest4);
        room.put("roomCapacity", roomCapacity);
        room.put("roomRemainingSeats", roomRemaingSeats);
        return room;
    }

}
