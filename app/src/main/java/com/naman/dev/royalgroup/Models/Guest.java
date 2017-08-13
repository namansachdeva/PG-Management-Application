package com.naman.dev.royalgroup.Models;

import java.util.ArrayList;
import java.util.Date;

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
                 int guestSecurity, ArrayList<Integer> guestMonthArrayList) {
        this.guestName = guestName;
        this.guestMobileNo = guestMobileNo;
        this.guestPermanentAddress = guestPermanentAddress;
        this.guestCorrespondenceAddress = guestCorrespondenceAddress;
        this.guestStartDate = guestStartDate;
        this.guestRent = guestRent;
        this.guestSecurity = guestSecurity;
        this.guestMonthArrayList = guestMonthArrayList;
    }

}
