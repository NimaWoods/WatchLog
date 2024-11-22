# 🎬 WatchLog

WatchLog is an Android app designed to help users keep track of their favorite movies and TV series. With features like a personalized watchlist, tracking of watched content, and quick search functionality, WatchLog makes managing your entertainment simple and fun.

---

## 📱 Features

- **Track Your Favorites:** Keep a watchlist of movies and TV series.
- **Mark Watched Content:** Easily mark movies or episodes as watched.
- **Horizontal Calendar View:** See your planned watchlist by date.
- **Personalized Search:** Find movies and series with a convenient search bar.
- **Dynamic UI:** Toggle between "Movies" and "Series" in a clean and user-friendly interface.
- **Integration with TMDB API:** Fetch movie and TV data directly from The Movie Database (TMDB).

---

## 🛠️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/username/watchlog.git
   ```
2. Open the project in **Android Studio**.
3. Sync the Gradle files.
4. Replace the `API_KEY` placeholder in the API handling code with your TMDB API key:
   ```kotlin
   const val API_KEY = "your_tmdb_api_key_here"
   ```
5. Run the app on your emulator or physical device.

---

## 🌟 Key Screenshots

| Home Page                                      | Watchlist                                  | Search Page                                |
|-----------------------------------------------|-------------------------------------------|-------------------------------------------|
| ![Home Page](https://via.placeholder.com/200) | ![Watchlist](https://via.placeholder.com/200) | ![Search Page](https://via.placeholder.com/200) |

---

## 🗂️ Project Structure

- **`/app/src/main`**
  - `models/`: Data models for the app (e.g., `WatchlistItem`, `CalendarItem`).
  - `ui/`: All app screens (`HomeFragment`, `SearchFragment`, etc.).
  - `adapter/`: RecyclerView adapters for displaying lists.
  - `api/`: API handlers for TMDB API integration.
  - `res/`: Resources including layouts, drawables, and styles.

---

## 🔌 APIs and External Libraries

- **[The Movie Database (TMDB)](https://www.themoviedb.org/)**:
  - Used to fetch data about movies and TV series.
- **OkHttp & Retrofit**:
  - For network requests and REST API handling.
- **Glide**:
  - For loading and displaying images from the internet.
- **Material Components**:
  - For building a modern, clean UI.

---

## 🚀 Contributing

We welcome contributions from the community! To contribute:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message here"
   ```
4. Push the branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Open a Pull Request.

---

## 🛡️ License

This project is licensed under the [MIT License](LICENSE).

---

## 👩‍💻 Author

- **NimaWoods** - [GitHub Profile](https://github.com/username)

---

## 📝 Acknowledgments

- Special thanks to **[TMDB](https://www.themoviedb.org/)** for their amazing API.
- Inspired by the need for a personalized and modern media tracking solution.
