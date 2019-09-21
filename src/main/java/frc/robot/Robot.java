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
import java.lang.Thread.*;

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
    private double temp;

    /*
    private static final enum Controls {
	A, B, X, Y, LB, RB, BACK, START, LEFT_JOYSTICK_PRESSED, RIGHT_JOYSTICK_PRESSED;
    }
    */
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
	actuator = new TalonSRX(12);
	
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
	if (joystick.getPOV() == 0) {
	    actuator.set(ControlMode.PercentOutput, 1);
	} else if (joystick.getPOV() == 180) {
	    actuator.set(ControlMode.PercentOutput, -1);
	} else {
	    actuator.set(ControlMode.PercentOutput, 0);
	}
	
	/* turn on the flywheels */
	if (joystick.getRawButton(8)) {
	    temp = flywheelspeed;
	    flywheelOne.set(ControlMode.PercentOutput, flywheelspeed);
	    flywheelTwo.set(ControlMode.PercentOutput, flywheelspeed);   
	}
	
	if (temp != flywheelspeed) {
	    flywheelOne.set(ControlMode.PercentOutput, flywheelspeed);
	    flywheelTwo.set(ControlMode.PercentOutput, flywheelspeed);
	    temp = flywheelspeed;
	}
	
	/* reduce and increase flywheel speed */
	if (joystick.getRawButton(5) && flywheelspeed > 0) {
	    flywheelspeed = flywheelspeed - 0.2;
	    System.out.println(flywheelspeed);

	    try {
		// thread to sleep for 1000 milliseconds
		Thread.sleep(1000);
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    
	} else if (joystick.getRawButton(6) && flywheelspeed < 1) {
	    flywheelspeed = flywheelspeed + 0.2;
	    System.out.println(flywheelspeed);

	    try {
		// thread to sleep for 1000 milliseconds
		Thread.sleep(1000);
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    
	}

	/* turn off flywheels */
	if (joystick.getRawButton(7)) {
	    flywheelOne.set(ControlMode.PercentOutput, 0);
	    flywheelTwo.set(ControlMode.PercentOutput, 0);
	}
	
    }

}
