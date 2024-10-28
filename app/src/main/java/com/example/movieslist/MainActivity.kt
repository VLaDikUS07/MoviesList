package com.example.movieslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.movieslist.ui.theme.MoviesListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    PreviewMainScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var currentMovieIndex by remember { mutableStateOf(0) }

    val currentMovie = movies[currentMovieIndex]

    MovieScreen(
        movie = currentMovie,
        onPreviousClick = {
            currentMovieIndex = if (currentMovieIndex == 0) {
                movies.size - 1
            } else {
                currentMovieIndex - 1
            }
        },
        onNextClick = {
            currentMovieIndex = if (currentMovieIndex == movies.size - 1) {
                0
            } else {
                currentMovieIndex + 1
            }
        }
    )
}

@Composable
fun MovieScreen(movie: Movie, onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(movie.posterRes),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, Color.White, RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = "Режиссер: ${movie.director} (${movie.year})",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.Gray),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onPreviousClick,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text("Предыдущее")
            }
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text("Следующее")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen(modifier: Modifier = Modifier) {
    MainScreen()
}