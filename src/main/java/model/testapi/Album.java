package model.testapi;

import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("id")
    private final int id;

    @SerializedName("userId")
    private final int userId;

    @SerializedName("title")
    private final String title;

    public Album(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }


    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }

}
