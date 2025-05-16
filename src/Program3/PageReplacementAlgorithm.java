package Program3;

import java.util.List;

public interface PageReplacementAlgorithm {
    int execute(int[] pages, int frameCount);
    List<String> getSteps();
}