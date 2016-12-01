public class Stopwatch {
	
	public Stopwatch stopwatch = new Stopwatch();

	private static long startTime = 0;
	private static long stopTime = 0;
	private static boolean running = false;

	public static void start() {
		startTime = System.nanoTime();
		running = true;
	} 

	
	public static void stop() {
		Stopwatch.stopTime = System.nanoTime();
		Stopwatch.running = false;
	}

	public static void jobStart() {
		startTime = System.nanoTime();
		running = true;
	}

	public void jobStop() {
		Stopwatch.stopTime = System.nanoTime();
		Stopwatch.running = false;
	}

	public void reset() {
		Stopwatch.stopTime = 0;
		Stopwatch.startTime = 0;
	}
	// elaspsed time in milliseconds
	public static long getElapsedTime() {
		long elapsed;
		if (running) {
			elapsed = (System.nanoTime() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	// elapsed time in milliseconds
	public static long getElapsedTimeSecs() {
		long elapsed;
		if (running) {
			elapsed = ((System.nanoTime() - startTime));
		} else {
			elapsed = ((stopTime - startTime));
		}
		return elapsed;
	}

	public long getJobTimeMil() {
		long elapsed;
		if (running) {
			elapsed = ((System.nanoTime() - startTime));
		} else {
			elapsed = ((stopTime - startTime));
		}
		return elapsed;
	}
}