package elieoko.hoshi.butterfly.core.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

data class ButterflyUser(
    val name: String,
    val email: String,
)

class ButterflySession {
    var user by mutableStateOf<ButterflyUser?>(null)
        private set

    var joinedGroupIds by mutableStateOf(setOf<String>())
        private set

    val isAuthenticated: Boolean
        get() = user != null

    fun createAccount(name: String, email: String) {
        user = ButterflyUser(name = name.trim(), email = email.trim())
    }

    fun signOut() {
        user = null
        joinedGroupIds = emptySet()
    }

    fun joinGroup(groupId: String) {
        joinedGroupIds = joinedGroupIds + groupId
    }

    fun leaveGroup(groupId: String) {
        joinedGroupIds = joinedGroupIds - groupId
    }

    fun isMemberOf(groupId: String): Boolean = groupId in joinedGroupIds
}

val LocalButterflySession = staticCompositionLocalOf<ButterflySession> {
    error("ButterflySession not provided")
}

@Composable
fun rememberButterflySession(): ButterflySession = remember { ButterflySession() }
