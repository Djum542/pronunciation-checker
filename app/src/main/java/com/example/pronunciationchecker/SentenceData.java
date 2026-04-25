package com.example.pronunciationchecker;

import java.util.ArrayList;
import java.util.List;

public class SentenceData {
    public static class Sentence {
        private String vietnamese;
        private String english;

        public Sentence(String vietnamese, String english) {
            this.vietnamese = vietnamese;
            this.english = english;
        }

        public String getVietnamese() {
            return vietnamese;
        }

        public String getEnglish() {
            return english;
        }
    }

    private List<Sentence> sentences;

    public SentenceData() {
        sentences = new ArrayList<>();
        initializeSentences();
    }

    private void initializeSentences() {
        sentences.add(new Sentence("Xin chào, tôi là bạn của bạn", "Hello, I am your friend"));
        sentences.add(new Sentence("Hôm nay thời tiết rất đẹp", "The weather is very nice today"));
        sentences.add(new Sentence("Tôi thích học tiếng Anh", "I like learning English"));
        sentences.add(new Sentence("Bạn khỏe không?", "How are you?"));
        sentences.add(new Sentence("Tôi sống ở thành phố Hồ Chí Minh", "I live in Ho Chi Minh City"));
        sentences.add(new Sentence("Công việc của tôi rất thú vị", "My job is very interesting"));
        sentences.add(new Sentence("Tối nay tôi sẽ đi xem phim", "I will go to the movies tonight"));
        sentences.add(new Sentence("Bạn có muốn uống cà phê không?", "Would you like to drink coffee?"));
        sentences.add(new Sentence("Tôi rất vui gặp bạn lại", "I am very happy to see you again"));
        sentences.add(new Sentence("Chúc bạn một ngày tốt lành", "Wish you a wonderful day"));
    }

    public Sentence getSentence(int index) {
        if (index >= 0 && index < sentences.size()) {
            return sentences.get(index);
        }
        return sentences.get(0);
    }

    public int getTotalSentences() {
        return sentences.size();
    }
}
