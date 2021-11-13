package kasem.sm.dynamiclinksplayground.utils

import android.content.Context
import android.content.Intent

fun Context.shareDeepLink(deepLink: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_SUBJECT,
        "You have been shared an amazing meme, check it out ->"
    )
    intent.putExtra(Intent.EXTRA_TEXT, deepLink)
    startActivity(intent)
}
