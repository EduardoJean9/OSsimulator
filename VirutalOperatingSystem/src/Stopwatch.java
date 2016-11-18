public class Stopwatch {

  private static long startTime = 0;
  private static long stopTime = 0;
  private static boolean running = false;


  public static void start() {
	
    startTime = System.nanoTime();
    running = true;
  }


  public void stop() {
    this.stopTime = System.nanoTime();
    this.running = false;
  }


  //elaspsed time in milliseconds
  public static long getElapsedTime() {
    long elapsed;
    if (running) {
      elapsed = (System.nanoTime() - startTime);
    }
    else {
      elapsed = (stopTime - startTime);
    }
    return elapsed;
  }


  //elaspsed time in milliseconds
  public long getElapsedTimeSecs() {
    long elapsed;
    if (running) {
      elapsed = ((System.nanoTime() - startTime) / 1000);
    }
    else {
      elapsed = ((stopTime - startTime) / 1000);
    }
    return elapsed;
  }
}