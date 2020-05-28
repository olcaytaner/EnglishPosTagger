package PosTagger;

import Corpus.Sentence;
import org.junit.Test;

import static org.junit.Assert.*;

public class NaivePosTaggerTest {

    @Test
    public void testPosTag() {
        PosTagger posTagger = new NaivePosTagger();
        PosTaggedCorpus posTaggedCorpus = new PosTaggedCorpus("brown.txt");
        posTagger.train(posTaggedCorpus);
        int correct = 0, incorrect = 0;
        for (int i = 0; i < posTaggedCorpus.sentenceCount(); i++){
            Sentence taggedSentence = posTagger.posTag(posTaggedCorpus.getSentence(i));
            for (int j = 0; j < taggedSentence.wordCount(); j++){
                if (((PosTaggedWord) posTaggedCorpus.getSentence(i).getWord(j)).getTag().equals(((PosTaggedWord)taggedSentence.getWord(j)).getTag())){
                    correct++;
                } else {
                    incorrect++;
                }
            }
        }
        assertEquals(93.69, 100 * correct / (correct + incorrect + 0.0), 0.01);
    }
}