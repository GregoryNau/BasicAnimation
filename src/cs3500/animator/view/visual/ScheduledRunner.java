package cs3500.animator.view.visual;

public interface ScheduledRunner {

  void go(Runnable command);

  void stop();

}
