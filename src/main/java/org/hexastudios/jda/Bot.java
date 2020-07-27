package org.hexastudios.jda;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    private Bot() throws LoginException {
        new JDABuilder()
                .setToken(Config.get("TOKEN"))
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("HexaStudios"))
                .build();
    }

    public static void main(String[] args) throws LoginException {

//        JDA api = new JDABuilder("NzM3MjgwOTc0NTU0OTIzMDU4.Xx7Eag.beS16jXzwRJO7tJxa8ytwkDcNyA").build();
//        api.addEventListener(new Listener());
        new Bot();
    }
}
