package com.example.pronunciationchecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SPEECH_RECOGNITION_CODE = 100;
    private static final int PERMISSION_CODE = 200;

    private TextView sentenceTextView;
    private TextView translationTextView;
    private TextView resultTextView;
    private TextView scoreTextView;
    private Button nextButton;
    private Button previousButton;
    private ImageButton recordButton;
    private ImageButton speakButton;
    private ProgressBar progressBar;
    private TextView progressTextView;

    private TextToSpeech textToSpeech;
    private PronunciationChecker pronunciationChecker;
    private SentenceData sentenceData;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeData();
        initializeTextToSpeech();
        checkPermissions();
        updateUI();
    }

    private void initializeViews() {
        sentenceTextView = findViewById(R.id.sentenceTextView);
        translationTextView = findViewById(R.id.translationTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);
        recordButton = findViewById(R.id.recordButton);
        speakButton = findViewById(R.id.speakButton);
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);

        nextButton.setOnClickListener(v -> nextSentence());
        previousButton.setOnClickListener(v -> previousSentence());
        recordButton.setOnClickListener(v -> startSpeechRecognition());
        speakButton.setOnClickListener(v -> speak());
    }

    private void initializeData() {
        sentenceData = new SentenceData();
        pronunciationChecker = new PronunciationChecker();
    }

    private void initializeTextToSpeech() {
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(new Locale("vi", "VN"));
            }
        });
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        PERMISSION_CODE);
            }
        }
    }

    private void updateUI() {
        SentenceData.Sentence sentence = sentenceData.getSentence(currentIndex);
        sentenceTextView.setText(sentence.getVietnamese());
        translationTextView.setText("(" + sentence.getEnglish() + ")");
        progressBar.setProgress(currentIndex + 1);
        progressTextView.setText((currentIndex + 1) + "/" + sentenceData.getTotalSentences());
        resultTextView.setText("");
        scoreTextView.setText("");

        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < sentenceData.getTotalSentences() - 1);
    }

    private void speak() {
        SentenceData.Sentence sentence = sentenceData.getSentence(currentIndex);
        textToSpeech.speak(sentence.getVietnamese(), TextToSpeech.QUEUE_FLUSH, null);
    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hãy đọc câu sau...");

        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_RECOGNITION_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String recognizedText = result.get(0);
                checkPronunciation(recognizedText);
            }
        }
    }

    private void checkPronunciation(String recognizedText) {
        SentenceData.Sentence sentence = sentenceData.getSentence(currentIndex);
        int score = pronunciationChecker.calculateScore(sentence.getVietnamese(), recognizedText);
        String feedback = pronunciationChecker.getFeedback(score);

        resultTextView.setText("Bạn đọc: " + recognizedText);
        scoreTextView.setText("Điểm: " + score + "%\n" + feedback);
    }

    private void nextSentence() {
        if (currentIndex < sentenceData.getTotalSentences() - 1) {
            currentIndex++;
            updateUI();
        }
    }

    private void previousSentence() {
        if (currentIndex > 0) {
            currentIndex--;
            updateUI();
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
