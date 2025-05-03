package com.example.gestionetudiants.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestionetudiants.data.model.MenuItem

@Composable
fun MenuActionItemCard(menuItem: MenuItem, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 15.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color(0xFF1E1E1E)
                )
            )
            Icon(
                painter = painterResource(id = menuItem.iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp), // ⬅️ Icône plus grande
                tint = Color.Unspecified
            )
        }
    }
}



/*
@Preview
@Composable
fun PreviewMenuActionItem() {
    val title = "Add Student"
    print(R.drawable.add)
    print("Hello Brothers")
    MenuActionItem(title,R.drawable.add)
}*/
