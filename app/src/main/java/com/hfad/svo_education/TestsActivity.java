package com.hfad.svo_education;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestsActivity extends AppCompatActivity {

    private ConstraintLayout commentLayout;
    private TextView quest, questionCounter, commentHeaderText, commentText;

    private Button answerButton, nextButton;

    private Test test1 = new Test();
    private Test test2 = new Test();
    private Test[] testArray = new Test[]{};

    private RadioGroup radioGroup;
    private RadioButton answer1, answer2, answer3, answer4;

    private int qNumber = 0, questionsLength;
    private String nowCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        try {
            commentLayout = findViewById(R.id.commentLayout);
            commentLayout.setVisibility(View.INVISIBLE);
// создаем тест
            createTest();
// длина массива "тестов" для контроля завершения теста
            questionsLength = testArray.length;
// счетчик вопросов теста
            questionCounter = findViewById(R.id.questionCounter);
            questionCounter.setText(qNumber+1 + "/" + questionsLength);


            answer1 = findViewById(R.id.answer1);
            answer2 = findViewById(R.id.answer2);
            answer3 = findViewById(R.id.answer3);
            answer4 = findViewById(R.id.answer4);

// формируем массив вопросов текущей выборки
            Questions[] q = testArray[qNumber].getQuestions();
// рандомно выбираем вопрос
            Questions nowQuestion = q[(int) (Math.random() * q.length)];
// находим и вставляем текст вопроса в поле вопроса
            TextView quest = findViewById(R.id.question);
            quest.setText(nowQuestion.getQuestion().toString());
// сохраняем текущий корректный отвает для проверки
            nowCorrectAnswer = nowQuestion.correctAnswer();
// создаем лист ответов, перемешиваем их и заполняем названия радиокнопок
            String[] answers = nowQuestion.getAnswers();
            List<String> intList = Arrays.asList(answers);

            Collections.shuffle(intList);

            answer1.setText(intList.get(0));
            answer2.setText(intList.get(1));
            answer3.setText(intList.get(2));
            answer4.setText(intList.get(3));

            questionCounter = findViewById(R.id.questionCounter);

        } catch(Exception e){
            Toast.makeText(this, e+" - "+testArray.length+" - "+qNumber, Toast.LENGTH_LONG).show();
        }
    }

    private void createTest() {
// создаем выборки и тест из выборок

        test1.setTestId(1);
        test1.setName("Знание операционных систем");
        test1.setQuestions(new Questions[]{ new Questions("Операционная система с максимальным уровнем оптимизации?",
                new String[]{"Windows", "Android", "Linux", "MacOS"}),
                new Questions("Самая распространенная система?",
                        new String[]{"MacOS", "Linux", "Windows", "Android"}),
                new Questions("Опитмальная операционная система для личного ноутбука?",
                        new String[]{"Windows", "MacOS", "Android", "Linux"})
        });
        test1.setTestComment("Операционная система с максимальным уровнем оптимизации - МакОсь!!! \n" +
                "Android, операционная система, использующая ядро Linux, является наиболее используемой операционной системой в мире, если судить по использованию Интернета. На его долю приходится 42% мирового рынка, за ним следует Windows с 30%, затем Apple iOS с 16%. \n" +
                "Опитмальная операционная система для личного ноутбука Конечно же Linux!");

        test2.setTestId(2);
        test2.setName("Знание языка программирования Java");
        test2.setQuestions(new Questions[]{
                new Questions("Какой тип данных отвечает за целые числа?",
                        new String[] {"String", "Float", "Boolean", "Integer"}),
                new Questions("Какой метод позволяет запустить программу на Java?",
                        new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
                new Questions("Сколько параметров может принимать функция?",
                        new String[] {"5", "10", "20", "неограниченное количество"}),
                new Questions("Кто лучший программист Java?",
                        new String[] {"Джеймс Гослинг", "Илон Маск", "Билл Гейтс", "Артем Чудодеев"})
        });
        test2.setTestComment("Integer - в переводе с английского \"Целое число\". Учи нерусский язык, хакер! \n" +
                "Основной метод всегда Main, конечно. Иди читай HeadFirst! \n" +
                "Функция может принимать в себя сколько угодно пораметров! \n" +
                "Создатель Java естественно Гослинг, но программист в этом списке один... ;)");

        testArray = new Test[] {test1, test2};

    }

    public void answerBtn(View view) {
        answerButton = findViewById(R.id.answerButton);
        answerButton.setVisibility(View.INVISIBLE);

        commentLayout = findViewById(R.id.commentLayout);
        commentLayout.setVisibility(View.VISIBLE);
// находим радиогруппу
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
// находим выбранную радиокнопку
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
// если кнопка выбрана идем дальше, если нет - алярм
        if (selectedRadioButton != null) {
            commentHeaderText = findViewById(R.id.commentHeaderText);
            commentText = findViewById(R.id.commentText);
// сверяем выбранную кнопку с правильным ответом
            if (selectedRadioButton.getText() == nowCorrectAnswer) {
                commentHeaderText.setText("");
                commentText.setText("Вы ответили правильно!");
                qNumber++;
                selectedRadioButton.setBackgroundColor(getColor(R.color.green));
            } else {
//  если ответ не верный - вытаскиваем коммент выборки
                selectedRadioButton.setBackgroundColor(getColor(R.color.red));
                commentHeaderText.setText("Мини-справка по теме");
                commentText.setText(testArray[qNumber].getTestComment());
            }
            commentText.setMovementMethod(new ScrollingMovementMethod());
            nextButton = findViewById(R.id.nextButton);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (qNumber == questionsLength) {
                        Intent intent = new Intent(TestsActivity.this, FinishWindowActivity.class);
                        startActivity(intent);
// иначе переходим к следующей выборке
                    } else {
                        //  чистим радиокнопки
                        radioGroup.clearCheck();
                        //  формируем массив вопросов выборки
                        Questions[] q = testArray[qNumber].getQuestions();
                        //  рандомно выбираем вопрос
                        Questions nowQuestion = q[(int) (Math.random() * q.length)];
                        //  прописываем текст вопроса в поле вопроса
                        TextView quest = findViewById(R.id.question);
                        quest.setText(nowQuestion.getQuestion().toString());
                        //  находим радиокнопки
                        answer1 = findViewById(R.id.answer1);
                        answer2 = findViewById(R.id.answer2);
                        answer3 = findViewById(R.id.answer3);
                        answer4 = findViewById(R.id.answer4);
                        //  находим корректный ответ
                        nowCorrectAnswer = nowQuestion.correctAnswer();
                        // формируем лист ответов, перемешиваем и заполняем радиокнопки
                        String[] answers = nowQuestion.getAnswers();
                        List<String> intList = Arrays.asList(answers);

                        Collections.shuffle(intList);

                        answer1.setText(intList.get(0));
                        answer2.setText(intList.get(1));
                        answer3.setText(intList.get(2));
                        answer4.setText(intList.get(3));

                        commentLayout.setVisibility(View.INVISIBLE);
                        answerButton.setVisibility(View.VISIBLE);
                        selectedRadioButton.setBackgroundColor(getColor(R.color.white));
                        questionCounter.setText(qNumber + 1 + "/" + questionsLength);
                    }
                }
            });
        } else {
// если не выборан ни один ответ - алярм
            Toast.makeText(this, "надо что-то выбрать...", Toast.LENGTH_LONG).show();
        }
    }

}