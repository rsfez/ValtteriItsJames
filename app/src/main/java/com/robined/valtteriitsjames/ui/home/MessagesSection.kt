package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.ds.Spacing.small

@Composable
internal fun MessagesSection(messages: SnapshotStateList<Message>) {
    LazyColumn(
        modifier = Modifier
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .fillMaxWidth()
    ) {
        items(messages.size) { index ->
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .padding(small)
                    .fillMaxWidth()
            ) {
                val message = messages[index]
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(small)
                    ) {
                        Message.Type.entries.forEach { type ->
                            val selected = message.type == type
                            MessageType(
                                type = type,
                                selected = selected,
                                onClick = { messages[index] = message.copy(type = type) })
                        }
                    }
                    Button(
                        onClick = { messages.removeAt(index) }) {
                        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Delete",
                            )
                        }
                    }
                }
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = message.getNotFormatted(),
                    onValueChange = { messages[index] = message.copy(content = it) }
                )
            }
            Box(modifier = Modifier.height(small))
        }
        item {
            TextButton(
                onClick = { messages.add(Message(content = "", type = Message.Type.DRIVER)) },
            ) { Text("Add new message") }
        }
    }
}

@Composable
private fun MessageType(
    type: Message.Type,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        onClick = onClick,
        label = {
            Text(type.name)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Selected",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}