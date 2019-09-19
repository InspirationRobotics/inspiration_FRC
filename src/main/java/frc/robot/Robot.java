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
    private Joystick m_joystick;

    /* initialize every talon */
    public TalonSRX drivetrainLeft;
    public TalonSRX drivetrainRight;
    
    private static final enum Controls {
	A=1, B, X, Y, LB, RB, BACK, START, LEFT_JOYSTICK_PRESSED, RIGHT_JOYSTICK_PRESSED
    };

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

	/* assign a port to the joystick */
	m_joystick = new Joystick(0);
	
	/* assign talon ports */
	drivetrainLeft = new TalonSRX(2);
	drivetrainRight = new TalonSRX(3);

    }
    
    @Override
    public void teleopPeriodic() {

	drivetrainLeft.set(ControlMode.PercentOutput, -(m_joystick.getRawAxis(1)));
        drivetrainRight.set(ControlMode.PercentOutput, m_joystick.getRawAxis(5));
	
    }
    
}
