package com.renj.jetpack.compose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renj.jetpack.R
import com.renj.jetpack.compose.ui.theme.JetpackTheme
import com.renj.jetpack.compose.ui.theme.Purple500
import com.renj.jetpack.compose.ui.theme.Typography

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2021-11-19   14:57
 *
 * 描述：
 *
 * 修订历史：
 *
 * ======================================================================
 */
class ComposeMainActivity : ComponentActivity() {

    object Commend {
        fun start(context: Context) {
            context.startActivity(Intent(context, ComposeMainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme(false) {
                PageContent()
            }
        }
    }

    @Composable
    fun PageContent() {
        MessageCord(name = "Android")
    }

    @Composable
    fun MessageCord(name: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_png1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
            )
            Text(text = "Hello $name", style = Typography.h6)
            Text(text = "Hello Word", style = Typography.subtitle1, color = Purple500)
        }
    }

    // 用于实时预览页面
    @Preview
    @Composable
    fun PreviewPageContent() {
        JetpackTheme(false) {
            PageContent()
        }
    }
}