package com.comsystem.homework.robot;

import com.comsystem.homework.exception.InvalidFunctionParameterException;
import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.comsystem.homework.common.ExceptionMessages.*;

@Service
public class RobotOperations {
    /**
     * An algorithm that converts a number of days into an action plan.
     * @param days the number of days that the robot can work
     * @return The action plan <em>must maximize</em> the number of stones that the robot will dig. In other words, this
     *         algorithm must try to achieve the highest value of {@link RobotPlan#numberOfStones} possible for the
     *         number of provided days. The value of {@link RobotPlan#numberOfDays} is equal to the input
     *         days parameter
     * @see RobotPlan
     */
    public RobotPlan excavateStonesForDays(int days) {
        handleInvalidFunctionParameterValue(days);

        List<RobotAction> robotActions = new ArrayList<>();
        int excavatedStones = calculateExcavatedStones(days, robotActions);

        return new RobotPlan(days, excavatedStones, robotActions);
    }

    /**
     * An algorithm that converts a number of stones into an action plan. Essentially this algorithm is the inverse of
     * {@link #excavateStonesForDays(int)}.
     * @param numberOfStones the number of stones the robot has to collect
     * @return The action plan <em>must minimize</em> the number of days necessary for the robot to dig the
     *         provided number of stones. In other words, this algorithm must try to achieve the lowest value of
     *         {@link RobotPlan#numberOfDays} possible for the number of provided stones. The value of
     *         {@link RobotPlan#numberOfStones} is equal to the numberOfStones parameter
     * @see RobotPlan
     */
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {
        handleInvalidFunctionParameterValue(numberOfStones);

        List<RobotAction> robotActions = new ArrayList<>();
        int daysToCollectStones = calculateDaysToExcavateStones(numberOfStones, robotActions);

        return new RobotPlan(daysToCollectStones, numberOfStones, robotActions);
    }

    private void handleInvalidFunctionParameterValue(int parameterValue) {
        if (parameterValue < 0) {
            throw new InvalidFunctionParameterException(NEGATIVE_PARAMETER_VALUE);
        } else if (parameterValue == 0) {
            throw new InvalidFunctionParameterException(ZERO_PARAMETER_VALUE);
        }
    }

    private int calculateExcavatedStones(int days, List<RobotAction> robotActions) {
        if (days == 1) {
            robotActions.add(RobotAction.DIG);
            return 1;
        } else {
            int excavatedStones = (int) Math.pow(2, days - 1);

            List<RobotAction> clones = Collections.nCopies(days - 1, RobotAction.CLONE);
            robotActions.addAll(clones);
            robotActions.add(RobotAction.DIG);

            return excavatedStones;
        }
    }

    private static int calculateDaysToExcavateStones(int stones, List<RobotAction> actions) {
        String daysInBinaryFormat = convertIntegerToBinary(stones);
        int previousPower = 0;
        int days = 0;

        int currentPower = daysInBinaryFormat.length();

        for (int i = daysInBinaryFormat.length() - 1; i >= 0; i--) {
            if (daysInBinaryFormat.charAt(i) == '1') {

                int cloneCount = (currentPower - i - 1) - previousPower;

                for (int j = 0; j < cloneCount; j++) {
                    actions.add(RobotAction.CLONE);
                    days++;
                }

                actions.add(RobotAction.DIG);
                days++;

                previousPower = cloneCount;
            }
        }

        return days;
    }

    private static String convertIntegerToBinary(int number) {
        return Integer.toBinaryString(number);
    }
}
