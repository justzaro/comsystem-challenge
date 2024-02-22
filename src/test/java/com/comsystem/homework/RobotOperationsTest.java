package com.comsystem.homework;

import com.comsystem.homework.exception.InvalidFunctionParameterException;
import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import com.comsystem.homework.robot.RobotOperations;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RobotOperationsTest {

    private final RobotOperations robotOperations = new RobotOperations();

    @Test
    public void testExcavateStonesForDays_WhenGivenNegativeInput_ShouldThrowException() {
        int days = -1;
        assertThrows(InvalidFunctionParameterException.class, () -> {
            robotOperations.excavateStonesForDays(days);
        });
    }

    @Test
    public void testExcavateStonesForDays_WhenGivenZeroAsInput_ShouldThrowException() {
        int days = 0;
        assertThrows(InvalidFunctionParameterException.class, () -> {
            robotOperations.excavateStonesForDays(days);
        });
    }

    @Test
    public void testExcavateStonesForDays_WhenGivenFiveAsInputValue_ShouldReturnValidRobotPlan() {
        int days = 5;

        int expectedNumberOfDays = 5;
        int expectedNumberOfStones = 16;
        ArrayList<RobotAction> expectedActions = new ArrayList<>();

        for (int i = 0; i < expectedNumberOfDays - 1; i++) {
            expectedActions.add(RobotAction.CLONE);
        }

        expectedActions.add(RobotAction.DIG);
        RobotPlan expectedPlan = new RobotPlan(expectedNumberOfDays, expectedNumberOfStones, expectedActions);
        RobotPlan resultPlan = robotOperations.excavateStonesForDays(days);

        assertEquals(expectedPlan, resultPlan);
    }

    @Test
    public void testExcavateStonesForDays_WhenGivenTenAsInputValue_ShouldReturnValidRobotPlan() {
        int days = 10;

        int expectedNumberOfDays = 10;
        int expectedNumberOfStones = 512;
        ArrayList<RobotAction> expectedActions = new ArrayList<>();

        for (int i = 0; i < expectedNumberOfDays - 1; i++) {
            expectedActions.add(RobotAction.CLONE);
        }

        expectedActions.add(RobotAction.DIG);
        RobotPlan expectedPlan = new RobotPlan(expectedNumberOfDays, expectedNumberOfStones, expectedActions);
        RobotPlan resultPlan = robotOperations.excavateStonesForDays(days);

        assertEquals(expectedPlan, resultPlan);
    }

    @Test
    public void testDaysRequiredToCollectStones_WhenGivenNegativeInput_ShouldThrowException() {
        int numberOfStones = -1;
        assertThrows(InvalidFunctionParameterException.class, () -> {
            robotOperations.daysRequiredToCollectStones(numberOfStones);
        });
    }

    @Test
    public void testDaysRequiredToCollectStones_WhenGivenZeroAsInput_ShouldThrowException() {
        int numberOfStones = 0;
        assertThrows(InvalidFunctionParameterException.class, () -> {
            robotOperations.excavateStonesForDays(numberOfStones);
        });
    }

    @Test
    public void testDaysRequiredToCollectStones_WhenGivenFiveAsInputValue_ShouldReturnValidRobotPlan() {
        int numberOfStones = 5;

        int expectedNumberOfDays = 4;
        int expectedNumberOfStones = 5;
        ArrayList<RobotAction> expectedActions = new ArrayList<>();

        expectedActions.add(RobotAction.DIG);
        expectedActions.add(RobotAction.CLONE);
        expectedActions.add(RobotAction.CLONE);
        expectedActions.add(RobotAction.DIG);

        RobotPlan expectedPlan = new RobotPlan(expectedNumberOfDays, expectedNumberOfStones, expectedActions);
        RobotPlan resultPlan = robotOperations.daysRequiredToCollectStones(numberOfStones);

        assertEquals(expectedPlan, resultPlan);
    }

    @Test
    public void testDaysRequiredToCollectStones_WhenGivenEightAsInputValue_ShouldReturnValidRobotPlan() {
        int numberOfStones = 8;

        int expectedNumberOfDays = 4;
        int expectedNumberOfStones = 8;
        ArrayList<RobotAction> expectedActions = new ArrayList<>();

        expectedActions.add(RobotAction.CLONE);
        expectedActions.add(RobotAction.CLONE);
        expectedActions.add(RobotAction.CLONE);
        expectedActions.add(RobotAction.DIG);

        RobotPlan expectedPlan = new RobotPlan(expectedNumberOfDays, expectedNumberOfStones, expectedActions);
        RobotPlan resultPlan = robotOperations.daysRequiredToCollectStones(numberOfStones);

        assertEquals(expectedPlan, resultPlan);
    }
}
