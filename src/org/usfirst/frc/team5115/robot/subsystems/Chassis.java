package org.usfirst.frc.team5115.robot.subsystems;

import org.usfirst.frc.team5115.robot.Robot;
import org.usfirst.frc.team5115.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	private double throttle;
    private Victor rightMotor;
    private Victor leftMotor;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private DigitalInput toteDetector;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Chassis() {
        rightMotor = new Victor(RobotMap.rightMotor);
        leftMotor = new Victor(RobotMap.leftMotor);
        
        leftEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2, true, EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2, true, EncodingType.k4X);
        leftEncoder.setDistancePerPulse(12.566);
        rightEncoder.setDistancePerPulse(12.566);
        
        toteDetector = new DigitalInput(RobotMap.toteDetector);
        
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    public void drive(double left, double right) {
    	throttle = Robot.oi.throttle();
    	
        leftMotor.set(left * throttle * RobotMap.speedFactor);
        rightMotor.set(right * throttle * RobotMap.speedFactor);
        if (left * throttle > 1) { leftMotor.set(-1); }
        if (left * throttle < -1) { leftMotor.set(1); }
        if (right * throttle > 1) { rightMotor.set(-1); }
        if (right * throttle < -1) { rightMotor.set(1); }
        
        SmartDashboard.putNumber("Throttle", throttle);
        SmartDashboard.putNumber("Left Speed", leftMotor.get());
        SmartDashboard.putNumber("Right Speed", rightMotor.get());
    }
    
    public void startEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public double leftDist() {
    	return leftEncoder.getDistance();
    }
    
    public double rightDist() {
    	return rightEncoder.getDistance();
    }
    
    public boolean hitTote() {
    	return toteDetector.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }  
}