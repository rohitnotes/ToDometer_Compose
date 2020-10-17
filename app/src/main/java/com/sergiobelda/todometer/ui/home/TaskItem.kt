/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.todometer.ui.home

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sergiobelda.todometer.model.Task
import com.sergiobelda.todometer.model.TaskState
import com.sergiobelda.todometer.sampledata.task1
import com.sergiobelda.todometer.ui.theme.shapes

@Composable
fun TaskItem(
    task: Task,
    updateState: (Int, TaskState) -> Unit,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = shapes.large,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.clickable(
                onClick = {
                    onClick(task.id)
                }
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                when (task.taskState) {
                    TaskState.DOING -> {
                        Text(task.title)
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { updateState(task.id, TaskState.DONE) }
                        ) {
                            Icon(Icons.Rounded.Check)
                        }
                    }
                    TaskState.DONE -> {
                        Text(
                            task.title,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { updateState(task.id, TaskState.DOING) }
                        ) {
                            Icon(Icons.Filled.Refresh)
                        }
                    }
                }
            }
            Text(
                task.description,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                maxLines = 3
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(task = task1, updateState = { _, _ -> }, onClick = {})
}