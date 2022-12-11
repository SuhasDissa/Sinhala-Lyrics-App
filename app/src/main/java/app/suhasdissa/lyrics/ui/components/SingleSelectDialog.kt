@file:Suppress("unused")

package app.suhasdissa.lyrics.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SingleSelectDialog(
    modifier: Modifier = Modifier,
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    submitButtonText: String,
    onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {

    val selectedOption = remember { mutableStateOf(defaultSelected) }
    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Surface(
            modifier = modifier.width(300.dp), shape = RoundedCornerShape(10.dp)
        ) {

            Column(modifier = modifier.padding(10.dp)) {

                Text(text = title)

                Spacer(modifier = modifier.height(10.dp))

                LazyColumn(modifier = modifier.height(500.dp)) {
                    items(items = optionsList) {
                        SelectRow(
                            text = it, selectedValue = optionsList[selectedOption.value]
                        ) { selectedValue ->
                            selectedOption.value = optionsList.indexOf(selectedValue)
                        }
                    }
                }

                Spacer(modifier = modifier.height(10.dp))

                Button(onClick = {
                    onSubmitButtonClick.invoke(selectedOption.value)
                    onDismissRequest.invoke()
                }) {
                    Text(text = submitButtonText)
                }
            }

        }
    }
}

@Composable
fun SelectRow(
    modifier: Modifier = Modifier,
    text: String,
    selectedValue: String,
    onClickListener: (String) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .selectable(selected = (text == selectedValue), onClick = {
                onClickListener(text)
            })
            .padding(horizontal = 16.dp)) {
        RadioButton(selected = (text == selectedValue), onClick = {
            onClickListener(text)
        })
        Text(
            text = text, modifier = modifier.padding(start = 16.dp)
        )
    }
}