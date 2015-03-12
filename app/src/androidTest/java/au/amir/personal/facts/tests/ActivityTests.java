package au.amir.personal.facts.tests;

import android.test.UiThreadTest;
import android.widget.ImageView;
import android.widget.TextView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowImageView;

import au.amir.personal.facts.R;
import au.amir.personal.facts.activity.MainActivity;
import au.amir.personal.facts.activity.SplashScreenActivity;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class ActivityTests {

    private MainActivity mActivity;

    @Before
    public void setup() {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

  @Test
    public void testStartupActivityInitialiseMsg() {
      TextView Msg2 = (TextView) mActivity.findViewById(R.id.txtMsg2);
      assertEquals(" Initializing, Please Wait ",Msg2.getText().toString());
    }


}
