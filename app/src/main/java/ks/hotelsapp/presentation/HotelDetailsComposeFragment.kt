package ks.hotelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import ks.hotelsapp.R
import androidx.compose.ui.graphics.Shape

class HotelDetailsComposeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeScreen()
            }
        }
    }
}

@Composable
fun ComposeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // изображение отеля
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .clip(CustomShape()),
            painter = painterResource(R.drawable.test),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        // контент карточки

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                text = "Best Western President Hotel at Times Square",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                text = "Рейтинг: 3.4",
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                text = "Расстояние от центра: 100.0",
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                text = "210 W. 55 STREET, NEW YORK NY 10019, UNITED STATES".capitalizeWords(), // для обработки текста, если там заглавные все
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                text = "Свободные номера: 34",
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun String.capitalizeWords(): String {
    return lowercase().split(" ").joinToString(" ") { it.replaceFirstChar { ch -> ch.uppercase() } }
}

@Preview(showBackground = true)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    ComposeScreen()
}

class CustomShape : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        val cropConstant = 1f * density.density
        val cornerConstant = 12f * density.density
        val path = androidx.compose.ui.graphics.Path().apply {
            addRoundRect(
                RoundRect(
                    left = size.width - cropConstant,
                    top = cropConstant,
                    right = cropConstant,
                    bottom = size.height - cropConstant,
                    topLeftCornerRadius = CornerRadius(cornerConstant, cornerConstant),
                    topRightCornerRadius = CornerRadius(cornerConstant, cornerConstant),
                    bottomLeftCornerRadius = CornerRadius(cornerConstant, cornerConstant),
                    bottomRightCornerRadius = CornerRadius(cornerConstant, cornerConstant)
                )
            )
        }
        return Outline.Generic(path = path)
    }
}