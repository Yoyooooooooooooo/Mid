package com.example.mid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 餐點選取狀態
    boolean[] checkedItems;
    // 餐點列表
    String[] mealItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealItems = getResources().getStringArray(R.array.meal_items);
        checkedItems = new boolean[mealItems.length];

        Button button = findViewById(R.id.button);
        TextView textView2 = findViewById(R.id.textView2);

        button.setOnClickListener(view -> showMenuDialog(textView2));
    }

    private void showMenuDialog(TextView textView2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);

        builder.setMultiChoiceItems(R.array.meal_items, checkedItems, (dialog, which, isChecked) -> {
            // 更新餐點的選取狀態
            checkedItems[which] = isChecked;
        });

        // 確認按鈕的事件處理
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            StringBuilder selectedMeals = new StringBuilder("您點的餐點有:\n"); // 加入前綴
            for (int i = 0; i < checkedItems.length; i++) {
                if (checkedItems[i]) {
                    selectedMeals.append(mealItems[i]).append("\n");
                }
            }
            textView2.setText(selectedMeals.toString());
        });

        // 離開按鈕的事件處理，這裡無需額外代碼，默認行為即關閉對話框
        builder.setNegativeButton(R.string.exit, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
