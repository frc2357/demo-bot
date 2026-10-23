package frc.robot.commands;

import edu.wpi.first.units.measure.Dimensionless;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class SetDriveSpeed extends Command {
    private final Dimensionless speed;

    public SetDriveSpeed(Dimensionless speed) {
        addRequirements(Robot.driveSubsystem);
        this.speed = speed;
    }

    @Override
    public void initialize() {
        Robot.driveSubsystem.setSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.driveSubsystem.stop();
    }
}
