package com.renj.jetpack.compose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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
class ComposeListActivity : ComponentActivity() {

    object Commend {
        fun start(context: Context) {
            context.startActivity(Intent(context, ComposeListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageContent()
        }
    }

    @Composable
    fun PageContent() {

    }

    // 用于实时预览页面
    @Preview
    @Composable
    fun PreviewPageContent() {
        PageContent()
    }
}