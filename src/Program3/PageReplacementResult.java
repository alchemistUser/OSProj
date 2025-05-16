package Program3;

import java.util.List;

public class PageReplacementResult {
    public List<Integer> referenceList;
    public List<List<Integer>> frameTable;
    public List<Boolean> hitList;
    public int pageFaults;

    public PageReplacementResult(List<Integer> referenceList, List<List<Integer>> frameTable, List<Boolean> hitList, int pageFaults) {
        this.referenceList = referenceList;
        this.frameTable = frameTable;
        this.hitList = hitList;
        this.pageFaults = pageFaults;
    }
}
