package com.naman.dev.royalgroup.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by naman on 8/13/2017.
 */

public class Room {

    public int roomCapacity;
    public int roomRemainingSeats;

    public Room(){}

    public Room(int roomCapacity) {
        this.roomCapacity = roomCapacity;
        this.roomRemainingSeats = roomCapacity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> room = new HashMap<>();
        room.put("roomCapacity", roomCapacity);
        room.put("roomRemainingSeats", roomRemainingSeats);
        return room;
    }

}
