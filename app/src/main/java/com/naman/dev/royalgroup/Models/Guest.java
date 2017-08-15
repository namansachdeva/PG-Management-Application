package com.naman.dev.royalgroup.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by naman on 8/13/2017.
 */

public class Guest {
    public String guestName;
    public String guestMobileNo;
    public String guestPermanentAddress;
    public String guestCorrespondenceAddress;
    public long guestStartDate;
    public String guestPGName;
    public String guestRoomNo;
    public int guestRent;
    public int guestSecurity;
    public boolean stillAGuest;
    public ArrayList<Integer> guestMonthArrayList;

    public Guest() {
    }

    public Guest(String guestName, String guestMobileNo, String guestPermanentAddress,
                 String guestCorrespondenceAddress, long guestStartDate, String guestPGName, String guestRoomNo, boolean stillAGuest) {
        this.guestName = guestName;
        this.guestMobileNo = guestMobileNo;
        this.guestPermanentAddress = guestPermanentAddress;
        this.guestCorrespondenceAddress = guestCorrespondenceAddress;
        this.guestStartDate = guestStartDate;
        this.guestPGName = guestPGName;
        this.guestRoomNo = guestRoomNo;
        this.guestRent = 0;
        this.guestSecurity = 0;
        this.stillAGuest = stillAGuest;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> guest = new HashMap<>();
        guest.put("guestName", guestName);
        guest.put("guestMobileNo", guestMobileNo);
        guest.put("guestPermanentAddress", guestPermanentAddress);
        guest.put("guestCorrespondenceAddress", guestCorrespondenceAddress);
        guest.put("guestStartDate", guestStartDate);
        guest.put("guestPGName", guestPGName);
        guest.put("guestRoomNo", guestRoomNo);
        guest.put("guestRent", guestRent);
        guest.put("guestSecurity", guestSecurity);
        guest.put("stillAGuest", stillAGuest);
        guest.put("guestMonthArrayList", guestMonthArrayList);
        return guest;
    }
}
