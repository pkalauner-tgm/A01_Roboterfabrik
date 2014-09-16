package tgm.sew.hit.roboterfabrik;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import tgm.sew.hit.roboterfabrik.lager.Lager;
import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Lieferant;

/**
 * Simulation
 * 
 * Hauptklasse Hier werden die Konsolenargumente auf ihre Richtigkeit ueberprueft sowie die Simulation gestartet.
 * 
 * @author Paul Kalauner 4AHITT
 *
 */
public class Simulation {

	private static final Logger LOG = LogManager.getLogger(Simulation.class);

	private static final int MAX_LAUFZEIT = 3600000;
	private static final int MAX_ARBEITER = 1000;

	public static boolean running;

	public static void main(String[] args) {
		if (!checkArgs(args))
			LOG.error("Ungueltige Argumente");
	}

	/**
	 * Setzt das Verzeichnis des Logfiles auf das angegebene
	 * 
	 * @param location
	 */
	private static void configLogger(File location) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		System.setProperty("logFilename", location.getAbsolutePath() + File.separatorChar + "roboterfabrik - " + ldt.format(dtf) + ".log");
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.reconfigure();
		File f = new File("${sys");
		if (f.exists())
			f.delete();
	}

	/**
	 * startet die Simulation
	 * 
	 * @param lieferanten
	 * @param monteure
	 * @param laufzeit
	 * @param lagerVerzeichnis
	 * @param logVerzeichnis
	 */
	private static void start(int lieferanten, int monteure, int laufzeit, File lagerVerzeichnis, File logVerzeichnis) {
		LOG.info("Starte Simulation");
		configLogger(logVerzeichnis);
		running = true;
		Lager lager = new Lager();
		Lagermitarbeiter lm = new Lagermitarbeiter(0, lager, lagerVerzeichnis);
		Sekretariat sekretariat = new Sekretariat(lm, monteure);
		ExecutorService esLieferanten = Executors.newFixedThreadPool(lieferanten);
		Lieferant l = new Lieferant(sekretariat);
		for (int i = 0; i < lieferanten; i++)
			esLieferanten.execute(l);
		
		// Programm nach laufzeit beenden
		new Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				LOG.info("Stoppe Simulation");
				running = false;
			}
		}, laufzeit);
	}

	/**
	 * Ueberprueft die angegebenen Argumente
	 * 
	 * @param args
	 * @return true wenn gueltig
	 */
	@SuppressWarnings("static-access")
	private static boolean checkArgs(String[] args) {
		Options options = new Options();
		options.addOption(OptionBuilder.hasArg(true).isRequired().withDescription("Das Verzeichnis zum Lager").create("lager"));
		options.addOption(OptionBuilder.hasArg(true).isRequired().withDescription("Das Verzeichnis zum Loggen").create("logs"));
		options.addOption(OptionBuilder.hasArg(true).isRequired().withType(Number.class).withDescription("Die Anzahl der Lieferanten").create("lieferanten"));
		options.addOption(OptionBuilder.hasArg(true).isRequired().withType(Number.class).withDescription("Die Anzahl der Monteure").create("monteure"));
		options.addOption(OptionBuilder.hasArg(true).isRequired().withType(Number.class).withDescription("Die Laufzeit der Simulation (in ms)").create("laufzeit"));

		HelpFormatter hf = new HelpFormatter();
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			int lieferanten = ((Number) cmd.getParsedOptionValue("lieferanten")).intValue();
			int monteure = ((Number) cmd.getParsedOptionValue("monteure")).intValue();
			int laufzeit = ((Number) cmd.getParsedOptionValue("laufzeit")).intValue();
			String lagerPfad = cmd.getOptionValue("lager");
			String logPfad = cmd.getOptionValue("logs");
			File lager = new File(lagerPfad);
			File log = new File(logPfad);

			// Zahl der Lieferanten und Monteure muss ueber 0 sein
			if (lieferanten <= 0 || monteure <= 0 || laufzeit <= 0 || lieferanten > MAX_ARBEITER || monteure > MAX_ARBEITER || laufzeit > MAX_LAUFZEIT) {
				System.out.println("Bitte geben Sie gueltige Werte an:");
				System.out.println("Die Anzahl der Lieferanten und Monteure muss zwischen 1 und " + MAX_ARBEITER + " liegen.");
				System.out.println("Die Laufzeit muss zwischen 1 und " + MAX_LAUFZEIT + " liegen.");
				return false;
			}

			if (!lager.exists() || !log.exists()) {
				System.out.println("Das Verzeichnis des Lagers oder des Logs existiert nicht.");
				return false;
			}

			start(lieferanten, monteure, laufzeit, lager, log);
		} catch (ParseException e) {
			System.out.println("Ungueltige Argumente. Beachten Sie, dass alle Argumente erforderlich sind.");
			hf.printHelp("java -jar Roboterfabrik.jar", options);
			return false;
		}
		return true;
	}
}
