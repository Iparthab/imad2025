package com.example.imadassignment20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imadassignment20.ui.theme.ImadAssignment20Theme

data class Flashcard(val questionText: String, val correctAnswer: Boolean)

val flashcards = listOf(
    Flashcard("Nelson Mandela was the president in 1994.", true),
    Flashcard("The capital of France is Berlin.", false),
    Flashcard("Water boils at 100 degrees Celsius at sea level.", true),
    Flashcard("The Great Wall of China is visible from space with the naked eye.", false),
    Flashcard("There are 7 continents in the world.", true)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImadAssignment20Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizApp()
                }
            }
        }
    }
}

// --- Helper Function ---
fun advanceToNextQuestion(index: Int, total: Int, onAdvance: () -> Unit): Boolean? {
    return if (index + 1 < total) {
        onAdvance()
        true
    } else {
        null
    }
}

@Composable
fun QuizApp() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var feedback by remember { mutableStateOf("") }
    var quizFinished by remember { mutableStateOf(false) }
    var welcomeScreenVisible by remember { mutableStateOf(true) }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFB2FEFA), Color(0xFF0ED2F7))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            welcomeScreenVisible -> {
                WelcomeScreen(onStartClicked = { welcomeScreenVisible = false })
            }
            quizFinished -> {
                ScoreScreen(score, flashcards.size, onGoBack = {
                    // Reset state and return to main menu
                    currentQuestionIndex = 0
                    score = 0
                    feedback = ""
                    quizFinished = false
                    welcomeScreenVisible = true
                })
            }
            else -> {
                val currentFlashcard = flashcards.getOrNull(currentQuestionIndex)
                if (currentFlashcard != null) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Question ${currentQuestionIndex + 1}/${flashcards.size}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                currentFlashcard.questionText,
                                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                                fontWeight = FontWeight.Medium
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Button(
                                    onClick = {
                                        val correct = currentFlashcard.correctAnswer
                                        if (true == correct) score++
                                        feedback = if (true == correct) "✅ Correct!" else "❌ Incorrect. Answer: $correct"
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853))
                                ) {
                                    Text("True")
                                }

                                Button(
                                    onClick = {
                                        val correct = currentFlashcard.correctAnswer
                                        if (false == correct) score++
                                        feedback = if (false == correct) "✅ Correct!" else "❌ Incorrect. Answer: $correct"
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD50000))
                                ) {
                                    Text("False")
                                }
                            }

                            if (feedback.isNotEmpty()) {
                                Text(
                                    feedback,
                                    color = if (feedback.contains("Correct")) Color(0xFF00C853) else Color(0xFFD50000),
                                    fontWeight = FontWeight.SemiBold
                                )

                                Button(
                                    onClick = {
                                        feedback = ""
                                        advanceToNextQuestion(currentQuestionIndex, flashcards.size) {
                                            currentQuestionIndex++
                                        } ?: run { quizFinished = true }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2962FF))
                                ) {
                                    Text("Next")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- Welcome Screen ---
@Composable
fun WelcomeScreen(onStartClicked: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to the Quiz App!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )
            Text(
                "Test your knowledge with a set of True or False flashcards.",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
            Button(
                onClick = onStartClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
            ) {
                Text("Start Quiz", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

// --- Score Screen ---
@Composable
fun ScoreScreen(score: Int, total: Int, onGoBack: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎉 Quiz Finished!", style = MaterialTheme.typography.headlineMedium)
            Text("Your Score: $score / $total", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(getScoreFeedback(score), fontSize = 18.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Review Answers:", style = MaterialTheme.typography.titleMedium)
            flashcards.forEachIndexed { index, card ->
                Text("Q${index + 1}: ${card.questionText} - Answer: ${card.correctAnswer}")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onGoBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0277BD))
            ) {
                Text("Go Back to Main Menu", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

// --- Feedback Logic ---
fun getScoreFeedback(score: Int): String {
    return if (score >= 3) "Great job! 🎯" else "Keep practising! 💪"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImadAssignment20Theme {
        QuizApp()
    }
}
