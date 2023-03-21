package com.rehan.portfolioapp

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rehan.portfolioapp.ui.theme.PortfolioAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioAppTheme {
                PortfolioApp()
            }
        }
    }
}

@Composable
fun PortfolioApp() {

    val isOpen =
        remember { // remember doesn't lose the state. It keeps remember everytime when we recompose
            mutableStateOf(false)       // Initially we won't show data in lazy column
        }

    // Surface is like canvas where we can draw anything over it or else we can use card view also
    Surface(
        elevation = 16.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            // ImageView
            Image(
                painter = painterResource(id = R.drawable.profile_logo),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            // Giving some height between 2 views
            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
            // TextView
            Text(
                text = "Mohammed Rehan",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.h6,
                color = Color.Magenta
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.github_logo),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "/coderehan",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }   // Row close

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.linkedin_logo),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "/mohammedrehan37",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }   // Row close

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { isOpen.value = !isOpen.value }) {
                Text(
                    text = "Click here",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (isOpen.value) {       // If it is true, we will display data
                LazyColumn {
                    items(getAllProjects()) {
                        // Separate composable view for this lazy column (RecyclerView Vertical)
                        ProjectItem(it)
                    }
                }
            }
        }   // Column close

    }   // Surface close
}

// This is the separate composable view that we are going to show inside lazy column (recyclerview)
@Composable
fun ProjectItem(projects: MyProjects) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_logo),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = projects.name, style = MaterialTheme.typography.h6, color = Color.Black)
            Text(
                text = projects.description,
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
        }
    }

}

fun getAllProjects(): List<MyProjects> {
    return listOf(
        MyProjects(
            name = "To do list app",
            description = "Write your to do task to complete today"
        ),
        MyProjects(name = "Foody app", description = "Delivers food on door"),
        MyProjects(name = "E-Commerce app", description = "Buy the products at your doorstep"),
        MyProjects(name = "Cryptocurrency app", description = "The bitcoin world"),
        MyProjects(name = "Linkedin app", description = "Connect with your connections"),
        MyProjects(
            name = "To do list app",
            description = "Write your to do task to complete today"
        ),
        MyProjects(name = "Foody app", description = "Delivers food on door"),
        MyProjects(name = "E-Commerce app", description = "Buy the products at your doorstep"),
        MyProjects(name = "Cryptocurrency app", description = "The bitcoin world"),
        MyProjects(name = "Linkedin app", description = "Connect with your connections")
    )
}

// Data class to hold name & description for lazy column
data class MyProjects(
    val name: String, val description: String
)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioAppTheme {
        Greeting("Android")
    }
}