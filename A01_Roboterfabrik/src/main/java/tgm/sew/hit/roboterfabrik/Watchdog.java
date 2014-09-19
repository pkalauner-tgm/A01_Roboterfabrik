package tgm.sew.hit.roboterfabrik;
import java.util.HashSet;
import java.util.Set;

/**
 * Watchdog
 * 
 * stoppt alle hinzugefuegten Watchables nach einer bestimmten Zeit
 * 
 * @author Paul Kalauner 4AHITT
 *
 */
public class Watchdog implements Runnable {
	private Set<WatchableWorker> watchables;
	private int runtime;

	public Watchdog(int runtime) {
		this.watchables = new HashSet<WatchableWorker>();
		this.runtime = runtime;
	}

	public void addWatchable(WatchableWorker watchable) {
		this.watchables.add(watchable);
	}
	
	public void stopAll() {
		for (WatchableWorker cur : watchables)
			cur.stopWorker();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(this.runtime);
			stopAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
