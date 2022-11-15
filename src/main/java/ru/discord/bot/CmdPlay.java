package ru.discord.bot;

import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.ICommand;
import java.net.URI;
import java.net.URISyntaxException;
import net.dv8tion.jda.api.managers.AudioManager;

public class CmdPlay implements ICommand {

  @Override
  public void execute(ExecuteArgs event) {
    if (!event.getMemberVoiceState().inAudioChannel()) {

    }
    if (!event.getSelfVoiceState().inAudioChannel()) {
      final AudioManager audioManager = event.getGuild().getAudioManager();
      audioManager.openAudioConnection(event.getMemberVoiceState().getChannel());
    }
    String link = String.join(" ", event.getArgs());
    if (!isUrl(link)) {
      link = "ytsearch:" + link + " audio";
    }
    PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
  }

  private boolean isUrl(String link) {
    try {
      new URI(link);
      return true;
    } catch (URISyntaxException e) {
      return false;
    }
  }

  @Override
  public String getName() {
    return "play";
  }

  @Override
  public String helpMessage() {
    return "This command is to play music";
  }

  @Override
  public boolean needOwner() {
    return false;
  }
}
