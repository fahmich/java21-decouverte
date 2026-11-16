package news;

import java.util.List;

public final class CollectionsOrdonnees {
    private CollectionsOrdonnees() {}

    public static void executer() {
        System.out.println("\n1. Collections ordonnées (SequencedCollection)");
        List<String> etapes = List.of("écrire", "compiler", "exécuter");
        System.out.println("Étapes             : " + etapes);
        System.out.println("Première / dernière: " + etapes.getFirst() + " / " + etapes.getLast());
        System.out.println("Vue inversée       : " + etapes.reversed());
    }
}
