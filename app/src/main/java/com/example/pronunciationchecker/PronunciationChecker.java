package com.example.pronunciationchecker;

public class PronunciationChecker {
    
    /**
     * Tính điểm phát âm dựa trên độ tương đồng giữa câu gốc và câu được đọc
     * Sử dụng Levenshtein Distance algorithm
     */
    public int calculateScore(String original, String recognized) {
        if (original == null || recognized == null) {
            return 0;
        }

        // Chuẩn hóa chuỗi
        original = original.toLowerCase().trim();
        recognized = recognized.toLowerCase().trim();

        // Nếu hoàn toàn giống nhau
        if (original.equals(recognized)) {
            return 100;
        }

        // Tính Levenshtein Distance
        int distance = levenshteinDistance(original, recognized);
        int maxLength = Math.max(original.length(), recognized.length());

        // Chuyển đổi distance thành score (0-100%)
        int score = Math.max(0, 100 - (distance * 100 / maxLength));
        return score;
    }

    /**
     * Tính khoảng cách Levenshtein giữa hai chuỗi
     */
    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(
                        dp[i - 1][j] + 1,      // deletion
                        dp[i][j - 1] + 1),    // insertion
                        dp[i - 1][j - 1] + cost  // substitution
                );
            }
        }

        return dp[s1.length()][s2.length()];
    }

    /**
     * Lấy phản hồi dựa trên điểm số
     */
    public String getFeedback(int score) {
        if (score >= 90) {
            return "Xuất sắc! ⭐⭐⭐";
        } else if (score >= 75) {
            return "Rất tốt! ⭐⭐";
        } else if (score >= 60) {
            return "Tốt! ⭐";
        } else if (score >= 40) {
            return "Cần cải thiện 💪";
        } else {
            return "Hãy thử lại 🔄";
        }
    }
}
