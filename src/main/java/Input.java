public class Input {
    private final String value;
    private final int count;

    public Input(String w, int i) {
        this.value = w;
        this.count = i;
    }
    public String getValue() {
        return this.value;
    }
    public int getWordCount() {
        return this.count;
    }
}
