/*
 * The MIT License
 *
 * Copyright 2015 Michael Hrcek <hrcekmj@clarkson.edu>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hrcek.core;

/**
 *
 * @author Michael Hrcek <hrcekmj@clarkson.edu>
 */
public class DerpyWriter {

    private volatile Dictionary dictionary;
    private int targetSentencesPerParagraph;
    private boolean useRandom;

    private static boolean ignorePunctuation = false;
    
    public static void setIgnorePunctuation(boolean ignorePunctuation){
        DerpyWriter.ignorePunctuation = ignorePunctuation;
    }
    
    public static boolean ignoresPunctuation(){
        return ignorePunctuation;
    }
    
    public DerpyWriter(Dictionary dictionary) {
        this.dictionary = dictionary;
        targetSentencesPerParagraph = 5;
        useRandom = false;
    }

    public void setTargetSentencesPerParagraph(int targetSentencesPerParagraph) {
        this.targetSentencesPerParagraph = targetSentencesPerParagraph;
    }

    String generateStory(int wordCount) {
        String story = "";
        int sentenceCount = 0;
        Word[] lastWords = new Word[Word.accuracyNumber];

        for (int i = 0; i < lastWords.length; i++) {
            lastWords[i] = LogicFactory.getRandomWord(dictionary);
        }

        for (int i = 0; i < wordCount; i++) {
            if (!useRandom) {
                Word lastWord = LogicFactory.getRandomWord(lastWords, dictionary);

                story += lastWord.getName() + " ";

                if (LogicFactory.isSentenceEnd(lastWord)) {
                    sentenceCount++;
                }

                if (sentenceCount >= targetSentencesPerParagraph) {
                    story += "\n\n";
                    sentenceCount = 0;
                }
            } else {
                Word lastWord = LogicFactory.getRandomWord(dictionary);

                story += lastWord.getName() + " ";

                if (LogicFactory.isSentenceEnd(lastWord)) {
                    sentenceCount++;
                }

                if (sentenceCount >= targetSentencesPerParagraph) {
                    story += "\n\n";
                    sentenceCount = 0;
                }
            }

        }

        return story;
    }

}