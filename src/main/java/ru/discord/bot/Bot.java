package ru.discord.bot;

import ca.tristan.jdacommands.JDACommands;
import java.util.Arrays;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot {
  private static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES,
  GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES,
      GatewayIntent.GUILD_EMOJIS};

  public static void main(String[] args) throws LoginException, InterruptedException {
    JDACommands jdaCommands = new JDACommands("!");
    jdaCommands.registerCommand(new CmdPlay());

    JDA jda = JDABuilder.create("5d314339e7ff8bdc78bcf3f0b14038a0cedde5455e219dd3dde46d8882518df6",Arrays.asList(INTENTS))
        .enableCache(CacheFlag.VOICE_STATE)
        .setActivity(Activity.watching("Nazir Player"))
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(jdaCommands)
        .build();

    jda.awaitReady();
  }
}
