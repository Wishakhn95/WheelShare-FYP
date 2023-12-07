package com.example.wheelshare_client.presentation.components.platform

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.wheelshare_client.presentation.components.setNullableIcon
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import com.example.wheelshare_client.presentation.theme.LocalWheelShareShapes
import com.example.wheelshare_client.presentation.theme.LocalWheelShareSpacing
import com.example.wheelshare_client.util.DrawableHelper
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun IconifiedTextInputField(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String? = null,
    placeHolder: String? = null,
    textLimit: Int = 1000,
    isError: Boolean = false,
    errorText: String? = null,
    value: String = "",
    isSingleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    trailingIcon: String? = null,
    trailingIconContentDescription: String? = null,
    leadingIcon: String? = null,
    leadingIconContentDescription: String? = null,
    isNumberOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onClick: (() -> Unit)? = null,
    onTextChanged: ((String) -> Unit)? = null,
) {

    var isFocused by remember { mutableStateOf(false) }

    var textColor = LocalWheelShareColors.current.greyMid
    var color = LocalWheelShareColors.current.grey

    when {
        isError -> {
            textColor = LocalWheelShareColors.current.error
            color = LocalWheelShareColors.current.error
        }

        isFocused -> {
            textColor = LocalWheelShareColors.current.primary
            color = LocalWheelShareColors.current.primary
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.xs)
    ) {
        MarkazOutlinedTextField(
            keyboardActions = keyboardActions,
            isError = isError,
            enabled = enabled,
            value = value,
            readOnly = readOnly,
            leadingIcon = setNullableIcon(leadingIcon, leadingIconContentDescription),
            trailingIcon = setNullableIcon(trailingIcon, trailingIconContentDescription),
            onValueChange = { value ->
                if (value.length <= textLimit) {
                    onTextChanged?.invoke(if (isNumberOnly) value.filter { it.isDigit() } else value)
                }
            },
            placeholder = {
                placeHolder?.let {
                    Text(
                        text = placeHolder,
                        style = MaterialTheme.typography.body1,
                        color = LocalWheelShareColors.current.greyMid
                    )
                }
            },
            modifier =
            (if (onClick != null) Modifier.clickable { onClick() } else Modifier)
                .height(44.dp)
                .onFocusChanged { isFocused = it.isFocused }
                .fillMaxWidth(),
            singleLine = isSingleLine,
            shape = LocalWheelShareShapes.current.textFieldShape,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions =
            KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            colors =
            TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color,
                unfocusedBorderColor = LocalWheelShareColors.current.grey,
                backgroundColor = LocalWheelShareColors.current.surface,
                focusedLabelColor = LocalWheelShareColors.current.primary,
                placeholderColor = LocalWheelShareColors.current.onSurface,
                unfocusedLabelColor = LocalWheelShareColors.current.greyMid,
                errorBorderColor = LocalWheelShareColors.current.error,
                errorLabelColor = LocalWheelShareColors.current.error,
                disabledBorderColor = LocalWheelShareColors.current.grey
            ),
            label = {
                label?.let {
                    Text(
                        color = textColor,
                        text = it,
                        style =
                        if (isFocused || value.isBlank().not()) MaterialTheme.typography.body2
                        else MaterialTheme.typography.body1
                    )
                }
            }
        )
        if (isError && errorText != null) {
            TextFieldErrorText(text = errorText)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TextFieldErrorText(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.xs),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(DrawableHelper.TEXT_FIELD_ERROR),
            contentDescription = null
        )
        Text(
            text = text,
            style =
            MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Thin, color = LocalWheelShareColors.current.error
            )
        )
    }
}

@Composable
fun MarkazOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = LocalWheelShareShapes.current.textFieldShape,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse { colors.textColor(enabled).value }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
        value = value,
        modifier =
        if (label != null) {
            modifier.semantics(mergeDescendants = true) {}
        } else {
            modifier
        }
            .background(colors.backgroundColor(enabled).value, shape),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox =
        @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding =
                PaddingValues(
                    start = LocalWheelShareSpacing.current.semiLargeSpacing,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = LocalWheelShareSpacing.current.semiLargeSpacing
                ),
                border = {
                    TextFieldDefaults.BorderBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        shape,
                        unfocusedBorderThickness = 1.dp,
                        focusedBorderThickness = 1.dp
                    )
                }
            )
        }
    )
}