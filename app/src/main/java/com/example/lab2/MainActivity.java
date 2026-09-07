package com.example.lab2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "NumbersGamePrefs";
    private static final String KEY_HIGH_SCORE = "high_score";

    private int num1;
    private int num2;
    private int points = 0;
    private int streak = 0;
    private int highScore = 0;

    private TextView leftText;
    private TextView rightText;
    private TextView pointsText;
    private TextView highScoreText;
    private TextView streakText;
    private TextView feedbackText;
    private MaterialCardView cardLeft;
    private MaterialCardView cardRight;
    private MaterialCardView feedbackCard;
    private View buttonReset;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left + dpToPx(24),
                        systemBars.top + dpToPx(16),
                        systemBars.right + dpToPx(24),
                        systemBars.bottom + dpToPx(16));
                return WindowInsetsCompat.CONSUMED;
            });
        }

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        highScore = sharedPreferences.getInt(KEY_HIGH_SCORE, 0);

        initViews();
        updateStatsDisplay();
        roll();
    }

    private void initViews() {
        leftText = findViewById(R.id.buttonLeft);
        rightText = findViewById(R.id.buttonRight);
        pointsText = findViewById(R.id.pointsText);
        highScoreText = findViewById(R.id.textHighScore);
        streakText = findViewById(R.id.textStreak);
        feedbackText = findViewById(R.id.textFeedback);

        cardLeft = findViewById(R.id.cardLeft);
        cardRight = findViewById(R.id.cardRight);
        feedbackCard = findViewById(R.id.cardFeedback);
        buttonReset = findViewById(R.id.buttonReset);

        if (cardLeft != null) {
            cardLeft.setOnClickListener(v -> animateAndCheck(v, num1, num2));
        }
        if (cardRight != null) {
            cardRight.setOnClickListener(v -> animateAndCheck(v, num2, num1));
        }
        if (buttonReset != null) {
            buttonReset.setOnClickListener(v -> resetGame());
        }
    }

    private void animateAndCheck(View view, int selected, int other) {
        if (view != null) {
            view.animate()
                    .scaleX(1.05f)
                    .scaleY(1.05f)
                    .setDuration(100)
                    .withEndAction(() -> view.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(100)
                            .start())
                    .start();
        }
        check(selected, other);
    }

    private void roll() {
        Random r = new Random();
        num1 = r.nextInt(100);
        num2 = r.nextInt(100);
        while (num2 == num1) {
            num2 = r.nextInt(100);
        }

        if (leftText != null) {
            leftText.setText(String.valueOf(num1));
        }
        if (rightText != null) {
            rightText.setText(String.valueOf(num2));
        }
    }

    private void check(int selected, int other) {
        boolean correct = selected > other;
        if (correct) {
            points++;
            streak++;
            if (points > highScore) {
                highScore = points;
                sharedPreferences.edit().putInt(KEY_HIGH_SCORE, highScore).apply();
            }
            showFeedback(true, getString(R.string.feedback_correct));
        } else {
            points--;
            streak = 0;
            showFeedback(false, getString(R.string.feedback_wrong));
        }

        updateStatsDisplay();
        roll();
    }

    private void showFeedback(boolean isCorrect, String message) {
        if (feedbackText != null) {
            feedbackText.setText(message);
            feedbackText.setTextColor(ContextCompat.getColor(this,
                    isCorrect ? R.color.success_color : R.color.error_color));
        }
        if (feedbackCard != null) {
            feedbackCard.setCardBackgroundColor(ContextCompat.getColor(this,
                    isCorrect ? R.color.success_bg : R.color.error_bg));
        }
    }

    private void updateStatsDisplay() {
        if (pointsText != null) {
            pointsText.setText(String.valueOf(points));
        }
        if (highScoreText != null) {
            highScoreText.setText(String.valueOf(highScore));
        }
        if (streakText != null) {
            streakText.setText(streak + " 🔥");
        }
    }

    private void resetGame() {
        points = 0;
        streak = 0;
        if (feedbackText != null) {
            feedbackText.setText(R.string.initial_feedback);
            feedbackText.setTextColor(ContextCompat.getColor(this, R.color.on_surface_variant));
        }
        if (feedbackCard != null) {
            feedbackCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.surface_variant));
        }
        updateStatsDisplay();
        roll();
    }

    public void clickButton1(View view) {
        animateAndCheck(cardLeft, num1, num2);
    }

    public void clickButton2(View view) {
        animateAndCheck(cardRight, num2, num1);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}