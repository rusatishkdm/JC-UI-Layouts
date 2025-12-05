package com.example.jc_ui_layouts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jc_ui_layouts.ui.theme.JCUILayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCUILayoutsTheme(darkTheme = false, dynamicColor = false) {
                Surface(color = Color.White) {
                    UILayoutScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UILayoutScreen() {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                onClick = {
                    Toast.makeText(context, "FAB Click", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.primary) {
                Text("Bottom App Bar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            ConstraintLayoutSample()
        }
    }
}

@Composable
fun ConstraintLayoutSample() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        val (textTitle1, textValue1, textTitle2, textValue2) = createRefs()

        val viewGuideline = createGuidelineFromStart(0.2f)

        Text(
            text = "Title 1",
            modifier = Modifier
                .constrainAs(textTitle1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(viewGuideline)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = ": 133232323",
            modifier = Modifier.constrainAs(textValue1) {
                top.linkTo(parent.top)
                start.linkTo(textTitle1.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.preferredWrapContent
            }
        )

        val barrier1 = createBottomBarrier(textTitle1, textValue1)

        Text(
            text = "Title 2",
            modifier = Modifier
                .constrainAs(textTitle2) {
                    top.linkTo(barrier1)
                    start.linkTo(parent.start)
                    end.linkTo(viewGuideline)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = ": Satish R",
            modifier = Modifier.constrainAs(textValue2) {
                top.linkTo(barrier1)
                start.linkTo(textTitle2.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UILayoutScreenPreview() {
    JCUILayoutsTheme {
        UILayoutScreen()
    }
}