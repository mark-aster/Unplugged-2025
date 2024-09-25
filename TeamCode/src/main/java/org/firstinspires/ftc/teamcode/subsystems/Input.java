package org.firstinspires.ftc.teamcode.subsystems;

import java.util.HashMap;
import java.util.Map;

public class Input
{
    private static Map<String, Boolean> previousStates = new HashMap<>();

    public static boolean OnKeyDown(String buttonName, boolean currentState)
    {
        boolean previousState = Boolean.TRUE.equals(previousStates.getOrDefault(buttonName, false));

        if (currentState && !previousState) {
            previousStates.put(buttonName, true);
            return true;
        } else if (!currentState) {
            previousStates.put(buttonName, false);
        }

        return false;
    }

    public static boolean OnKeyUp(String buttonName, boolean currentState)
    {
        boolean previousState = Boolean.TRUE.equals(previousStates.getOrDefault(buttonName, false));

        if (!currentState && previousState) {
            previousStates.put(buttonName, false);
            return true;
        }

        return false;
    }

    public static boolean IsDown(String buttonName, boolean currentState)
    {
        return Boolean.TRUE.equals(previousStates.getOrDefault(buttonName, currentState));
    }
}
