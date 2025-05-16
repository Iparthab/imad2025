https://youtube.com/shorts/t-UTy2B53As?si=2Q9y8rayC4RGyPzk
1. Purpose of the App
The primary purpose of this app is to provide users with a simple True or False quiz experience using flashcards. It is designed to:

Help users test general knowledge interactively.

Provide immediate feedback on each answer.

Offer a score summary and feedback at the end of the quiz.

Encourage learning through repetition and review of correct answers.

🎨 2. Design Overview
2.1 UI Design
The app uses Jetpack Compose for building the user interface declaratively.

Welcome Screen:

A friendly welcome message introduces the quiz.

A button starts the quiz.

Simple layout using Card and Column with spacing and colors for engagement.

Quiz Screen:

Displays the current question and its number.

Includes True and False buttons with feedback on correctness.

A Next button only appears after answering, preventing skipping.

Score Screen:

Displays the total score and a message based on performance.

Shows a list of all questions and correct answers for review.

Includes a Go Back to Main Menu button to restart the quiz.

2.2 Theming and Aesthetics
Gradient background provides a visually appealing look.

Color-coded buttons (Green for True, Red for False, Blue for navigation).

Typography is used consistently with MaterialTheme styles and font weights.

🧠 3. Functional Features
3.1 State Management
The app uses remember and mutableStateOf for managing UI state:

currentQuestionIndex: Tracks which question is shown.

score: Tracks correct answers.

feedback: Stores correctness message.

quizFinished: Toggles score screen visibility.

welcomeScreenVisible: Controls whether the user sees the welcome screen.

3.2 Navigation Flow
The user starts on the welcome screen.

On tapping "Start Quiz", they are taken to the first question.

After each answer, they get immediate feedback and then move to the next.

Once all questions are answered, the score screen is shown.

The user can return to the welcome screen via a button.

⚙️ 4. Implementation Considerations
4.1 User Experience (UX)
Feedback is essential: the app immediately shows if the user is right or wrong.

Prevents users from clicking "Next" without answering.

A restart option gives users a fresh start after completing the quiz.

4.2 Scalability
Currently uses a static list of flashcards.

Can be extended to support:

Categories or difficulty levels.

Dynamic question loading (from a server or database).

Timer or progress bar for timed quizzes.

4.3 Modularity
The app is structured into reusable composable functions:

QuizApp(): Main controller.

WelcomeScreen()

ScoreScreen()

advanceToNextQuestion() logic separated for clarity.

4.4 Accessibility
Uses clear, high-contrast colors.

Simple layout is easy to navigate and understand.

Font sizes and spacing are appropriate for readabiility 


below are screenshots of the app.
![image](https://github.com/user-attachments/assets/a38b5c56-9462-4e03-8d84-9eef442c8f7a)

![image](https://github.com/user-attachments/assets/5c1820f4-968c-4e37-bd0c-247b010c9b1e)

![image](https://github.com/user-attachments/assets/a6767149-0a38-4c17-bd5c-494212de1b95)
![image](https://github.com/user-attachments/assets/4aee9f45-66cd-4db2-a80e-272899aae4fd)

![image](https://github.com/user-attachments/assets/264f9c85-8a07-490e-afdc-cbbb8f87d0f2)

![image](https://github.com/user-attachments/assets/605bc307-8c9c-409f-818c-eadce8f4c063)




