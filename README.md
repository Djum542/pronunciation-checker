# Pronunciation Checker - Android App

## 📱 Mô Tả

Ung dụng Android để kiểm tra phát âm tiếng Việt. Người dùng được cung cấp 10 câu mẫu, phải đọc lại và ứng dụng sẽ kiểm tra độ chính xác của phát âm.

## ✨ Tính Năng

✅ **10 Câu Tiếng Việt** - Kèm dịch tiếng Anh
✅ **Text-to-Speech** - Nghe cách đọc chuẩn (tiếng Việt)
✅ **Speech Recognition** - Ghi âm giọng nói người dùng
✅ **Kiểm Tra Phát Âm** - So sánh và tính điểm (0-100%)
✅ **Phản Hồi Tức Thì** - Hiển thị kết quả và nhận xét
✅ **Giao Diện Thân Thiện** - Thiết kế đơn giản, dễ sử dụng

## 🛠️ Công Nghệ

- **Ngôn Ngữ**: Java
- **Framework**: Android
- **API**: Android Speech Recognition, TextToSpeech
- **Target SDK**: Android 13 (API 33)
- **Min SDK**: Android 5.0 (API 21)

## 📝 Các Câu Mẫu

1. "Xin chào, tôi là bạn của bạn"
2. "Hôm nay thời tiết rất đẹp"
3. "Tôi thích học tiếng Anh"
4. "Bạn khỏe không?"
5. "Tôi sống ở thành phố Hồ Chí Minh"
6. "Công việc của tôi rất thú vị"
7. "Tối nay tôi sẽ đi xem phim"
8. "Bạn có muốn uống cà phê không?"
9. "Tôi rất vui gặp bạn lại"
10. "Chúc bạn một ngày tốt lành"

## 🚀 Cách Sử Dụng

### Cài Đặt

1. Clone repository:
   ```bash
   git clone https://github.com/Djum542/pronunciation-checker.git
   ```

2. Mở trong Android Studio

3. Build project:
   ```bash
   ./gradlew build
   ```

4. Chạy trên thiết bị/emulator:
   ```bash
   ./gradlew installDebug
   ```

### Quyền Cần Thiết

- `RECORD_AUDIO` - Để ghi âm giọng nói
- `INTERNET` - Cho Google Speech Recognition API

## 📊 Cách Hoạt Động

### 1. Hiển Thị Câu
- Hiển thị câu tiếng Việt cần đọc
- Kèm dịch tiếng Anh
- Nút "Phát Âm" để nghe cách đọc chuẩn

### 2. Ghi Âm
- Bấm nút "Ghi Âm" (Mic)
- Đọc câu vào microphone
- Ứng dụng nhận dạng giọng nói (Google Speech Recognition)

### 3. Kiểm Tra Phát Âm
- Sử dụng **Levenshtein Distance Algorithm**
- So sánh câu gốc với câu được đọc
- Tính điểm từ 0-100%

### 4. Phản Hồi
- **90-100%**: "Xuất sắc! ⭐⭐⭐"
- **75-89%**: "Rất tốt! ⭐⭐"
- **60-74%**: "Tốt! ⭐"
- **40-59%**: "Cần cải thiện 💪"
- **Dưới 40%**: "Hãy thử lại 🔄"

## 📁 Cấu Trúc Dự Án

```
app/src/main/
├── java/com/example/pronunciationchecker/
│   ├── MainActivity.java              # Giao diện chính
│   ├── SentenceData.java              # 10 câu mẫu
│   └── PronunciationChecker.java      # Logic kiểm tra phát âm
├── res/
│   ├── layout/activity_main.xml       # XML Layout
│   ├── values/
│   │   ├── colors.xml                 # Màu sắc
│   │   ├── strings.xml                # Chuỗi văn bản
│   │   └── styles.xml                 # Kiểu dáng
│   └── drawable/                      # Hình ảnh nút
└── AndroidManifest.xml                # Manifest
```

## 🔧 Dependencies

```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.9.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
```

## 📄 License

MIT License

## 👨‍💻 Tác Giả

Djum542

---

**Phát triển bởi Copilot** 🤖
