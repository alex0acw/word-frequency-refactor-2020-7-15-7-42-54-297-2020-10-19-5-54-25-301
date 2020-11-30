import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    public static final String SINGLE_WORD_SUFFIX = " 1";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputSentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (String inputWord : inputSentence.split(SPACE_REGEX)) {
                WordFrequency wordFrequency = new WordFrequency(inputWord, 1);
                wordFrequencyList.add(wordFrequency);
            }

            //sizing the same word
            List<WordFrequency> uniqueWordFrequencyList = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : getWordFrequencyMap(wordFrequencyList).entrySet()) {
                uniqueWordFrequencyList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
            }
            uniqueWordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            StringJoiner resultJoiner = new StringJoiner(NEW_LINE_DELIMITER);
            for (WordFrequency word : uniqueWordFrequencyList) {
                resultJoiner.add(word.getWord() + " " + word.getCount());
            }
            return resultJoiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR_MESSAGE;
        }

    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())) {
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                wordFrequencies.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), wordFrequencies);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequencyMap;
    }
}
