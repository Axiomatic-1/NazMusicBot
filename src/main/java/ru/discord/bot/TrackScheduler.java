package ru.discord.bot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter {

  private final AudioPlayer audioPlayer;
  private final BlockingQueue<AudioTrack> queue;

  public TrackScheduler(AudioPlayer audioPlayer) {
    this.audioPlayer = audioPlayer;
    this.queue = new LinkedBlockingDeque<>();
  }

  public void queue(AudioTrack audioTrack) {
    if (!this.audioPlayer.startTrack(audioTrack, true)) {
      this.queue.offer(audioTrack);
    }
  }

  @Override
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
    if (endReason.mayStartNext) {
      nextTrack();
    }
  }

  private void nextTrack() {
    this.audioPlayer.startTrack(queue.poll(), false);
  }
}
