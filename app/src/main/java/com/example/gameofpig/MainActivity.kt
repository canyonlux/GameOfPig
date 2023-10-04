package com.example.gameofpig

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gameofpig.ui.theme.GameOfPigTheme

class MainActivity : AppCompatActivity() {
    // Declarar las variables para los elementos de la interfaz
    private lateinit var playButton: Button
    private lateinit var passButton: Button
    private lateinit var player1ScoreText: TextView
    private lateinit var player1ThresholdText: TextView
    private lateinit var player2ScoreText: TextView
    private lateinit var player2ThresholdText: TextView
    private lateinit var winnerText: TextView
    private lateinit var restartButton: Button
    private lateinit var player1CurrentScoreText: TextView
    private lateinit var player2CurrentScoreText: TextView




    // Variables para el juego
    private var currentPlayer = 1
    private var player1Score = 0
    private var player2Score = 0
    private val winThreshold = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar elementos de la interfaz y configurar clics de botón
        playButton = findViewById(R.id.playButton)
        passButton = findViewById(R.id.passButton)
        player1ScoreText = findViewById(R.id.player1ScoreText)
        player1ThresholdText = findViewById(R.id.player1ThresholdText)
        player2ScoreText = findViewById(R.id.player2ScoreText)
        player2ThresholdText = findViewById(R.id.player2ThresholdText)
        winnerText = findViewById(R.id.winnerText)
        restartButton = findViewById(R.id.restartButton)
        player1CurrentScoreText = findViewById(R.id.player1CurrentScoreText)
        player2CurrentScoreText = findViewById(R.id.player2CurrentScoreText)


        playButton.setOnClickListener {
            // Lógica para tirar el dado y actualizar puntuaciones
            rollDice()
        }

        passButton.setOnClickListener {
            // Lógica para pasar el turno al otro jugador
            switchPlayer()
        }

        restartButton.setOnClickListener {
            // Lógica para reiniciar el juego
            resetGame()
        }
    }



    // Método para tirar el dado y actualizar puntuaciones
    private fun rollDice() {
        val diceValue =
            (1..6).random() // Simular el lanzamiento de un dado (genera un número aleatorio del 1 al 6)


        if (currentPlayer == 1) {
            player1Score += diceValue // Actualiza la puntuación del jugador 1
            player1ScoreText.text = "Jugador 1: $player1Score"
            player1CurrentScoreText.text = "Tirada actual: $diceValue" // Muestra la puntuación de la tirada actual

        } else {
            player2Score += diceValue // Actualiza la puntuación del jugador 2
            player2ScoreText.text = "Jugador 2: $player2Score"
            player2CurrentScoreText.text = "Tirada actual: $diceValue" // Muestra la puntuación de la tirada actual
        }

        // Verifica si algún jugador ha ganado
        if (player1Score >= winThreshold) {
            winnerText.text = "¡Jugador 1 ha ganado!"
            disableButtons() // Deshabilita los botones después de que se haya declarado un ganador
        } else if (player2Score >= winThreshold) {
            winnerText.text = "¡Jugador 2 ha ganado!"
            disableButtons() // Deshabilita los botones después de que se haya declarado un ganador
        } else {
            // Cambia al siguiente jugador (puedes implementar esta función en `switchPlayer()`)
            switchPlayer()
        }
    }

    private fun disableButtons() {
        playButton.isEnabled = false
        passButton.isEnabled = false
    }

    // Método para cambiar al siguiente jugador
    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 1) 2 else 1 // Cambia al siguiente jugador
        winnerText.text = "" // Borra el mensaje del ganador
        // Actualiza la interfaz gráfica de usuario para mostrar el turno del nuevo jugador
    }

    // Método para reiniciar el juego
    private fun resetGame() {
        player1Score = 0
        player2Score = 0
        currentPlayer = 1
        player1ScoreText.text = "Jugador 1: 0"
        player2ScoreText.text = "Jugador 2: 0"
        winnerText.text = ""
        // Habilita los botones nuevamente
        playButton.isEnabled = true
        passButton.isEnabled = true
    }
    private fun accumulateScore(currentPlayer: Int, diceValue: Int): Int {
        if (currentPlayer == 1) {
            player1Score += diceValue // Actualiza la puntuación del jugador 1
            player1ScoreText.text = "Jugador 1: $player1Score"
        } else {
            player2Score += diceValue // Actualiza la puntuación del jugador 2
            player2ScoreText.text = "Jugador 2: $player2Score"
        }

        return if (currentPlayer == 1) player1Score else player2Score
    }

}










