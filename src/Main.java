import news.CollectionsOrdonnees;
import news.ObservationThreadsVirtuels;
import news.RecordPatternsEtSwitch;
import news.ThreadsVirtuels;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Découverte de Java " + Runtime.version().feature());
        System.out.println("PID du processus Java : " + ProcessHandle.current().pid());

        if (args.length > 0 && args[0].equals("--observer")) {
            ObservationThreadsVirtuels.executer();
            return;
        }

        CollectionsOrdonnees.executer();
        RecordPatternsEtSwitch.executer();
        ThreadsVirtuels.executer();
    }
}
