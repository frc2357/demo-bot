package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class DriveStop extends Command {
    public DriveStop() {
        addRequirements(Robot.driveSubsystem);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.driveSubsystem.setMaxOutput(0);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
}
