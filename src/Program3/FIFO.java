package Program3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FIFO implements PageReplacementAlgorithm {
    private PageReplacementResult result;

    @Override
    public int execute(int[] pages, int frameCount) {
        List<List<Integer>> frameStates = new ArrayList<>();
        List<Integer> refList = new ArrayList<>();
        List<Boolean> hitList = new ArrayList<>();
        Set<Integer> frames = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int faults = 0;

        for (int page : pages) {
            refList.add(page);
            boolean isHit = frames.contains(page);
            if (!isHit) {
                faults++;
                if (frames.size() == frameCount) {
                    int removed = queue.poll();
                    frames.remove(removed);
                }
                frames.add(page);
                queue.offer(page);
            }
            hitList.add(isHit);
            frameStates.add(new ArrayList<>(frames));
        }

        this.result = new PageReplacementResult(refList, frameStates, hitList, faults);
        return faults;
    }

    @Override
    public List<String> getSteps() {
        return null; // not needed anymore
    }

    public PageReplacementResult getResult() {
        return result;
    }
}
