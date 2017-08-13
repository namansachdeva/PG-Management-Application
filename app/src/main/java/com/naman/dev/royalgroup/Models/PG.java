package com.naman.dev.royalgroup.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by naman on 8/13/2017.
 */

public class PG {
    public String pgAddress;
    public ArrayList<Room> roomArrayList;

    public PG(String pgAddress) {
        this.pgAddress = pgAddress;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> pg = new HashMap<>();
        pg.put("pgAddress", pgAddress);
        pg.put("roomArrayList",roomArrayList);
        return pg;
    }
}
