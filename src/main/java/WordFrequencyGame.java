import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    public static final String SINGLE_WORD_SUFFIX = " 1";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputSentence) {
        if (inputSentence.split(SPACE_REGEX).length == 1) {
            return inputSentence + SINGLE_WORD_SUFFIX;
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] inputWords = inputSentence.split(SPACE_REGEX);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String inputWord : inputWords) {
                    WordFrequency wordFrequency = new WordFrequency(inputWord, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordFrequencyMap = getWordFrequencyMap(wordFrequencyList);

                List<WordFrequency> wordFrequencyList1 = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencyList1.add(wordFrequency);
                }
                wordFrequencyList = wordFrequencyList1;
                wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                StringJoiner resultJoiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordFrequency word : wordFrequencyList) {
                    String s = word.getWord() + " " + word.getCount();
                    resultJoiner.add(s);
                }
                return resultJoiner.toString();
            } catch (Exception exception) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequencies = new ArrayList<>();
                wordFrequencies.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), wordFrequencies);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequencyMap;
    }
}
