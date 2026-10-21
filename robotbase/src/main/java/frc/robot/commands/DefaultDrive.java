package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class DefaultDrive extends Command {
    public DefaultDrive() {
        addRequirements(Robot.driveSubsystem);
    }

    // @Override
    // public void initialize() {
    //     Robot.driveSubsystem.setSpeed(10);
    // }

    // @Override
    // public void end(boolean interrupted) {
    //     Robot.driveSubsystem.stop();
    // }
}
