package com.example.kotlinbasics.jetpackcompose

import android.icu.number.Scale
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbasics.jetpackcompose.ui.theme.JetPackComposeTheme
import org.w3c.dom.NameList
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color(0xFF202020), modifier = Modifier.fillMaxSize()) {
                Navigation()
            }
            JetPackComposeTheme {
                // A surface container using the 'background' color from the theme
//                var count by remember {
//                    mutableStateOf(0)
//                }
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = count.toString(),
//                        fontSize = 30.sp
//                    )
//                    Button(onClick = {
//                        count++
//                    }) {
//                        Text(text = "Click Me $count")
//                    }
//                }

                var name by remember {
                    mutableStateOf("")

                }
                var names by remember {
                    mutableStateOf(listOf<String>())
                }


                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                       OutlinedTextField(value = name, onValueChange = {
                           text -> name = text
                       },
                           modifier = Modifier.weight(1f))

                        Spacer(modifier = Modifier.width(20.dp))

                        Button(onClick = {

                            if (name.isNotBlank()){
                                names = names + name
                                name = ""

                            }
                        }) {
                            Text(text = "Add")
                        }
                    }
                    NameList(names)
                }
            }
        }
    }
}

@Composable
fun NameList(names : List<String>,
             modifier: Modifier = Modifier){
    LazyColumn(
        modifier
    ){
        items(names) {
                currentName -> Text(text = currentName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
            Divider()
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SplashScreen"){
        composable("SplashScreen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ){
                Text(text = "Main Screen", color = Color.Cyan)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("main_screen")
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.todo), contentDescription = "Logo"
            , modifier = Modifier.scale(scale.value)
        )
    }
}
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = modifier.fillMaxSize()
//    ) {
//        Text(
//            text = "Hello $name!",
//            color = Color.Blue,
//        modifier = modifier.padding(14.dp)
//            .background(Color.Black)
//        )
//        Text(
//            text = "Hello $name!",
//            color = Color.Blue,
//        modifier = modifier.padding(14.dp)
//            .background(Color.Black)
//        )
//    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground)
//        , contentDescription = null,
//        modifier = Modifier.background(Color.Black)
//    )
//    Icon(imageVector = Icons.Default.Call , contentDescription = null)
//    if (name.length > 5){
//        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
//    }
//    Column {
//        for (i in 1 .. 10){
//            Icon(
//                imageVector = Icons.Default.Star, contentDescription = null
//            )
//        }
//    }
//    Row {
//        for (i in 1 .. 10){
//            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
//        }
//    }
//    LazyColumn(modifier = Modifier.fillMaxSize()){
//        items (10){
//            Icon(
//                imageVector = Icons.Default.AddCircle, contentDescription = null,
//                modifier = Modifier.size(50.dp)
//            )
//        }
//    }
//}