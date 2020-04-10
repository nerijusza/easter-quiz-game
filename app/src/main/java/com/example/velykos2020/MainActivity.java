package com.example.velykos2020;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    List<Task> tasks;
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tasks = Arrays.asList(
                new Task(Task.Kid.VISI, "Įėjus pro duris reikia kažkur batus nusivalyti, gal ten ir kodukas slepiasi?", "pradedam"),
                new Task(Task.Kid.VISI, "Tėčio mėgiamas, o Beno labai nemėgiamas valgis?", "grikiai"),
                new Task(Task.Kid.VISI, "Minu minu ir stoviu vietoje? Kur čia taip? Gal ir kodas ten yra?", "sportas"),
                new Task(Task.Kid.BENAS, "Jei sudėsi skirtingos vertės euro monetas, kokia suma gausis centais?", "388"),
                new Task(Task.Kid.MILDA, "Koks skaičius gaunasi sudėjus Beno, Mildos ir Simo metus?", "22"),
                new Task(Task.Kid.SIMAS, "Koks šunyčio patrulio vardas, kuris gelbėja vandenyje (oranžinis)? ", "zuma"),
                new Task(Task.Kid.VISI, "Kur mes valgome? Ten kažkur kodukas slepiasi. Surasit?", "blynai"),
                new Task(Task.Kid.VISI, "Ilgiausia Lietuvos upė?", "nemunas"),
                new Task(Task.Kid.BENAS, "Robotas aš kietas esu, valdo mane Benas su pultu, ir kodą aš galbūt turiu ;)", "robo"),
                new Task(Task.Kid.SIMAS, "Wrum wrum wrum. Simas ne pėščias,bet lauke lekia greitai su kuom? Gal ten ir kodukas yra?", "kietuolis"),
                new Task(Task.Kid.MILDA, "Lėja iš Miglų slėnio turėjo brolį, koks buvo jo vardas?", "nojus"),
                new Task(Task.Kid.VISI, "Kiek pusseserių turite? Hmm?", "4"),
                new Task(Task.Kid.VISI, "Koks naminis gyvūnas pasislėpė tėčio naujame Treatwell puodelyje?", "višta"),
                new Task(Task.Kid.VISI, "Monopolis fainas žaidimas, įdomu kiek kainuoja Pilies gatvė?", "300"),
                new Task(Task.Kid.BENAS, "Ninzės Timio draugas (vardas prasideda iš A)?", "alfredas"),
                new Task(Task.Kid.SIMAS, "3 + 2 + 4 = ?", "9"),
                new Task(Task.Kid.MILDA, "Aš Mildutė, dar mažytė buvau, vežimėlyje sėdėjau, žiūriu į jus ir koduką slepiu, surasit?", "princesė"),
                new Task(Task.Kid.VISI, "Koks pats stipriausias žmogaus riksmas decibelais?", "129"),
                new Task(Task.Kid.VISI, "Smagu prisiminti kai buvom mažesni, gal paieškom koduko peliuko Mikio foto albume?", "šeima"),
                new Task(Task.Kid.BENAS, "13 padauginus iš 13, kiek bus Benai (be skaičiuotuvo)?", "169"),
                new Task(Task.Kid.SIMAS, "Simas pats geriausias ropliukas pasaulyje, hmm kur galėtų būti kodas?", "čempionas"),
                new Task(Task.Kid.MILDA, "Simas per vieną valandą suvalgo 1 saldainį, Benas 2, o Milda 3. Kiek saldainių visi vaikai suvalgys per 2 valandas?", "12"),
                new Task(Task.Kid.VISI, "Gal padėliojam Vinipūho puzzle iš 30 detalių?", "labai geri vaikai"),
                new Task(Task.Kid.VISI, "Kad mergaitė būtų princesė, ko reikia? Gal ten ir kodukas yra?", "auksas"),
                new Task(Task.Kid.VISI, "Aš labai labai mažas. Aukštai, aukštai, vaikų kambaryje, slepiuosi, surasit mane?", "super")
        );

        init();
    }

    private void init() {
        TextView textView = findViewById(R.id.labelTaskTotal);
        textView.setText(String.valueOf(tasks.size()));

        updateTask();
        configureButton();
    }

    private void configureButton() {
        findViewById(R.id.buttonAnswer).setOnClickListener(v -> {
            String answer = getAnswer();

            if (answer.isEmpty()) {
                showMessage("Įvesk atsakymą");
                return;
            }

            if (!getCurrentTask().getAnswer().equals(answer)) {
                showMessage("Neteisingas atsakymas :(");
                return;
            }

            currentQuestion++;
            if (tasks.size() == currentQuestion) {
                showFinalScreen();
            } else {
                showMessage("Teisingai, valio!!!");
                updateTask();
            }
        });
    }

    private void updateTask() {
        updateTaskNumber();
        updateKidName();
        updateQuestion();
        clearAnswer();
    }

    private void updateTaskNumber() {
        TextView textView = findViewById(R.id.labelTaskNumber);
        textView.setText(String.valueOf(currentQuestion + 1));
    }

    private void updateKidName() {
        ImageView imageView = findViewById(R.id.imageKid);
        switch (getCurrentTask().getKid()) {
            case BENAS: imageView.setImageResource(R.drawable.benas); break;
            case MILDA: imageView.setImageResource(R.drawable.milda); break;
            case SIMAS: imageView.setImageResource(R.drawable.simas); break;
            case VISI: imageView.setImageResource(R.drawable.visi); break;
        }
    }

    private void updateQuestion() {
        TextView textView = findViewById(R.id.labelQuestionContent);
        textView.setText(getCurrentTask().getQuestion());
    }

    private void clearAnswer() {
        TextView textView = findViewById(R.id.textAnswer);
        textView.setText("");
    }

    private Task getCurrentTask() {
        return tasks.get(currentQuestion);
    }

    private void showMessage(String text) {
        makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private String getAnswer() {
        TextView textView = findViewById(R.id.textAnswer);
        return textView.getText().toString().toLowerCase();
    }

    private void showFinalScreen() {
        hideKeyboard(this);

        findViewById(R.id.label1).setVisibility(View.INVISIBLE);
        findViewById(R.id.label2).setVisibility(View.INVISIBLE);
        findViewById(R.id.label3).setVisibility(View.INVISIBLE);
        findViewById(R.id.labelTaskNumber).setVisibility(View.INVISIBLE);
        findViewById(R.id.labelTaskTotal).setVisibility(View.INVISIBLE);
        findViewById(R.id.labelQuestionContent).setVisibility(View.INVISIBLE);
        findViewById(R.id.textAnswer).setVisibility(View.INVISIBLE);
        findViewById(R.id.buttonAnswer).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageKid).setVisibility(View.INVISIBLE);

        findViewById(R.id.finalContent).setVisibility(View.VISIBLE);
        findViewById(R.id.finalImage).setVisibility(View.VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
