package com.patients.main.core.ui.top_bar

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.patients.main.DoctorRoutes
import com.patients.main.R
import com.patients.main.core.ui.about_button.AboutButton
import com.patients.main.core.ui.search_text_field.SearchTextField

@Composable
fun TopSearchBar(
    modifier: Modifier = Modifier,
    title: String,
    navController: NavController,
    visibleScreens: List<String>,
    isResetFilter: Boolean,
    onSearch: (String) -> Unit,
    isHomeScreen: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topBarDestination = visibleScreens.any { it == currentDestination?.route }
    if (topBarDestination) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isHomeScreen)
                    AboutButton(modifier = Modifier)
                else {
                    Icon(
                        painter = painterResource(id = +R.drawable.bar_chart_01),
                        contentDescription = null,
                        tint = Color(0xFF3C9AFB),
                        modifier = modifier.clickable {
                            navController.navigate(route = DoctorRoutes.Statistics.route)
                        }
                    )
                }
            }
            SearchTextField(
                isResetFilter = isResetFilter,
                onSearch = onSearch
            )
        }
    }
}