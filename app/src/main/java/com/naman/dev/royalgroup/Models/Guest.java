package com.naman.dev.royalgroup.Models;

import java.util.ArrayList;
import java.util.Date;
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
    public Date guestStartDate;
    public int guestRent;
    public int guestSecurity;
    public ArrayList<Integer> guestMonthArrayList;

    public Guest(String guestName, String guestMobileNo, String guestPermanentAddress,
                 String guestCorrespondenceAddress, Date guestStartDate, int guestRent,
                 int guestSecurity) {
        this.guestName = guestName;
        this.guestMobileNo = guestMobileNo;
        this.guestPermanentAddress = guestPermanentAddress;
        this.guestCorrespondenceAddress = guestCorrespondenceAddress;
        this.guestStartDate = guestStartDate;
        this.guestRent = guestRent;
        this.guestSecurity = guestSecurity;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> guest = new HashMap<>();
        guest.put("guestName", guestName);
        guest.put("guestMobileNo", guestMobileNo);
        guest.put("guestPermanentAddress", guestPermanentAddress);
        guest.put("guestCorrespondenceAddress", guestCorrespondenceAddress);
        guest.put("guestRent", guestRent);
        guest.put("guestMonthArrayList", guestMonthArrayList);
        return guest;
    }
}
