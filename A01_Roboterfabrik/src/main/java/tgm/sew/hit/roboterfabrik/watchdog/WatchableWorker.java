package tgm.sew.hit.roboterfabrik.watchdog;

/**
 * WatchableWorker
 * 
 * @author Paul Kalauner 4AHITT
 *
 */
public interface WatchableWorker extends Runnable {
	/**
	 * Stoppt den Worker
	 */
	public void stopWorker();
}
