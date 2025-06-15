package com.example.latihan_praktikum_6;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Object InstrumentationRegistry = null;
        byte[] appContext = InstrumentationRegistry.toString().getBytes();

        assertEquals("com.example.latihan_praktikum_6", String.valueOf(appContext.getClass()));
    }

    private void assertEquals(String s, String packageName) {

    }
}
