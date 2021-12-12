package day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

    public class Part2 {

        public static void main(String[] args) throws IOException, URISyntaxException {
            day12.Part2 solver = new day12.Part2();
            Path input = Paths.get(Objects.requireNonNull(day9.Part2.class.getResource("/day12.txt")).toURI());
            var list = Files.readAllLines(input);
            Map<String, List<String>> map = new HashMap<>();

            list.stream().map(line -> line.split("-")).forEach(path -> {
                map.computeIfAbsent(path[0], key -> new ArrayList<>()).add(path[1]);
                map.computeIfAbsent(path[1], key -> new ArrayList<>()).add(path[0]);
            });

            List<List<String>> result = solver.findPaths(map, "start", "end", Collections.emptyList(), true);
            System.out.println(result.size());
        }

        private List<List<String>> findPaths(Map<String, List<String>> map, String start, String end, List<String> visited, boolean canDoubleVisitSmallCave) {
            if (start.equals(end)) {
                return Collections.singletonList(Collections.singletonList(start));
            }

            List<String> currentPath = new ArrayList<>(visited);
            currentPath.add(start);

            boolean isSecondSmallCaveVisit = start.toLowerCase().equals(start) && visited.contains(start);

            List<String> visitable = map.get(start).stream()
                    .filter(cave -> !"start".equals(cave))
                    .filter(cave -> cave.toUpperCase().equals(cave) || !visited.contains(cave) || (!isSecondSmallCaveVisit && canDoubleVisitSmallCave))
                    .collect(Collectors.toList());

            List<List<String>> paths = new ArrayList<>();

            visitable.forEach(cave -> paths.addAll(findPaths(map, cave, end, currentPath,
                    !isSecondSmallCaveVisit && canDoubleVisitSmallCave)));

            return paths;
        }

    }
