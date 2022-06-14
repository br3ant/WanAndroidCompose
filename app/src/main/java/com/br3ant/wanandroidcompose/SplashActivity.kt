package com.br3ant.wanandroidcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.br3ant.wanandroidcompose.SplashActivity.Companion.LOGO_HEIGHT
import com.br3ant.wanandroidcompose.SplashActivity.Companion.SPLASH_DELAY
import com.br3ant.wanandroidcompose.ui.theme.WanTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WanTheme {
                Splash {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    companion object {
        const val SPLASH_DELAY = 3000L
        const val LOGO_HEIGHT = 100
    }
}


@Composable
fun Splash(gotoHomeScreen: () -> Unit) {

    var start by remember { mutableStateOf(false) }

    val offset by animateDpAsState(
        targetValue = if (start) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val alpha by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = Unit) {
        start = true
        delay(SPLASH_DELAY)
        gotoHomeScreen()
    }

    Splash(offsetState = offset, alphaState = alpha)
}


@Composable
fun Splash(offsetState: Dp, alphaState: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT.dp)
                    .alpha(alpha = alphaState),
                painter = painterResource(id = R.drawable.ic_android_logo_media_social_icon),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Text(
                modifier = Modifier
                    .offset(y = offsetState)
                    .alpha(alpha = alphaState),
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
            )
        }
    }
}
