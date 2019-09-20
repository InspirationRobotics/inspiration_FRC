/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Robot extends TimedRobot {

    /* initialize the joystick */
    private Joystick joystick;

    /* create objects for every talon */
    private TalonSRX flywheelOne;
    private TalonSRX flywheelTwo;
    private TalonSRX drivetrainLeft;
    private TalonSRX drivetrainRight;
    private TalonSRX shooter;
    private TalonSRX actuator;

    private double flywheelspeed = 0;
    
    private static final enum Controls {
	A=1, B, X, Y, LB, RB, BACK, START, LEFT_JOYSTICK_PRESSED, RIGHT_JOYSTICK_PRESSED
    };

    /**
     * Initialization process
     */
    @Override
    public void robotInit() {

        /* assign a port to the joystick */
	joystick = new Joystick(0);
	
	/* assign talon ports */
	flywheelOne = new TalonSRX(0);
	flywheelTwo = new TalonSRX(1);
	drivetrainLeft = new TalonSRX(2);
	drivetrainRight = new TalonSRX(3);
	shooter = new TalonSRX(4);
	actuator = new TalonSRX(5);
	
    }

    /**
     * Send packets every time interval.
     */
    @Override
    public void teleopPeriodic() {

	/* tank drive */
	drivetrainLeft.set(ControlMode.PercentOutput, -(joystick.getRawAxis(1)));
        drivetrainRight.set(ControlMode.PercentOutput, joystick.getRawAxis(5));

	/* use the dpad to elevate and lower the shooter */
	while (joystick.getPOV() == 0) {
	    actuator.set(ControlMode.PercentOutput, 100);
	}
	while (joystick.getPOV() == 180) {
	    actuator.set(ControlMode.PercentOutput, 0);
	}

	/* turn on the flywheels */
	if (joystick.getRawButton(Controls.START)) {
	    flywheelOne.set(ControlMode.PercentOutput, (flywheelspeed)*100)
	}

	/* reduce and increase flywheel speed */
	if (joystick.getRawButton(LB) && flywheelspeed > 0) {
	    flywheelspeed = flywheelspeed - 0.2;
	} else if (joystick.getRawButton(LB) && flywheelspeed < 1.0) {
	    flywheelspeed = flywheelspeed + 0.2;
	}

	/* turn off flywheels */
	if (m_joystick.getRawButton(Controls.BACK)) {
	    flywheelOne.set(ControlMode.PercentOutput, 0);
	    flywheelTwo.set(ControlMode.PercentOutput, 0);
	}
	
    }
    
}
