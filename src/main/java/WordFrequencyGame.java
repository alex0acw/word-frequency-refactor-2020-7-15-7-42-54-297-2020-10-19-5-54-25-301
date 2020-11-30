import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String WORD_FREQUENCY_ITEM_TEMPLATE = "%s %d";

    public String getResult(String inputSentence) throws CalculateErrorException {
        try {
            List<WordFrequency> uniqueWordFrequencyList = generateWordFrequencyList(inputSentence);
            return generateResultString(uniqueWordFrequencyList);
        } catch (Exception exception) {
            throw new CalculateErrorException();
        }
    }

    private List<WordFrequency> generateWordFrequencyList(String inputSentence) {
        return Arrays.stream(inputSentence.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(stringLongEntry -> new WordFrequency(stringLongEntry.getKey(), Math.toIntExact(stringLongEntry.getValue())))
                .sorted(Comparator.comparing(WordFrequency::getCount).reversed())
                .collect(Collectors.toList());
    }

    private String generateResultString(List<WordFrequency> uniqueWordFrequencyList) {
        return uniqueWordFrequencyList.stream()
                .map(wordFrequency -> String.format(WORD_FREQUENCY_ITEM_TEMPLATE, wordFrequency.getWord(), wordFrequency.getCount()))
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

}
