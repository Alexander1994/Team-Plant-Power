package com.teamplantpower.team_plant_power;
import android.provider.Settings.Secure;
import android.view.View;

/**
 * User Model for firebase: id uniquely identifies phone and is reference to name in dbs.
 * Ex:
 * User -
 *       id : name
 */

public class User {
    private String name;
    private String id;
    private static final String DBUserListKey = "User";

    /**
     * User Constructor, View required for getting context to create unique device Id
     * @param name
     * @param v
     */
    public User(String name, View v) {
        this.name = name;
        this.id = Secure.getString(v.getContext().getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * retrieves names
     * @return String
     */

    public String getName() {
        return name;
    }

    /**
     * retrives unique id to retrieve user from DB
     * @return String
     */
    public String getId() {
        return id;
    }

}
