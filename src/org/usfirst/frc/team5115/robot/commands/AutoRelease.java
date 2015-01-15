
package org.usfirst.frc.team5115.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5115.robot.Robot;

/**
 *
 */
public class AutoRelease extends Command {
	
	private double height;

    public AutoRelease(double h) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumatic);
        requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.move(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.winch.distToTop() == height;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.hold();
    	Robot.pneumatic.out();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
