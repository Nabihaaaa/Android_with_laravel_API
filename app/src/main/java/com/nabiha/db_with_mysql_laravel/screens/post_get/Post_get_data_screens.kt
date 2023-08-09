package com.nabiha.db_with_mysql_laravel.screens.post_get

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Post_get_data_screens(
    navcontroller: NavHostController,
    postGetDataModel: PostGetDataModel = viewModel()
) {

    var text by remember {
        mutableStateOf("")
    }

    val data = postGetDataModel.data
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val pullRefreshState = rememberPullRefreshState(
        refreshing = postGetDataModel.refreshing,
        onRefresh = {
            coroutine.launch {
                postGetDataModel.refreshData()
            }
        }
    )

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Box(modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState), contentAlignment = Alignment.Center) {

            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    ) {
                        Text(
                            text = "post Data",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Button(onClick = {

                            coroutine.launch {
                                postGetDataModel.addData(text, context)
                            }

                        }, modifier = Modifier.padding(bottom = 42.dp)) {
                            Text(text = "Upload")
                        }
                        Text(
                            text = "get Data",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleLarge
                        )
                        data.forEach {

                            Log.d("ISI DATA", "Post_get_data_screens: ${it.id}")

                            Row(
                                modifier = Modifier.padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = it.name)
                                Button(
                                    onClick = {
                                        navcontroller.navigate("update_data_screens/${it.id}")
                                    },
                                    modifier = Modifier.padding(start = 12.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "Update",
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                                Button(
                                    onClick = {
                                        coroutine.launch {
                                            postGetDataModel.deleteData(it.id, context)
                                        }
                                    },
                                    modifier = Modifier.padding(start = 12.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(Color.Red)
                                ) {
                                    Text(
                                        text = "Delete",
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                            }

                        }

                    }
                }
            }
            PullRefreshIndicator(
                refreshing = postGetDataModel.refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

    }
}