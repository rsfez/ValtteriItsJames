package com.robined.valtteriitsjames.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.robined.valtteriitsjames.R
import com.robined.valtteriitsjames.domain.Message
import com.robined.valtteriitsjames.ds.PrimaryButton
import com.robined.valtteriitsjames.ds.Spacing.small
import com.robined.valtteriitsjames.ds.Spacing.xSmall

@Composable
internal fun MessagesSection(modifier: Modifier = Modifier, messages: SnapshotStateList<Message>) {
    LazyColumn(modifier = modifier) {
        item {
            Text(stringResource(R.string.messages))
        }
        items(messages.size) { index ->
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
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
                    MultiChoiceSegmentedButtonRow {
                        Message.Type.entries.forEachIndexed { typeIndex, type ->
                            SegmentedButton(
                                checked = message.type == type,
                                onCheckedChange = { messages[index] = message.copy(type = type) },
                                shape = SegmentedButtonDefaults.itemShape(
                                    index = typeIndex,
                                    count = Message.Type.entries.size
                                ),
                            ) {
                                Text(type.name)
                            }
                        }
                    }
                    PrimaryButton(
                        onClick = { messages.removeAt(index) }) {
                        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = stringResource(R.string.delete_content_description),
                            )
                        }
                    }
                }
                Box(modifier = Modifier.height(xSmall))
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
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add_content_description),
                    )
                    Box(modifier = Modifier.width(small))
                    Text(stringResource(R.string.add_new_message_button))
                }
            }
        }
    }
}
