package dev.dbuzin.android.main.navigation.bottom

internal enum class BottomNavigationKeys {
    HOME( HOME_ROUTE),
    SCAN(SCAN_ROUTE),
    PROFILE(PROFILE_ROUTE);

    var route: String = ""

    constructor()

    constructor(route: String) {
        this.route = route
    }

    companion object {
        fun getByRoute(route: String) = when(route) {
            HOME_ROUTE -> HOME
            SCAN_ROUTE -> SCAN
            PROFILE_ROUTE -> PROFILE
            else -> HOME
        }
    }
}

private const val HOME_ROUTE = "/home"
private const val SCAN_ROUTE = "/scan"
private const val PROFILE_ROUTE = "/profile"
