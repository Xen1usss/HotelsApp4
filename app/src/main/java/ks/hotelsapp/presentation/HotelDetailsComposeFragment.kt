package ks.hotelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.fragment.app.viewModels
import coil3.compose.rememberAsyncImagePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelDetailsComposeFragment : Fragment() {

    private val viewModel: HotelDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val hotelId = arguments?.getInt("hotelId") ?: -1
        viewModel.loadHotel(hotelId)

        return ComposeView(requireContext()).apply {
            setContent {
                val hotel = viewModel.hotel.observeAsState().value
                val hotelImage = viewModel.hotelImage.observeAsState().value
                val imageError = viewModel.hotelImageError.observeAsState().value ?: false

                hotel?.let {
                    ComposeScreen(
                        hotelName = it.name,
                        rating = "Рейтинг: ${it.stars}",
                        distanceToCenter = "${it.distance}",
                        address = it.address,
                        freeRooms = "${it.availableSuitesCount}",
                        imageUrl = hotelImage,
                        imageError = imageError  // Передаем флаг ошибки
                    )
                } ?: Text("Отель не найден")
            }
        }
    }
}

@Composable
fun ComposeScreen(
    hotelName: String = "Какое-то название",
    rating: String = "Рейтинг: 3.4",
    distanceToCenter: String = "100 metres",
    address: String = "Санкт Петербург, Мурино",
    freeRooms: String = "1, 2, 23",
    imageUrl: String? = null,
    imageError: Boolean = false  // флаг ошибки 404 по идее
)
{
    val imageError = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // изображение отеля
        if (imageError.value) {
            Text(
                "Изображение не найдено",
                color = Color.Red
            )  // Показываем ошибку, если изображение не найдено
        } else {
            imageUrl?.let {
                val painter = rememberAsyncImagePainter(
                    model = it,
                    onError = {
                        // Если ошибка загрузки (например, 404), показываем сообщение
                        imageError.value = true
                    }
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                        .clip(CustomShape()),
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            } ?: Text("Изображение не доступно")
        }
        // контент карточки
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Название отеля
            Text(
                text = hotelName,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Рейтинг и расстояние до центра
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Рейтинг
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107), // Желтый цвет, как в Material Design
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = rating,
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Расстояние до центра
                Icon(
                    imageVector = Icons.Outlined.LocationOn, // 📍 Иконка Material 3
                    contentDescription = "Distance",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = distanceToCenter,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Адрес
            Text(
                text = address,
                fontSize = 16.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Свободные номера
            Text(
                text = "Свободные номера: $freeRooms",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
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