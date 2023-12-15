package com.example.bwfmock

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.bwfmock.ui.theme.JetpackComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSampleTheme {
                MyApp(modifier = Modifier)
            }
        }
    }
}

data class Ranking(
    val worldRank: Int,
    val name: String,
    val icon: String
)

@Composable
fun MyApp(
    modifier: Modifier
) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        RankingScreen(
            ranking = emptyList(),
            modifier = modifier
        )
    }
}

@Composable
private fun RankingItem(
    ranking: Ranking,
    modifier: Modifier
) {
    Row(
        modifier = modifier//.padding(all = 8.dp)
    ) {
        Text(text = ranking.worldRank.toString())
        Spacer(modifier = modifier.width(8.dp))
        // インターネットから画像を読み込む
        SubcomposeAsyncImage(
            model = ranking.icon,
            contentDescription = "player icon",
            loading = {
                CircularProgressIndicator()
            },
            modifier = modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(text = ranking.name)
    }
}

@Composable
fun RankingScreen(
    ranking: List<Ranking>,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        for (rank in ranking) {
            this.item {
                RankingItem(ranking = rank, modifier = modifier)
            }
        }
    }
}

private class PreviewProvider : PreviewParameterProvider<List<Ranking>> {
    override val values: Sequence<List<Ranking>>
        get() = sequenceOf(
            emptyList(),
            listOf(
                Ranking(

                    1,
                    "Test VALUE",
                    "https://img.bwfbadminton.com/image/upload/t_96_player_profile/v1658823118/assets/players/thumbnail/89785.png"
                ),
                Ranking(
                    2,
                    "Sample HOGE",
                    "https://img.bwfbadminton.com/image/upload/t_96_player_profile/v1697764072/assets/players/thumbnail/25831.png"
                )
            )
        )
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun RankingScreenPreview(
    @PreviewParameter(PreviewProvider::class) sampleDate: List<Ranking>
) {
    RankingScreen(ranking = sampleDate, modifier = Modifier)
}
