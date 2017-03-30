package circle.malabarblood.malabarbloodcircle;

import java.io.Serializable;

/**
 * Created by Mango on 27-03-2017.
 */

public class User {

    public String name;
    public String mobileNo;
    public String bloodGroup;
    public String district;
    public String taluk;
    public String place;
    public String dateOfDonation;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String Name, String usermobile, String bloodGroup, String district, String taluk, String place, String dateOfDonation) {
        this.name = Name;
        this.mobileNo = usermobile;
        this.bloodGroup = bloodGroup;
        this.district = district;
        this.taluk = taluk;
        this.place = place;
        this.dateOfDonation = dateOfDonation;
    }

}
