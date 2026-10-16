package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class DefaultDrive extends Command {
    public DefaultDrive() {
        addRequirements(Robot.driveSubsystem);
    }

    @Override
    public void execute() {
        Robot.driveSubsystem.setMaxOutput(1);
        Robot.driveSubsystem.arcadeDrive();
    }
}
