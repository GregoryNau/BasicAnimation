package cs3500.animator.controller.scheduler;

import cs3500.animator.view.visual.ScheduledRunner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A wrapper class for a {@link ScheduledExecutorService} which allows for more simple
 * method signatures, and easy change of speed.
 */
public class BasicScheduler implements ScheduledRunner {

  private ScheduledExecutorService executorService;
  private float fps;
  private Runnable command;

  /**
   * Constructs a {@link BasicScheduler} with a given frames per second.
   * @param fps the frames per second to run at
   */
  public BasicScheduler(float fps) {
    this.fps = fps;
  }

  @Override
  public void go(Runnable command) {
    this.executorService = Executors.newSingleThreadScheduledExecutor();
    this.command = command;
    this.executorService.scheduleWithFixedDelay(this.command, 0,
        (long)((1 / (double)this.fps) * 1000), TimeUnit.MILLISECONDS);
  }

  @Override
  public void stop() {
    this.executorService.shutdown();
  }

  /**
   * Changes the speed this {@link BasicScheduler} runs at.
   * @param fps the new speed to run.
   */
  public void changeSpeed(float fps) {
    this.stop();
    this.fps = fps;
    this.go(this.command);
  }
}
