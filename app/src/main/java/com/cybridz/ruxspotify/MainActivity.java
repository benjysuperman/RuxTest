package com.cybridz.ruxspotify;

import static com.cybridz.ruxspotify.helpers.NetworkHelper.isNetworkAvailable;

import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.cybridz.ruxspotify.components.Andromeda;
import com.cybridz.ruxspotify.components.AnimCharacter;
import com.cybridz.ruxspotify.components.Athena;
import com.cybridz.ruxspotify.components.Doraemon;
import com.cybridz.ruxspotify.components.Dorami;
import com.cybridz.ruxspotify.components.Seiya;
import com.cybridz.ruxspotify.components.Shiryu;
import com.cybridz.ruxspotify.helpers.DatasHelper;
import com.leitianpai.robotsdk.RobotService;
import com.leitianpai.robotsdk.commandlib.Light;
import com.leitianpai.robotsdk.message.ActionMessage;
import com.leitianpai.robotsdk.message.AntennaLightMessage;
import com.leitianpai.robotsdk.message.AntennaMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    /** ACTIVITY ELEMENTS **/
    private ImageView imageView;
    private TextView name;

    /** CONSTANTS **/
    // Game modes
    private static final int TEST_GAME = 1;
    private static final int ANIM_GAME = 2;

    // Timer
    private static final int BLINK_INTERVAL_FROM = 300;
    private static final int BLINK_INTERVAL_TO = 600;

    private static final int LIGHT_OFF = Light.BLACK;

    /** DATA LIST **/
    private static final int[] lights = new int[] {Light.RED,Light.GREEN,Light.BLUE,Light.ORANGE,Light.WHITE,Light.YELLOW,Light.CYAN};
    private static final int[] rotation_type = new int[]{1, 2, 3};

    private static ArrayList<AnimCharacter> characters;

    private String[] sentences = new String[]{"Hello I'm Rux", "How are you Benji", "Hello folks"};

    /** FLOW STATES **/
    private long pressed = new Date().getTime();
    private int launch = ANIM_GAME;

    private int cursor = -1;

    private Map<String, Drawable> drawableMap;

    private Handler blinkHandler;
    private Runnable blinkRunnable;

    private boolean isOff;
    protected int currentEarsColor;

    /** RUX ELEMENTS **/
    private RobotService robotService;
    private boolean openedServices = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isNetworkAvailable(getSystemService(ConnectivityManager.class))) {
            Log.d("com.cybridz.ruxspotify.launch", "No network");
            System.exit(0);
        }
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.anim_face);
        name = findViewById(R.id.character_name);
        name.setVisibility(View.INVISIBLE);
        imageView.setImageDrawable(getDrawable("home"));
        Log.d("com.cybridz.ruxspotify.launch", "setting the view");
        robotService = RobotService.getInstance(this);
        hideBar();
    }

    private void hideBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( launch == ANIM_GAME && characters == null){
            characters = new ArrayList<AnimCharacter>() {{
                add((new Seiya()).setDescription(DatasHelper.getDescription(getAssets(), "seiya")));
                add((new Shiryu()).setDescription(DatasHelper.getDescription(getAssets(), "shiryu")));
                add((new Andromeda()).setDescription(DatasHelper.getDescription(getAssets(), "andromeda")));
                add((new Athena()).setDescription(DatasHelper.getDescription(getAssets(), "athena")));
                add((new Doraemon()).setDescription(DatasHelper.getDescription(getAssets(), "doraemon")));
                add((new Dorami()).setDescription(DatasHelper.getDescription(getAssets(), "dorami")));
            }};
        }
        Random random = new Random();
        if (!openedServices) {
            openedServices = true;
            openServices();
            isOff = false;
            currentEarsColor = Light.WHITE;
            lightMessage(currentEarsColor);
            blinkHandler = new Handler();
            blinkRunnable = new Runnable() {
                @Override
                public void run() {
                    lightMessage(isOff ? currentEarsColor : LIGHT_OFF);
                    isOff = !isOff;
                    blinkHandler.postDelayed(this, random.nextInt(BLINK_INTERVAL_TO-BLINK_INTERVAL_FROM) + BLINK_INTERVAL_FROM);
                }
            };
            blinkHandler.postDelayed(blinkRunnable, random.nextInt(BLINK_INTERVAL_TO-BLINK_INTERVAL_FROM) + BLINK_INTERVAL_FROM);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.d("com.cybridz.ruxspotify.launch", "touched");
        if ((new Date().getTime() - pressed) > 500) {
            switch (launch) {
                case TEST_GAME:
                    playTest();
                    break;
                case ANIM_GAME:
                    playAnimGame();
                    break;
            }
            Log.d("com.cybridz.ruxspotify.launch", "Initial : " + pressed + ", since " + (new Date().getTime() - pressed));
            pressed = new Date().getTime();
        }
        return b;
    }

    private Drawable getDrawable(String name) {
        if(drawableMap != null && drawableMap.containsKey(name)){
            return drawableMap.get(name);
        }
        drawableMap = new HashMap<>();
        drawableMap.put("home", ContextCompat.getDrawable(this, R.drawable.home));
        drawableMap.put("seiya", ContextCompat.getDrawable(this, R.drawable.seiya));
        drawableMap.put("shiryu", ContextCompat.getDrawable(this, R.drawable.shiryu));
        drawableMap.put("andromeda", ContextCompat.getDrawable(this, R.drawable.andromeda));
        drawableMap.put("athena", ContextCompat.getDrawable(this, R.drawable.athena));
        drawableMap.put("doraemon", ContextCompat.getDrawable(this, R.drawable.doraemon));
        drawableMap.put("dorami", ContextCompat.getDrawable(this, R.drawable.dorami));
        if(drawableMap.containsKey(name)){
            return drawableMap.get(name);
        }
        throw new NullPointerException("The drawable does not exists");
    }
    protected void playTest() {
        speak();
        randomColorPart();
        randomMotorRotation();
    }

    protected void playAnimGame() {
        cursor = cursor < 0 || cursor == characters.size() - 1 ? 0 : cursor + 1;
        AnimCharacter character = characters.get(cursor);
        currentEarsColor = character.getLight();
        imageView.setImageDrawable(getDrawable(character.getName().toLowerCase()));
        name.setVisibility(View.VISIBLE);
        name.setText(character.getName());
        lightMessage(currentEarsColor);
        speak(character.getDescription());
        long wait = 100L;
        for (int i=0; i < character.getActions().size(); i++) {
            Handler handler = new Handler();
            int[] action = character.getActions().get(i);
            wait += (action[1] * action[2] * 1000L) / 2;
            Log.d("com.cybridz.ruxspotify.launch", "waiting " + wait);
            handler.postDelayed(new Runnable() {
                public void run() {
                    executeActionsMessage(action);
                }
            }, wait);
        }
        randomMotorRotation();
    }


    protected void executeActionsMessage(int[] action){
        ActionMessage actionMessage = new ActionMessage();
        actionMessage.set(action[0], action[1], action[2]);
        robotService.robotActionCommand(actionMessage);
    }


    protected void openServices() {
        robotService.robotOpenMotor();
    }


    protected void speak() {
        Random random = new Random();
        String sentence = sentences[random.nextInt(sentences.length)];
        speak(sentence);
    }

    protected void speak(String sentence) {
        Log.d("com.cybridz.ruxspotify.launch", "The tts is " + sentence);
        robotService.robotPlayTTs(sentence);
    }

    protected void randomColorPart() {
        int lightValue = lights[new Random().nextInt(lights.length)];
        lightMessage(lightValue);
    }

    protected void lightMessage(int lightValue){
        AntennaLightMessage antennaLightMessage = new AntennaLightMessage();
        antennaLightMessage.set(lightValue);
        robotService.robotAntennaLight(antennaLightMessage);
        Log.d("com.cybridz.ruxspotify.launch", "the light color is : " + lightValue);
    }

    protected void randomMotorRotation() {
        int rotationType = rotation_type[new Random().nextInt(rotation_type.length)];
        Log.d("com.cybridz.ruxspotify.launch", "the rotation type is : " + rotationType);
        AntennaMessage antennaMessage = new AntennaMessage();
        antennaMessage.set(rotationType, 1, 9, 90);
        robotService.robotAntennaMotion(antennaMessage);
    }

    protected void closeServices() {
        robotService.robotCloseMotor();
        robotService.robotCloseAntennaLight();
        robotService.unbindService();
        if (blinkHandler != null && blinkRunnable != null) {
            blinkHandler.removeCallbacks(blinkRunnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeServices();
    }

    @Override
    protected void onStop() {
        super.onStop();
        closeServices();
    }
}