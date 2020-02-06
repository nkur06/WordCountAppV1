package wordcountapptests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.ankur.config.Configuration;
import com.ankur.processor.design.WordCounter;
import com.ankur.processor.impl.WordCounterImpl;
import com.ankur.util.Utility;

class WordAppTests {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	WordCounter wordCounter = new WordCounterImpl();

	@Test
	void wordCountWhenFilePathIsEmpty_ExpectsFails() {
		exception.expect(IllegalArgumentException.class);
		wordCounter.startCounting("", Configuration.MAX_LINES_PERMITTED_PER_FILE);
	}

	@Test
	void wordCountWhenMaxLinesPermitted_PerFileIs0_ExpectsFails() {
		exception.expect(IllegalArgumentException.class);
		wordCounter.startCounting(Configuration.FILE_PATH, 0);
	}

	@Test
	void wordCountWhenBothArgumentAreValid_ExpectsPass() {
		ExpectedException.none();
		wordCounter.startCounting(Configuration.FILE_PATH, Configuration.MAX_LINES_PERMITTED_PER_FILE);
	}

	@Test
	void wordCountWhenPassingALine_ExpectsSameNoOfWordCountAsPassed() {
		ExpectedException.none();
		Utility utility = new Utility();
		String[] words = utility.extractWordsFromALine("Today is saturday, which is a weekend. Enjoy");
		for (String string : words) {
			System.out.println(string);
		}
		assertEquals(8, words.length);
	}

}
