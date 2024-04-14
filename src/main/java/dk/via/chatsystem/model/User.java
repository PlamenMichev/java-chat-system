package dk.via.chatsystem.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public String toString()
    {
        return username;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof User))
        {
            return false;
        }

        return username.equals(((User)obj).username);
    }
}
