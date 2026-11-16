package news;

import java.util.List;

public final class RecordPatternsEtSwitch {
    private RecordPatternsEtSwitch() {}

    private sealed interface Figure permits Point, Segment {}

    private record Point(int x, int y) implements Figure {}

    private record Segment(Point debut, Point fin) implements Figure {}

    public static void executer() {
        System.out.println("\n2. Record patterns et switch sur les types");
        List<Figure> figures = List.of(
                new Point(0, 0),
                new Point(3, 4),
                new Segment(new Point(1, 2), new Point(5, 6)));

        for (Figure figure : figures) {
            String description = switch (figure) {
                case Point(int x, int y) when x == 0 && y == 0 -> "point à l'origine";
                case Point(int x, int y) -> "point (" + x + ", " + y + ")";
                case Segment(Point(int x1, int y1), Point(int x2, int y2)) ->
                        "segment de (" + x1 + ", " + y1 + ") à (" + x2 + ", " + y2 + ")";
            };
            System.out.println("  " + description);
        }
    }
}
