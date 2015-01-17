
package org.usfirst.frc.team5115.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5115.robot.Robot;

/**
 *
 */
public class AutoLift extends Command {
	
	private double initHeight;
	private double height;

    public AutoLift(double h) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumatic);
        requires(Robot.winch);
        
        initHeight = Robot.winch.heightFromTop();
        height = h;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatic.in();
    	Robot.winch.move(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.winch.heightFromTop() - initHeight >= height;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
